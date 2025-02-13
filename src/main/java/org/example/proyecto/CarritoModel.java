package org.example.proyecto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Alberto
 * @vesion 1.0
 * @since 10/02/2025
 **/

/**
 * Modelo que gestiona el carrito de compras
 * Permite agregar, remover y mostrar los productos del carrito
 * y calcular un total del pedido
 */
public class CarritoModel {
    private static CarritoModel instancia;
    private final Map<String, Integer> items;
    private final Map<String, Double> preciosUnitarios;
    private double totalPrecio;

    /**
     * Constructor de la clase
     * Inicializa las estructuras de datos para los productos y precios
     */
    private CarritoModel() {
        this.items = new LinkedHashMap<>();
        this.preciosUnitarios = new LinkedHashMap<>();
        this.totalPrecio = 0.0;
    }

    /**
     * Obtiene la instancia del carrito
     * @return la instancia del carrito
     */

    public static CarritoModel getInstance() {
        if (instancia == null) {
            instancia = new CarritoModel();
        }
        return instancia;
    }

    /**
     * Agrega un nuevo elemento al carrito
     * @param nombre  Nombre del producto
     * @param precio Precio del producto
     */
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

    /**
     * Obtiene los productos del carrito con sus cantidades
     *
     * @return un map con los productos y cantidades
     */

    public Map<String, Integer> getItems() {
        return items;
    }

    /**
     * Precio total del carrito
     * @return El precio total
     */
    public double getTotalPrecio() {
        return totalPrecio;
    }

    /**
     * Obtiene le precio unitario de los productos
     * @param nombre del producto
     * @return Precio unitario o 0.0 si no existe
     */

    public double getPrecioUnitario(String nombre) {
        return preciosUnitarios.getOrDefault(nombre, 0.0);
    }

    /**
     * Vacia el carrito eliminando los productos y reiniciando el total
     */

    public void limpiarCarrito() {
        items.clear();
        preciosUnitarios.clear();
        totalPrecio = 0.0;
    }
}