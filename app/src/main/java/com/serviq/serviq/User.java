package com.serviq.serviq;

import java.util.UUID;

/**
 * Created by Manuel Carrillo on 10/21/2016.
 */

public class User
{
    private long id;
    private String nombre;
    private String correo;
    private String password;

    public User(String nombre,
                  String correo, String password) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }
    public User() {}

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String nombre) {
        this.nombre = nombre;
    }

}
