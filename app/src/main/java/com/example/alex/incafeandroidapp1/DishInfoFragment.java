package com.example.alex.incafeandroidapp1;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.incafeandroidapp1.database.DataBaseHelper;
import com.example.alex.incafeandroidapp1.database.dao.DaoImpl;
import com.example.alex.incafeandroidapp1.database.model.DishItem;
import com.example.alex.incafeandroidapp1.entity.OrderDish;
import com.example.alex.incafeandroidapp1.utils.FileUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Created by Alex on 23.11.2015.
 */
public class DishInfoFragment extends Fragment {
    private static final String LOG_TAG = DishInfoFragment.class.getSimpleName();
    private static String DISH_ITEM = "dish_item";
    private TextView dishName;
    private TextView dishCountMenu;
    private ImageView dishPhoto;
    private Button dishPrice;
    MainActivity mainActivity;
    private DishItem dishItem;
    private OrderDish orderDish;
    private int indicatorCount = 8;
    private int clickCountOrderDish = 0;

    public static DishInfoFragment newInstance(DishItem dishItem) {
        DishInfoFragment dishInfoFragment = new DishInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(DISH_ITEM, dishItem);

        dishInfoFragment.setArguments(bundle);
        return dishInfoFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        if (mainActivity.orderDishList.size() > 0) {
            mainActivity.orderDishList.get(0).getDishCount();
        }
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(DISH_ITEM)) {

            dishItem = bundle.getParcelable(DISH_ITEM);
            orderDish = new OrderDish();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_dish_info, container, false);

        initUI(view);

        final String pathPhoto = dishItem.getLocalPathBigPhoto();

        if (!TextUtils.isEmpty(pathPhoto)) {
            picassoDawnloadPhotoFromLocalPath(pathPhoto);
            initOrderDish().setDishPhotoLocalPath(pathPhoto);
        } else {
            String urlPhoto = dishItem.getPhotoBig();
            picassoDawnloadPhotoFromURL(urlPhoto);
            initOrderDish().setDishPhotoLocalPath(urlPhoto);
            DaoImpl daoDish = (DaoImpl<DishItem>) DataBaseHelper.getInstance().getSensorDao(DishItem.class);
            daoDish.updateItem(dishItem);
        }


        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initUI(View view) {
        dishName = (TextView) view.findViewById(R.id.dish_name_menu);
        dishCountMenu = (TextView) view.findViewById(R.id.dish_count_menu);
        dishPhoto = (ImageView) view.findViewById(R.id.dish_photo_menu);
        dishPrice = (Button) view.findViewById(R.id.dish_price_menu);

        dishName.setText(dishItem.getName());
        dishPrice.setText("ЗАКАЗАТЬ ЗА " + dishItem.getPrice() + " ГРН");
        dishPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCountOrderDish = clickCountOrderDish + 1;
                dishCountMenu.setText("" + clickCountOrderDish);
                dishCountMenu.setVisibility(clickCountOrderDish > 0 ? View.VISIBLE : View.GONE);
                mainActivity.addOrderDish(initOrderDish());
            }
        });
    }

    private OrderDish initOrderDish() {
        orderDish.setDishCount(clickCountOrderDish);
        orderDish.setDishName(dishItem.getName());
        orderDish.setPriceDish(dishItem.getPrice());
        if (FileUtil.isFileExist(dishItem.getLocalPathBigPhoto())) {
            orderDish.setDishPhotoLocalPath(dishItem.getLocalPathBigPhoto());
        } else {
            orderDish.setDishPhotoUrl(dishItem.getPhotoBig());
        }
        return orderDish;
    }

    private void picassoDawnloadPhotoFromLocalPath(String pathPhoto) {

        Picasso.with(mainActivity)
                .load(new File(pathPhoto))
                .error(android.R.drawable.stat_notify_error)
                .transform(createdTransformation())
                .into(dishPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(LOG_TAG, "picasso dawnload with path");
                    }

                    @Override
                    public void onError() {
                        Log.e(LOG_TAG, "error download with path");
                    }
                });
    }

    private Transformation createdTransformation() {
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = dishPhoto.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        return transformation;
    }


    private void picassoDawnloadPhotoFromURL(String urlPhoto) {

        Picasso.with(mainActivity)
                .load(urlPhoto)
                .error(android.R.drawable.stat_notify_error)
                .transform(createdTransformation())
                .into(dishPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d(LOG_TAG, "picasso dawnload with url");
                    }

                    @Override
                    public void onError() {
                        Log.e(LOG_TAG, "error download with url");
                    }
                });

    }


}
