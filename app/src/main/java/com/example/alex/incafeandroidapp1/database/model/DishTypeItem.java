package com.example.alex.incafeandroidapp1.database.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.alex.incafeandroidapp1.entity.DishTypeJsonItem;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "dish_type")
public class DishTypeItem extends DbItem implements Parcelable {

    public static final String NAME = "name";
    public static final String DISH_ID = "dish_id";

    @DatabaseField(canBeNull = true, columnName = NAME, dataType = DataType.STRING)
    protected String name = "";

    @DatabaseField(foreign = true, columnName = DISH_ID, foreignAutoRefresh = true)
    private DishItem dishItem;


    public DishTypeItem(DishTypeJsonItem dishTypeJsonItem) {
        super(dishTypeJsonItem);
        name = dishTypeJsonItem.getName();
    }

    public DishTypeItem() {

    }

    public DishItem getDishItem() {
        return dishItem;
    }

    public void setDishItem(DishItem dishItem) {
        this.dishItem = dishItem;
    }

    public static final Creator<DishTypeItem> CREATOR = new Creator<DishTypeItem>() {
        @Override
        public DishTypeItem createFromParcel(Parcel in) {
            return new DishTypeItem(in);
        }

        @Override
        public DishTypeItem[] newArray(int size) {
            return new DishTypeItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public DishTypeItem(Parcel in) {
        id = in.readLong();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
    }
}
