package com.example.alex.incafeandroidapp1.database.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.alex.incafeandroidapp1.entity.SuggestionJsonItem;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "suggestion")
public class SuggestionItem extends DbItem implements Parcelable{
    public static final String DISH_ID = "dish_id";

    @DatabaseField(foreign = true, columnName = DISH_ID)
    private DishItem dishItem;

    public SuggestionItem() {

    }

    public SuggestionItem(SuggestionJsonItem suggestionJsonItem) {
        super(suggestionJsonItem);
    }

    protected SuggestionItem(Parcel in) {
        id = in.readLong();
    }

    public static final Creator<SuggestionItem> CREATOR = new Creator<SuggestionItem>() {
        @Override
        public SuggestionItem createFromParcel(Parcel in) {
            return new SuggestionItem(in);
        }

        @Override
        public SuggestionItem[] newArray(int size) {
            return new SuggestionItem[size];
        }
    };

    public DishItem getDishItem() {
        return dishItem;
    }

    public void setDishItem(DishItem dishItem) {
        this.dishItem = dishItem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
    }
}
