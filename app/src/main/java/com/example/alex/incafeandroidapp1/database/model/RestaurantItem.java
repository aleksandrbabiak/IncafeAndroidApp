package com.example.alex.incafeandroidapp1.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Alex on 14.11.2015.
 */
@DatabaseTable(tableName = "restaurant")
public class RestaurantItem extends DbItem implements Parcelable {
    private static final String NAME = "name";
    private static final String DISH_ID = "dish_id";

    @DatabaseField(canBeNull = true, dataType = DataType.STRING, columnName = NAME)
    private String name;

    @DatabaseField(foreign = true, columnName = DISH_ID)
    private DishItem dishItem;

    protected RestaurantItem(Parcel in) {
        name = in.readString();
        dishItem = in.readParcelable(DishItem.class.getClassLoader());
    }

    public static final Creator<RestaurantItem> CREATOR = new Creator<RestaurantItem>() {
        @Override
        public RestaurantItem createFromParcel(Parcel in) {
            return new RestaurantItem(in);
        }

        @Override
        public RestaurantItem[] newArray(int size) {
            return new RestaurantItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeParcelable(dishItem, i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishItem getDishItem() {
        return dishItem;
    }

    public void setDishItem(DishItem dishItem) {
        this.dishItem = dishItem;
    }


}
