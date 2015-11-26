package com.example.alex.incafeandroidapp1.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.alex.incafeandroidapp1.database.model.DishTypeItem;
import com.example.alex.incafeandroidapp1.utils.JSONObjectImpl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 12.11.2015.
 */
public class DishTypeJsonItem extends JSonItem implements Parcelable {

    public static final String NAME = "name";
    private String name;

    public DishTypeJsonItem(JSONObject jsonObject) {
        super(jsonObject);
    }


    protected DishTypeJsonItem(Parcel in) {
        this.name = in.readString();
    }

    @Override
    protected void parse(JSONObjectImpl object) throws JSONException {
        super.parse(object);
        name = object.optString(NAME);
    }





    public static final Creator<DishTypeJsonItem> CREATOR = new Creator<DishTypeJsonItem>() {
        @Override
        public DishTypeJsonItem createFromParcel(Parcel in) {
            return new DishTypeJsonItem(in);
        }

        @Override
        public DishTypeJsonItem[] newArray(int size) {
            return new DishTypeJsonItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }

    public String getName() {
        return name;
    }

}
