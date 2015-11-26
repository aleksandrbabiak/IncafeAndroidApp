package com.example.alex.incafeandroidapp1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.incafeandroidapp1.database.DataBaseHelper;
import com.example.alex.incafeandroidapp1.database.dao.DaoImpl;
import com.example.alex.incafeandroidapp1.database.model.DishItem;
import com.example.alex.incafeandroidapp1.utils.FileUtil;
import com.example.alex.incafeandroidapp1.utils.ServerApi;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Alex on 20.11.2015.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String LOG_TAG = SplashScreenActivity.class.getSimpleName();
    public static String STARTED_PREF = "0";
    private static int countPref = 0;
    //  private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    //   private ImageView imageView;
    private Button anonimusButton;
    private Intent mainIntent;
    private final String ID = "id";
    private final String NAME = "name";
    private Bundle parameters;
    private Retrofit retrofit;
    private ServerApi serverApi;
    private DaoImpl daoDish;
    private SharedPreferences sharedpreferences;
    private ArrayList<DishItem> dishItemList;
    private DaoImpl daoPathBigPhoto;
    private TextView info;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        initUi();
        mainIntent = new Intent(this, MainActivity.class);
        configAndStartFacebook();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private String parseJson(JSONObject jsonObject) {
        String name = null;
        try {
            name = jsonObject.getString(NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }

    private ServerApi createdRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl("http://46.228.199.33:8080/incafe-rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi = retrofit.create(ServerApi.class);
        return serverApi;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anonimus:
                daoDish = (DaoImpl<DishItem>) DataBaseHelper.getInstance().getSensorDao(DishItem.class);
                dishItemList = (ArrayList<DishItem>) daoDish.getAllItems();
                if(dishItemList != null && dishItemList.size() > 0){
                    mainIntent.putExtra("list", dishItemList);
                    startActivity(mainIntent);
                }else {
                    startRetrofit();
                }
                    break;

        }
    }

    private void saveInSharedPreferenceCountCall() {
        sharedpreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedpreferences.edit();

        ed.putInt(STARTED_PREF, 1);

        ed.commit();

    }

    private int checkingFirstDawnload() {

        sharedpreferences = getPreferences(MODE_PRIVATE);
        int savedInt = sharedpreferences.getInt(STARTED_PREF, 0);
        return savedInt;
    }


    private void startRetrofit() {
        final Call<List<DishItem>> dishItems = createdRetrofit().getDish(2);
        dishItems.enqueue(new Callback<List<DishItem>>() {

            @Override
            public void onResponse(Response<List<DishItem>> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    daoDish = (DaoImpl<DishItem>) DataBaseHelper.getInstance().getSensorDao(DishItem.class);

                    if (checkingFirstDawnload() == 0) {
                        Log.d(LOG_TAG, "result id " + response.body().get(0).getId());
                        List<DishItem> dishItemsList = response.body();
                        saveInSharedPreferenceCountCall();

                        for (DishItem dishItem : dishItemsList) {
                            String filePath = FileUtil.savePhotoByUrl(dishItem.getPhotoBig());
                            Log.d(LOG_TAG, "File filePath: " + filePath);
                            dishItem.setLocalPathBigPhoto(filePath);
                            daoDish.createItem(dishItem);
                        }
                    }

                    dishItemList = (ArrayList<DishItem>) daoDish.getAllItems();

                    if (dishItemList != null) {
                        Log.d(LOG_TAG, "List " + dishItemList.size());
                        mainIntent.putExtra("list", dishItemList);
                        startActivity(mainIntent);
                    }
                } else {
                    Log.d(LOG_TAG, "service not worked " + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, t.getMessage());
            }
        });
    }

    private void configAndStartFacebook() {
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
                showInfoText(loginResult);
                mainIntent.putExtra(ID, loginResult.getAccessToken().getUserId());
                final GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                if (parseJson(response.getJSONObject()) != null) {
                                    mainIntent.putExtra(NAME, parseJson(response.getJSONObject()));
                                }
                            }
                        });
                parameters = new Bundle();
                parameters.putString("fields", NAME);
                request.setParameters(parameters);
                request.executeAsync();
                startRetrofit();

            }


            @Override
            public void onCancel() {
                Log.d("LoginActivity", "cancel");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d("LoginActivity", "error " + e);
            }
        });
    }

    private void showInfoText(LoginResult loginResult) {
        info.setText(
                "User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken()
        );
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initUi() {

        info = (TextView) findViewById(R.id.info);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //  imageView = (ImageView) findViewById(R.id.image);
        anonimusButton = (Button) findViewById(R.id.anonimus);
        anonimusButton.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(getResources().getDrawable(R.drawable.background_splesh));
        setSupportActionBar(toolbar);
    }
}

