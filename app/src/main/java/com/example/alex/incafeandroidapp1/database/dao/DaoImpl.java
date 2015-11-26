package com.example.alex.incafeandroidapp1.database.dao;

import android.util.Log;

import com.example.alex.incafeandroidapp1.database.model.DbItem;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.StatementBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 15.11.2015.
 */
public class DaoImpl<T extends DbItem> implements Dao {
   protected final String LOG_TAG = DaoImpl.class.getSimpleName();
    protected BaseDaoImpl<T, Long> baseDao;

    public DaoImpl(BaseDaoImpl<T, Long> baseDao) {
        this.baseDao = baseDao;


    }

    @Override
    public List<T> getAllItems() {
        try {
            List<T> items = baseDao.queryBuilder().query();
            return items;
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Can't get dataItem " + Log.getStackTraceString(e));
        }
        return new ArrayList<T>();
    }

    @Override
    public void deleteAllItems() {
        try {
            TableUtils.clearTable(baseDao.getConnectionSource(), baseDao.getDataClass());
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Can't clear dataItem " + Log.getStackTraceString(e));
        }
    }

    @Override
    public boolean createItem(DbItem dbItem) {
        boolean isSuccess = false;
        try {
            Log.d(LOG_TAG, "insert item " + dbItem.getId());
            com.j256.ormlite.dao.Dao.CreateOrUpdateStatus createOrUpdateStatus = baseDao.createOrUpdate((T) dbItem);
            isSuccess = createOrUpdateStatus.isCreated() || createOrUpdateStatus.isUpdated();

        } catch (SQLException e) {
            Log.e(LOG_TAG, e.toString());
        }
        return isSuccess;
    }


    @Override
    public boolean updateItem(String key, DbItem value) {
        try {
            UpdateBuilder<T, Long> updateBuilder = baseDao.updateBuilder();
            StatementBuilder statementBuilder = updateBuilder.updateColumnValue(key, value);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Can't get dataItem " + Log.getStackTraceString(e));
        }
        return false;
    }


    @Override
    public boolean updateItem(DbItem dbItem) {
        boolean isSuccess = false;
        try {
            int updateRowCount = baseDao.update((T) dbItem);
            Log.d(LOG_TAG, " update row count :" + updateRowCount);
            if (updateRowCount > 0) isSuccess = true;
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.toString());
        }
        return isSuccess;
    }

    @Override
    public boolean deleteItem(DbItem dbItem) {
        boolean isSuccess = false;

        try {
            int deleteRowCount = baseDao.delete((T) dbItem);
            Log.d(LOG_TAG, " delete row count :" + deleteRowCount);
            if (deleteRowCount > 0) isSuccess = true;
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.toString());
        }
        return false;
    }

    @Override
    public DbItem getItem(long id) {
        try {
            return (DbItem) getQueryBuilder().where().eq(DbItem.ID, id).queryForFirst();
        } catch (SQLException e) {
           Log.e(LOG_TAG,e.toString());
        }
        return null;
    }




    @Override
    public QueryBuilder getQueryBuilder() {
        return baseDao.queryBuilder();
    }



    public List query(PreparedQuery preparedQuery){
        List<T> list = new ArrayList<>();
        try {
            list =   baseDao.query(preparedQuery);
        } catch (SQLException e) {
           Log.e(LOG_TAG,e.toString());
        }
        return list;
    }
}
