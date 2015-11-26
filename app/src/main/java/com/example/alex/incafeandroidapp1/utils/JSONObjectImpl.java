package com.example.alex.incafeandroidapp1.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 12.11.2015.
 */
public class JSONObjectImpl extends JSONObject {
    private static final String LOG_TAG = JSONObjectImpl.class.getSimpleName();
    private static final String EXCEPTION_MESSAGE = "Problem to put value into JSON";

    public JSONObjectImpl() {}

    public JSONObjectImpl(String jsonString) throws JSONException {
        super(jsonString);
    }



    /**
     * Maps {@code name} to {@code value}, clobbering any existing name/value
     * mapping with the same name. If the value is {@code null}, put {@link #NULL}.
     */
    @Override
    public JSONObject put(String name, Object value) {
        JSONObject obj = null;
        if (value == null)
            value = JSONObject.NULL;
        try {
            obj = super.put(name, value);
        } catch (JSONException e) {
            Log.d(LOG_TAG, EXCEPTION_MESSAGE);
        }
        return obj;
    }

    @Override
    public JSONObject put(String name, boolean value) {
        JSONObject obj = null;
        try {
            obj = super.put(name, value);
        } catch (JSONException e) {
            Log.d(LOG_TAG, EXCEPTION_MESSAGE);
        }
        return obj;
    }

    @Override
    public JSONObject put(String name, double value) {
        JSONObject obj = null;
        try {
            obj = super.put(name, value);
        } catch (JSONException e) {
            Log.d(LOG_TAG, EXCEPTION_MESSAGE);
        }
        return obj;
    }

    @Override
    public JSONObject put(String name, int value) {
        JSONObject obj = null;
        try {
            obj = super.put(name, value);
        } catch (JSONException e) {
            Log.d(LOG_TAG, EXCEPTION_MESSAGE);
        }
        return obj;
    }

    @Override
    public JSONObject put(String name, long value) {
        JSONObject obj = null;
        try {
            obj = super.put(name, value);
        } catch (JSONException e) {
            Log.d(LOG_TAG, EXCEPTION_MESSAGE);
        }
        return obj;
    }

    /**
     * Returns the value mapped by {@code name} if it exists, coercing it if
     * necessary. Returns {@code fallback} if no such mapping exists or if it has
     * a mapping whose value is {@link #NULL}.
     */
    @Override
    public String optString(String name, String fallback) {
        if (isNull(name)) return fallback;

        return super.optString(name, fallback);
    }
}
