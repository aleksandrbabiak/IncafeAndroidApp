package com.example.alex.incafeandroidapp1.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 13.11.2015.
 */
public class JSONUtils {
    private JSONUtils() {
    }

    public static List<JSONObject> prepareList(JSONArray jsonArray) {
        List<JSONObject> jsonObjects = new ArrayList<>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObjects.add(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObjects;
    }
}
