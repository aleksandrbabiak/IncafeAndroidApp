package com.example.alex.incafeandroidapp1.utils;

import com.example.alex.incafeandroidapp1.database.model.DishItem;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Alex on 13.11.2015.
 */
public interface ServerApi {

    @GET("dish/getAll")
    public Call<List<DishItem>> getDish(@Query("resId") int resId);

    @POST("incafe-rest/client/order")
    public Call<DishItem> getDish1( int resId);

}
