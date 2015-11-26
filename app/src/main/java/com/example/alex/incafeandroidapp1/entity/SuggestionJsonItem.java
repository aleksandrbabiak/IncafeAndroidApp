package com.example.alex.incafeandroidapp1.entity;

import com.example.alex.incafeandroidapp1.database.model.DishItem;
import com.example.alex.incafeandroidapp1.utils.JSONObjectImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Alex on 13.11.2015.
 */
public class SuggestionJsonItem extends JSonItem {

    private List<DishItem> dishItems;


    public SuggestionJsonItem(JSONObject object) {
        super(object);
    }

    public List<DishItem> getSexJsonItems() {
        return dishItems;
    }

    @Override
    protected void parse(JSONObjectImpl object) throws JSONException {
        super.parse(object);

      //  dishItems = JSONFactory.prepareList(object, JSONTypes.DISH);
    }









}
