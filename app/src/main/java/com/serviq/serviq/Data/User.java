package com.serviq.serviq.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.serviq.serviq.data.UsersContract.UserEntry;

import java.util.UUID;

/**
 * Created by Manuel Carrillo on 27/11/2016.
 */

public class User {
    private String id;
    private String nombre;
    private String correo;
    private String password;

    public User(String nombre,String correo,String password) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public User(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(UserEntry.ID));
        nombre = cursor.getString(cursor.getColumnIndex(UserEntry.NOMBRE));
        correo = cursor.getString(cursor.getColumnIndex(UserEntry.CORREO));
        password = cursor.getString(cursor.getColumnIndex(UserEntry.PASSWORD));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(UserEntry.ID, id);
        values.put(UserEntry.NOMBRE, nombre);
        values.put(UserEntry.CORREO, correo);
        values.put(UserEntry.PASSWORD, password);;
        return values;
    }

    public String getId(){
        return id;
    }

    public  String getNombre(){
        return nombre;
    }

    public  String getCorreo(){
        return correo;
    }

    public  String getPassword(){
        return password;
    }
}
