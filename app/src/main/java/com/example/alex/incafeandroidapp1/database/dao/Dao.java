package com.example.alex.incafeandroidapp1.database.dao;

import com.example.alex.incafeandroidapp1.database.model.DbItem;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.util.List;

public interface Dao<T extends DbItem> {


    public List<T> getAllItems();

    public void deleteAllItems();

    public boolean createItem(T item);

//   public void createItems(List<Item> items);

    public boolean updateItem(String key, T value);

    public boolean updateItem(T dbItem);

    public boolean deleteItem(T dbItem);

    public T getItem(long id);
    public List<T> query(PreparedQuery<T> preparedQuery);



    public QueryBuilder getQueryBuilder();

}

