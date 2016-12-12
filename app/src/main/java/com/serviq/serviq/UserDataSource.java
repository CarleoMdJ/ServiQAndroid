package com.serviq.serviq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Carrillo on 10/12/16.
 */

public class UserDataSource {

    private SQLiteOpenHelper dbhelper;
    private SQLiteDatabase database;
    private Context mContext;
    private static final String[] allColumns = {
            ServiqDBOpenHelper.COLUMN_USER_ID,
            ServiqDBOpenHelper.COLUMN_NOMBRE,
            ServiqDBOpenHelper.COLUMN_CORREO,
            ServiqDBOpenHelper.COLUMN_PASSWORD
    };

    public UserDataSource(Context context)
    {
        dbhelper = new ServiqDBOpenHelper(context);
        mContext = context;
    }

    public void open()
    {
        database = dbhelper.getWritableDatabase();
    }

    public void close()
    {
        dbhelper.close();
    }

    public User create(User user)
    {
        ContentValues values = new ContentValues();
        values.put(ServiqDBOpenHelper.COLUMN_USER_ID, user.getId());
        values.put(ServiqDBOpenHelper.COLUMN_NOMBRE, user.getNombre());
        values.put(ServiqDBOpenHelper.COLUMN_CORREO, user.getCorreo());
        values.put(ServiqDBOpenHelper.COLUMN_PASSWORD, user.getPassword());
        long insertId = database.insert(ServiqDBOpenHelper.TABLE_USER, null, values);
        user.setId(insertId);
        return user;
    }

    public List<User> findAll()
    {
        List<User> items = new ArrayList<>();
        Cursor cursor = database.query(ServiqDBOpenHelper.TABLE_USER, allColumns,
                null, null, null, null, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Log.d("UserDataSource","LEL!!!!!LEL!!!!!!");
                User item = new User();
                item.setId(cursor.getLong(cursor.getColumnIndex(
                        ServiqDBOpenHelper.COLUMN_USER_ID)
                ));

                item.setNombre(cursor.getString(cursor.getColumnIndex(
                        ServiqDBOpenHelper.COLUMN_NOMBRE)
                ));

                item.setCorreo(cursor.getString(cursor.getColumnIndex(
                        ServiqDBOpenHelper.COLUMN_CORREO)
                ));

                item.setPassword(cursor.getString(cursor.getColumnIndex(
                        ServiqDBOpenHelper.COLUMN_PASSWORD)
                ));

                UserDataSource uds = new UserDataSource(mContext);

                items.add(item);
            }
        }
        return items;
    }
}
