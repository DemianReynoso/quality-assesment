package org.example.carritoCompras;

/**
 * Clase que representa un producto en el sistema de carrito de compras.
 * Contiene información básica del producto como ID, nombre y precio.
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;

    /**
     * Constructor para crear un nuevo producto.
     * @param id Identificador único del producto
     * @param nombre Nombre del producto
     * @param precio Precio del producto (debe ser mayor a 0)
     * @throws IllegalArgumentException si el precio es negativo o cero
     */
    public Producto(int id, String nombre, double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    // Setters
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        this.precio = precio;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return id == producto.id;
    }
}
