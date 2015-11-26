package com.example.alex.incafeandroidapp1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.incafeandroidapp1.entity.OrderDish;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.List;

/**
 * Created by Alex on 22.11.2015.
 */
public class InfoForOrderDishReciclerAdapter extends RecyclerView.Adapter<InfoForOrderDishReciclerAdapter.ViewHolder> implements View.OnClickListener {

    private static final String LOG_TAG = InfoForOrderDishReciclerAdapter.class.getSimpleName();
    private List<OrderDish> orderDishList;
    private Activity activity;
    private ViewHolder vh;


    public InfoForOrderDishReciclerAdapter(List<OrderDish> orderDishList, Activity activity) {
        this.orderDishList = orderDishList;
        this.activity = activity;
    }


    @Override
    public InfoForOrderDishReciclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recicler_info_for_order_dish, parent, false);



        vh = new ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dishName;
        public TextView dishCount;
        public TextView dishPrice;
        public Button deleteItemDish;
        public Button dishCountPlus;
        public Button dishCountMinus;
        public ImageView dishPhoto;


        public ViewHolder(View view) {
            super(view);
            dishName = (TextView) view.findViewById(R.id.dish_name);
            dishCount = (TextView) view.findViewById(R.id.dish_count);
            dishPrice = (TextView) view.findViewById(R.id.dish_price);
            deleteItemDish = (Button) view.findViewById(R.id.dish_item_delete);
            dishCountPlus = (Button) view.findViewById(R.id.dish_count_plus);
            dishCountMinus = (Button) view.findViewById(R.id.dish_count_minus);
            dishPhoto = (ImageView) view.findViewById(R.id.dish_photo);

        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderDish orderDish = orderDishList.get(position);
        String localPathPhoto = orderDish.getDishPhotoLocalPath();
        holder.dishName.setText(orderDish.getDishName());
        holder.dishCount.setText("" + orderDish.getDishCount());
        holder.dishPrice.setText("" + orderDish.getAllPriceOneKindDish() + " грн");
        if (!TextUtils.isEmpty(localPathPhoto)) {
            picassoDawnloadPhotoFromLocalPath(localPathPhoto);

        } else {
          picassoDawnloadPhotoFromURL(orderDish.getDishPhotoUrl());
        }

    }
    private void picassoDawnloadPhotoFromURL(String urlPhoto) {

        Picasso.with(activity)
                .load(urlPhoto)
                .error(android.R.drawable.stat_notify_error)
                .transform(createdTransformation())
                .into(vh.dishPhoto, new Callback() {
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

    private void picassoDawnloadPhotoFromLocalPath(String pathPhoto) {

        Picasso.with(activity)
                .load(new File(pathPhoto))
                .error(android.R.drawable.stat_notify_error)
                .transform(createdTransformation())
                .into(vh.dishPhoto, new Callback() {
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
                int targetWidth = vh.dishPhoto.getWidth();

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








    @Override
    public int getItemCount() {
        return orderDishList.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.dish_count_plus:
                break;

            case R.id.dish_count_minus:


        }
    }

}
