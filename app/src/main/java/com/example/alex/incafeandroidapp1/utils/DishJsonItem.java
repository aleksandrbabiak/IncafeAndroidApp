package com.example.alex.incafeandroidapp1.utils;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.alex.incafeandroidapp1.entity.JSonItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 13.11.2015.
 */
public class DishJsonItem {

//    public static final String NAME = "name";
//    public static final String PRICE = "price";
//    public static final String COOK_TIME = "cook_time";
//    public static final String PHOTO = "photo";
//    public static final String RATING = "rating";



    private String name;
    private double price;
    private String cookTime;
    private String photo;
    private double rating;


//    protected DishJsonItem(Parcel in) {
//        name = in.readString();
//        price = in.readDouble();
//        cookTime = in.readString();
//        photo = in.readString();
//        rating = in.readDouble();
//    }
//
//    public static final Creator<DishJsonItem> CREATOR = new Creator<DishJsonItem>() {
//        @Override
//        public DishJsonItem createFromParcel(Parcel in) {
//            return new DishJsonItem(in);
//        }
//
//        @Override
//        public DishJsonItem[] newArray(int size) {
//            return new DishJsonItem[size];
//        }
//    };
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(name);
//        parcel.writeDouble(price);
//        parcel.writeString(cookTime);
//        parcel.writeString(photo);
//        parcel.writeDouble(rating);
//    }
    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public String getCookTime(){
        return cookTime;
    }

    public String getPhoto() {
        return photo;
    }

    public double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}
