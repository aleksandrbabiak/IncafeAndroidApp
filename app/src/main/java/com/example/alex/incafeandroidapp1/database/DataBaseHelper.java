package com.example.alex.incafeandroidapp1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alex.incafeandroidapp1.database.dao.Dao;
import com.example.alex.incafeandroidapp1.database.dao.DishDaoImpl;
import com.example.alex.incafeandroidapp1.database.model.DbItem;
import com.example.alex.incafeandroidapp1.database.model.DishItem;
import com.example.alex.incafeandroidapp1.database.model.DishTypeItem;
import com.example.alex.incafeandroidapp1.database.model.SuggestionItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Alex on 10.11.2015.
 */
public class DataBaseHelper <T extends DbItem> extends OrmLiteSqliteOpenHelper{
    private static final String LOG_TAG = DataBaseHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incafe.db";
    private static volatile DataBaseHelper instance;

    public static final Class[] sensorItemClasses = new Class[]{DishItem.class, DishTypeItem.class, SuggestionItem.class};

    private ConcurrentMap<Class<T>, Dao<T>> daos = new ConcurrentHashMap<>();

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static void initialize(Context context) {
        instance = OpenHelperManager.getHelper(context, DataBaseHelper.class);
    }


    public static DataBaseHelper getInstance() {
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class sensorItemClass : sensorItemClasses)
                TableUtils.createTable(connectionSource, sensorItemClass);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Error on creation of db", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public <E extends Dao> E getSensorDao(Class<T> clazz) {
//        DebugLog.d(LOG_TAG,"CLASSNAME:= "+clazz.getName());
//        DebugLog.d(LOG_TAG,"SUPER CLASS:= "+clazz.getSuperclass());
//        DebugLog.d(LOG_TAG,"Class name: is extend from DbItemWithNetworkID: "+ DbItemWithNetworkId.class.isAssignableFrom(clazz));
//        DebugLog.d(LOG_TAG,"clazz.isInstance(DbItemWithNetworkId.class):"+DbItemWithNetworkId.class.isInstance(clazz));
//        DebugLog.d(LOG_TAG,"_______________________________________________________________________________________________________");

        Dao<T> dao = daos.get(clazz);
        if (dao == null) {
            synchronized (clazz) {
                dao = daos.get(clazz);
                if (dao == null) {
                    try {
                        if (clazz.equals(DishItem.class)) {
                            dao = new DishDaoImpl((BaseDaoImpl) DaoManager.createDao(getConnectionSource(), clazz));
//                        } else if (clazz.equals(PathBigPhotoItem.class)) {
//                            dao = new PathBigPhotoImpl((BaseDaoImpl) DaoManager.createDao(getConnectionSource(), clazz));
//                        } else if (clazz.equals(ShopItem.class)) {
//                            dao = new ShopItemDaoImpl((BaseDaoImpl) DaoManager.createDao(getConnectionSource(), clazz));
//                        } else if (DbItemWithNetworkId.class.isAssignableFrom(clazz)) {
//                            dao = new DaoWithNetworkIdImpl((BaseDaoImpl) DaoManager.createDao(getConnectionSource(), clazz));
//                        } else {
//                            dao = new DaoImpl<T>((BaseDaoImpl) DaoManager.createDao(getConnectionSource(), clazz));
                        }
                        daos.put(clazz, dao);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (E) dao;
    }



    public void clearAllDb() {
        for (Dao dao : daos.values()) {
            dao.deleteAllItems();
        }
    }


    @Override
    public void close() {
        super.close();
        daos.clear();
    }
}
