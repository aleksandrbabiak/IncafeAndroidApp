package com.example.alex.incafeandroidapp1.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Alex on 13.11.2015.
 */

@DatabaseTable(tableName = "dish")
public class DishItem extends DbItem implements Parcelable {

    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String COOK_TIME = "cook_time";
    public static final String PHOTO = "photo";
    public static final String DISH_TYPE_ID = "dish_type_id";
    public static final String RATING = "rating";
    public static final String SUGGESTION_ID = "sugestion_id";
    public static final String PATH_BIG_PHOTO = "path_big_photo";


    @DatabaseField(canBeNull = true, dataType = DataType.STRING, columnName = NAME)
    private String name;

    @DatabaseField(canBeNull = true, dataType = DataType.DOUBLE, columnName = PRICE)
    private double price;

    @DatabaseField(canBeNull = true, dataType = DataType.STRING, columnName = COOK_TIME)
    private String cookTime;

    @DatabaseField(canBeNull = true, dataType = DataType.STRING,columnName = PHOTO)
    private String photoBig;

    @DatabaseField(foreign = true, columnName = DISH_TYPE_ID)
    private DishTypeItem dishTypeItem;

    @DatabaseField(canBeNull = true, dataType = DataType.DOUBLE, columnName = RATING)
    private double rating;

    @DatabaseField(foreign = true, columnName = SUGGESTION_ID)
    private SuggestionItem suggestionItem;

    @DatabaseField(canBeNull = true, dataType = DataType.STRING, columnName = PATH_BIG_PHOTO)
    private String localPathBigPhoto;

    public DishItem(){

    }


    protected DishItem(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        cookTime = in.readString();
        photoBig = in.readString();
        rating = in.readDouble();
        id = in.readLong();
        localPathBigPhoto = in.readString();
    }

    public static final Creator<DishItem> CREATOR = new Creator<DishItem>() {
        @Override
        public DishItem createFromParcel(Parcel in) {
            return new DishItem(in);
        }

        @Override
        public DishItem[] newArray(int size) {
            return new DishItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeString(cookTime);
        parcel.writeString(photoBig);
        parcel.writeDouble(rating);
        parcel.writeLong(id);
        parcel.writeString(localPathBigPhoto);


    }

    public DishTypeItem getDishTypeItem(){
        return dishTypeItem;
    }
    public SuggestionItem getSuggestionItem(){
        return suggestionItem;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCookTime() {
        return cookTime;
    }

    public String getPhotoBig() {
        return photoBig;
    }

    public double getRating() {
        return rating;
    }

    public String getLocalPathBigPhoto() {
        return localPathBigPhoto;
    }

    public void setLocalPathBigPhoto(String localPathBigPhoto) {
        this.localPathBigPhoto = localPathBigPhoto;
    }
}
