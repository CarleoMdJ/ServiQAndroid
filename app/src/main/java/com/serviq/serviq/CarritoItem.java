package com.serviq.serviq;

/**
 * Created by al342944 on 10/21/2016.
 */

public class CarritoItem
{
    private long id;
    private int cantidad;
    private Comida comida;
    private String descripcion;

    public CarritoItem(Comida comida, String descripcion)
    {
        this.comida = comida;
        this.cantidad = 1;
        this.descripcion = descripcion;
    }

    public CarritoItem() {}

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public Comida getComida() {
        return comida;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }
}
