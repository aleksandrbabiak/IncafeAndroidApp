package com.example.alex.incafeandroidapp1;

import android.app.Application;
import android.util.Log;

import com.example.alex.incafeandroidapp1.database.DataBaseHelper;
import com.example.alex.incafeandroidapp1.utils.WakeLockProvider;
import com.facebook.FacebookSdk;

/**
 * Created by Alex on 10.11.2015.
 */
public class IncafeApplication extends Application{


    private static final String LOG_TAG = IncafeApplication.class.getSimpleName();
    private static IncafeApplication application;


    public static IncafeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //DataLoaderProvider.getInstance().init(this);
        Log.d(IncafeApplication.class.getSimpleName(), "onCreate " + getInstance());
        DataBaseHelper.initialize(this);
        WakeLockProvider.initialize(this);
      //  ClothesDataBaseWrapper.initialize();
        FacebookSdk.sdkInitialize(getApplicationContext());
    //    MSLoginManager.getInstance().init(this);

    }






}
