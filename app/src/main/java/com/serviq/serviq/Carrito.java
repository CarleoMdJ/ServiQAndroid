package com.serviq.serviq;

import java.util.ArrayList;

/**
 * Created by al342944 on 10/21/2016.
 */

public class Carrito
{
    private ArrayList<CarritoItem> comidas;
    private double total;

    public Carrito()
    {
        this.comidas = new ArrayList<CarritoItem>();
        this.total = 0.0;
    }

    /**
     * Calcula el precio de las comidas en el carrito.
     * @return Total del carrito.
     */
    private double calcularTotal()
    {
        double costo = 0.0;
        for(CarritoItem item: comidas) {
            costo += item.getCantidad() * item.getComida().getPrecio();
        }
        return costo;
    }

    private double getTotal()
    {
        this.total = this.calcularTotal();
        return this.total;
    }

}
