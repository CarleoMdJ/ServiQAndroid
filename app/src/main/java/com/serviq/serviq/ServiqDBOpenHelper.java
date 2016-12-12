package com.serviq.serviq;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by gildardo on 8/12/16.
 */

public class ServiqDBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "serviq.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMIDA = "comida";
    public static final String COLUMN_ID = "cId";
    public static final String COLUMN_TITLE = "comida";
    public static final String COLUMN_DESC = "descripcion";
    public static final String COLUMN_PRICE = "precio";
    public static final String COLUMN_TIME = "tiempo";
    public static final String COLUMN_IMAGE = "imagen";

    public static final String TABLE_CARRITO_ITEM = "carrito_item";
    public static final String ITEM_COLUMN_ID = "id";
    public static final String ITEM_COLUMN_DESC = "descripcion";
    public static final String ITEM_COLUMN_CANT = "cantidad";
    public static final String ITEM_COMIDA_ID = "comida_id";

    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_CARRITO_CREATE = "CREATE TABLE "
            + TABLE_CARRITO_ITEM + "(" + ITEM_COLUMN_ID + " INTEGER PRIMARY KEY, "
            + ITEM_COLUMN_DESC + " TEXT, " + ITEM_COLUMN_CANT + " NUMERIC, "
            + ITEM_COMIDA_ID + " INT, " + "FOREIGN KEY(" + ITEM_COMIDA_ID + ") REFERENCES "
            + COLUMN_ID + "(id) " + ")";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_COMIDA +  " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE + " TEXT, " + COLUMN_DESC + " TEXT," +
            COLUMN_PRICE + " NUMERIC, " + COLUMN_TIME + " NUMERIC, " +
            COLUMN_IMAGE + " TEXT " + ")";

    private static final String TABLE_USER_CREATE = "CREATE TABLE "
            + TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOMBRE + " TEXT NOT NULL, " + COLUMN_CORREO + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL, "
            + "UNIQUE (" + COLUMN_CORREO + ")";

    public ServiqDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        sqLiteDatabase.execSQL(TABLE_CARRITO_CREATE);
        sqLiteDatabase.execSQL(TABLE_USER);

        Log.i("ServiQ", "Table has been created ;D");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMIDA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARRITO_ITEM);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_CREATE);

        onCreate(sqLiteDatabase);
    }
}
