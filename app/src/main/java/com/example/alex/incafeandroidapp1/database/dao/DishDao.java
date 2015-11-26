package com.example.alex.incafeandroidapp1.database.dao;

import com.example.alex.incafeandroidapp1.database.model.DishItem;

import java.util.List;

/**
 * Created by Alex on 15.11.2015.
 */
public interface DishDao extends Dao{
    List<DishItem> getAll(long clothesTypeId);
}
