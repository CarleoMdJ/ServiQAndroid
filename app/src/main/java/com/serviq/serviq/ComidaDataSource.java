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
 * Created by gildardo on 8/12/16.
 */

public class ComidaDataSource
{
    private SQLiteOpenHelper dbhelper;
    private SQLiteDatabase database;

    private static final String[] allColumns = {
            ServiqDBOpenHelper.COLUMN_ID,
            ServiqDBOpenHelper.COLUMN_TITLE,
            ServiqDBOpenHelper.COLUMN_PRICE,
            ServiqDBOpenHelper.COLUMN_DESC,
            ServiqDBOpenHelper.COLUMN_TIME,
            ServiqDBOpenHelper.COLUMN_IMAGE
    };
    public ComidaDataSource(Context context)
    {
        dbhelper = new ServiqDBOpenHelper(context);

    }

    public void open()
    {
        Log.i("My app", "Database opened");
        database = dbhelper.getWritableDatabase();
    }

    public void close()
    {
        Log.i("My app", "Database closed");
        dbhelper.close();
    }

    public Comida create(Comida comida)
    {
        ContentValues values = new ContentValues();
        values.put(ServiqDBOpenHelper.COLUMN_TITLE, comida.getComida());
        values.put(ServiqDBOpenHelper.COLUMN_DESC, comida.getDescripcion());
        values.put(ServiqDBOpenHelper.COLUMN_PRICE, comida.getPrecio());
        values.put(ServiqDBOpenHelper.COLUMN_TIME, comida.getTiempo());
        values.put(ServiqDBOpenHelper.COLUMN_IMAGE, comida.getAvatar());
        long insertId = database.insert(ServiqDBOpenHelper.TABLE_COMIDA, null, values);
        comida.setId(insertId);
        return comida;
    }

    public List<Comida> findAll()
    {
        List<Comida> comidas = new ArrayList<>();

        Cursor cursor =  database.query(ServiqDBOpenHelper.TABLE_COMIDA, allColumns,
                null, null, null, null, null);
        Log.i("My app", cursor.getCount() + "renglones");
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                Comida comida = new Comida();
                comida.setId(cursor.getLong(cursor.getColumnIndex(ServiqDBOpenHelper.COLUMN_ID)));
                comida.setComida(cursor.getString(cursor.getColumnIndex(ServiqDBOpenHelper.COLUMN_TITLE)));
                comida.setDescripcion(cursor.getString(cursor.getColumnIndex(ServiqDBOpenHelper.COLUMN_DESC)));
                comida.setPrecio(cursor.getDouble(cursor.getColumnIndex(ServiqDBOpenHelper.COLUMN_PRICE)));
                comida.setTiempo(cursor.getDouble(cursor.getColumnIndex(ServiqDBOpenHelper.COLUMN_TIME)));
                comidas.add(comida);
            }
        }
        return comidas;
    }

}
