package org.example.proyecto;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarritoModel {
    private static CarritoModel instancia;
    private final Map<String, Integer> items;
    private final Map<String, Double> preciosUnitarios;
    private double totalPrecio;

    private CarritoModel() {
        this.items = new LinkedHashMap<>();
        this.preciosUnitarios = new LinkedHashMap<>();
        this.totalPrecio = 0.0;
    }

    public static CarritoModel getInstance() {
        if (instancia == null) {
            instancia = new CarritoModel();
        }
        return instancia;
    }

    public void agregarItem(String nombre, double precio) {
        items.put(nombre, items.getOrDefault(nombre, 0) + 1);
        preciosUnitarios.put(nombre, precio);
        totalPrecio += precio;
    }

    public void removerItem(String nombre, double precio) {
        if (items.containsKey(nombre) && items.get(nombre) > 0) {
            items.put(nombre, items.get(nombre) - 1);
            totalPrecio -= precio;
            if (items.get(nombre) == 0) {
                items.remove(nombre);
                preciosUnitarios.remove(nombre);
            }
        }
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public double getPrecioUnitario(String nombre) {
        return preciosUnitarios.getOrDefault(nombre, 0.0);
    }

    public void limpiarCarrito() {
        items.clear();
        preciosUnitarios.clear();
        totalPrecio = 0.0;
    }
}