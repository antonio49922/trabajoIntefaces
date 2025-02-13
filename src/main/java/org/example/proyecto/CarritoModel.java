package org.example.proyecto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Alberto
 * @version 1.0
 * @since 10/02/2025
 **/

/**
 * Modelo que gestiona el carrito de compras.
 * Permite agregar, remover y mostrar los productos del carrito
 * y calcular el total del pedido.
 */
public class CarritoModel {
    private static CarritoModel instancia;
    private final Map<String, Integer> items;
    private final Map<String, Double> preciosUnitarios;
    private double totalPrecio;

    /**
     * Constructor privado para el patrón Singleton.
     * Inicializa las estructuras de datos para los productos y precios.
     */
    private CarritoModel() {
        this.items = new LinkedHashMap<>();
        this.preciosUnitarios = new LinkedHashMap<>();
        this.totalPrecio = 0.0;
    }

    /**
     * Obtiene la única instancia de CarritoModel.
     * Implementa Singleton con seguridad en concurrencia.
     * @return la instancia única del carrito.
     */
    public static synchronized CarritoModel getInstance() {
        if (instancia == null) {
            instancia = new CarritoModel();
        }
        return instancia;
    }

    /**
     * Agrega un nuevo elemento al carrito.
     * @param nombre  Nombre del producto.
     * @param precio Precio del producto.
     */
    public void agregarItem(String nombre, double precio) {
        if (nombre == null || nombre.isEmpty() || precio < 0) {
            System.out.println("Error: Producto inválido.");
            return;
        }
        items.put(nombre, items.getOrDefault(nombre, 0) + 1);
        preciosUnitarios.put(nombre, precio);
        totalPrecio += precio;
        System.out.println("Producto añadido: " + nombre + " - Precio: " + precio);
        mostrarCarrito();
    }

    /**
     * Remueve un elemento del carrito.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public void removerItem(String nombre, double precio) {
        if (nombre == null || !items.containsKey(nombre) || items.get(nombre) <= 0) {
            System.out.println("Error: Producto no encontrado en el carrito.");
            return;
        }
        items.put(nombre, items.get(nombre) - 1);
        totalPrecio -= precio;
        if (items.get(nombre) == 0) {
            items.remove(nombre);
            preciosUnitarios.remove(nombre);
        }
        System.out.println("Producto eliminado: " + nombre);
        mostrarCarrito();
    }

    /**
     * Obtiene los productos del carrito con sus cantidades.
     * @return un Map con los productos y cantidades.
     */
    public Map<String, Integer> getItems() {
        return items;
    }

    /**
     * Obtiene el precio total del carrito.
     * @return El precio total.
     */
    public double getTotalPrecio() {
        return Math.max(totalPrecio, 0.0); // Evitar valores negativos
    }

    /**
     * Obtiene el precio unitario de un producto.
     * @param nombre del producto.
     * @return Precio unitario o 0.0 si no existe.
     */
    public double getPrecioUnitario(String nombre) {
        return preciosUnitarios.getOrDefault(nombre, 0.0);
    }

    /**
     * Vacía el carrito eliminando todos los productos y reiniciando el total.
     */
    public void limpiarCarrito() {
        items.clear();
        preciosUnitarios.clear();
        totalPrecio = 0.0;
        System.out.println("El carrito ha sido vaciado.");
    }

    /**
     * Metodo de depuración para mostrar el contenido actual del carrito.
     */
    public void mostrarCarrito() {
        System.out.println("Carrito actual:");
        for (Map.Entry<String, Integer> item : items.entrySet()) {
            System.out.println("- " + item.getKey() + " x" + item.getValue() + " (Precio unitario: " + getPrecioUnitario(item.getKey()) + "€)");
        }
        System.out.println("Total: " + getTotalPrecio() + "€");
    }
}
