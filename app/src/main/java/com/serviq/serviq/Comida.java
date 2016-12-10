package com.serviq.serviq;

/**
 * Created by al342944 on 10/21/2016.
 */

public class Comida
{

    private long id;
    private String comida;
    private double precio;
    private String descripcion;
    private double tiempo;
    private String avatar;
    private int thumbnail;

    public Comida(String comida, double precio, String descripcion, double tiempo, String avatar)
    {
        this.comida = comida;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.avatar = avatar;
    }

    public Comida(String comida, double precio, String descripcion, double tiempo, int thumbnail)
    {
        this.comida = comida;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.thumbnail = thumbnail;
    }

    public Comida()
    {

    }

    public void setId(long id) { this.id = id; }

    public String getComida()
    {
        return comida;
    }

    public String getAvatar() { return avatar; }

    public void setComida(String comida)
    {
        this.comida = comida;
    }

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public double getTiempo()
    {
        return tiempo;
    }

    public void setTiempo(double tiempo)
    {
        this.tiempo = tiempo;
    }

    public long getId() { return id; }

    public int getThumbnail() { return thumbnail; }


}
