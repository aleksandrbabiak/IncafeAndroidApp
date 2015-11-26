package com.example.alex.incafeandroidapp1.database.model;

import com.example.alex.incafeandroidapp1.entity.JSonItem;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import org.json.JSONObject;

/**
 * Created by Alex on 10.11.2015.
 */
public class DbItem {


    public static final String ID = "id";
    @DatabaseField(generatedId = true, columnName = ID)
    protected long id;

    public DbItem(JSonItem jSonItem) {
        this.id = jSonItem.getId();

    }

    public DbItem() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
