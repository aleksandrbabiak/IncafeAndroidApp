package com.example.alex.incafeandroidapp1.entity;

/**
 * Created by Alex on 22.11.2015.
 */
public class OrderDish {
    private String dishName;
    private String dishPhotoUrl;
    private String dishPhotoLocalPath;
    private double priceDish;
    private int dishCount;
    private double allPriceOneKindDish;


    public double getAllPriceOneKindDish() {
        return allPriceOneKindDish;

    }

    public void setAllPriceOneKindDish(double allPriceOneKindDish) {
        this.allPriceOneKindDish = allPriceOneKindDish;
    }

    public double getPriceDish() {
        return priceDish;
    }

    public void setPriceDish(double priceDish) {
        this.priceDish = priceDish;
    }



    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getDishCount() {
        return dishCount;
    }

    public void setDishCount(int dishCount) {
        this.dishCount = dishCount;
    }

    public String getDishPhotoUrl() {
        return dishPhotoUrl;
    }

    public void setDishPhotoUrl(String dishPhotoUrl) {
        this.dishPhotoUrl = dishPhotoUrl;
    }

    public String getDishPhotoLocalPath() {
        return dishPhotoLocalPath;
    }

    public void setDishPhotoLocalPath(String dishPhotoLocalPath) {
        this.dishPhotoLocalPath = dishPhotoLocalPath;
    }
}
