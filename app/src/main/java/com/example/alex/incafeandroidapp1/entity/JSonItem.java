package com.example.alex.incafeandroidapp1.entity;

import com.example.alex.incafeandroidapp1.utils.JSONObjectImpl;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 12.11.2015.
 */
public class JSonItem {
    protected static final String ID = "id";
    protected int id;


    public JSonItem() {
    }
    public JSonItem(JSONObject object) {
        init(object.toString());
    }

    private void init(String inputString) {
        JSONObjectImpl jsonObject = null;
        try {
            jsonObject = new JSONObjectImpl(inputString);
            parse(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    protected void parse(JSONObjectImpl inputString) throws JSONException {
        id = inputString.getInt(ID);
    }

    public int getId() {
        return id;
    }
}

