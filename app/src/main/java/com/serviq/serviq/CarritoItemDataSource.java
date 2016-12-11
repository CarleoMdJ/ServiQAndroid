package com.serviq.serviq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gildardo on 10/12/16.
 */

public class CarritoItemDataSource {

    private SQLiteOpenHelper dbhelper;
    private SQLiteDatabase database;
    private Context mContext;
    private static final String[] allColumns = {
            ServiqDBOpenHelper.ITEM_COLUMN_ID,
            ServiqDBOpenHelper.ITEM_COLUMN_CANT,
            ServiqDBOpenHelper.ITEM_COLUMN_DESC,
            ServiqDBOpenHelper.ITEM_COMIDA_ID
    };

    public CarritoItemDataSource(Context context)
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

    public CarritoItem create(CarritoItem carritoItem)
    {
        ContentValues values = new ContentValues();
        values.put(ServiqDBOpenHelper.ITEM_COLUMN_ID, carritoItem.getId());
        values.put(ServiqDBOpenHelper.ITEM_COLUMN_CANT, carritoItem.getCantidad());
        values.put(ServiqDBOpenHelper.ITEM_COLUMN_DESC, carritoItem.getDescripcion());
        values.put(ServiqDBOpenHelper.ITEM_COMIDA_ID, carritoItem.getComida().getId());
        long insertId = database.insert(ServiqDBOpenHelper.TABLE_CARRITO_ITEM, null, values);
        carritoItem.setId(insertId);
        return carritoItem;
    }

    public List<CarritoItem> findAll()
    {
        List<CarritoItem> items = new ArrayList<>();
        Cursor cursor = database.query(ServiqDBOpenHelper.TABLE_CARRITO_ITEM, allColumns,
                null, null, null, null, null);

        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                CarritoItem item = new CarritoItem();
                item.setId(cursor.getLong(cursor.getColumnIndex(
                        ServiqDBOpenHelper.ITEM_COLUMN_ID)
                ));

                item.setCantidad(cursor.getInt(cursor.getColumnIndex(
                        ServiqDBOpenHelper.ITEM_COLUMN_CANT)
                ));

                item.setDescripcion(cursor.getString(cursor.getColumnIndex(
                        ServiqDBOpenHelper.ITEM_COLUMN_DESC)
                ));

                //Agrega una nueva comida en el Carrito Item
                long comidaId = cursor.getLong(cursor.getColumnIndex(
                        ServiqDBOpenHelper.ITEM_COMIDA_ID)
                );

                ComidaDataSource dao = new ComidaDataSource(mContext);
                Comida comida = dao.getComidaById(comidaId);
                if (comida != null)
                    item.setComida(comida);

                items.add(item);
            }
        }
        return items;
    }
}
