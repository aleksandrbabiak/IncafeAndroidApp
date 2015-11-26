package com.example.alex.incafeandroidapp1.database.dao;

import android.util.Log;

import com.example.alex.incafeandroidapp1.database.model.DishItem;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 15.11.2015.
 */
public class DishDaoImpl extends DaoImpl implements DishDao{
    private static final String LOG_TAG = DishDaoImpl.class.getSimpleName();

    public DishDaoImpl(BaseDaoImpl baseDao) {
        super(baseDao);
    }

    @Override
    public List<DishItem> getAll(long clothesTypeId) {
        try {
            //eq("account_id", account.getName()).query();
            List<DishItem> items = getQueryBuilder().where()
                    .eq(DishItem.ID, clothesTypeId).query();


            return items;
        } catch (SQLException e) {
            Log.d(LOG_TAG, "Can't get dataItem " + Log.getStackTraceString(e));
        }
        return new ArrayList<>();
    }
}
