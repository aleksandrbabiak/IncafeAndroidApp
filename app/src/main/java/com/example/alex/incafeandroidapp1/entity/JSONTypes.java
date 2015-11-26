package com.example.alex.incafeandroidapp1.entity;

import org.json.JSONObject;

/**
 * Created by Alex on 13.11.2015.
 */
public enum JSONTypes {

    DISH("dish"),
    DISH_TYPE("dish_type"),
    SUGGESTION("suggestion");
    public String value;

    JSONTypes(String value) {
        this.value = value;
    }
}
