package org.example.carritoCompras;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase que representa un carrito de compras.
 * Permite agregar, eliminar y modificar productos, así como calcular el total.
 */
public class Carrito {
    private List<ItemCarrito> items;

    /**
     * Constructor que inicializa un carrito vacío.
     */
    public Carrito() {
        this.items = new ArrayList<>();
    }

    /**
     * Agrega un producto al carrito con la cantidad especificada.
     * Si el producto ya existe, incrementa su cantidad.
     * @param producto El producto a agregar
     * @param cantidad La cantidad del producto
     * @throws IllegalArgumentException si el producto es null o la cantidad es menor o igual a 0
     */
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        Optional<ItemCarrito> itemExistente = buscarItemPorProducto(producto);
        if (itemExistente.isPresent()) {
            itemExistente.get().incrementarCantidad(cantidad);
        } else {
            items.add(new ItemCarrito(producto, cantidad));
        }
    }

    /**
     * Elimina completamente un producto del carrito.
     * @param producto El producto a eliminar
     * @throws IllegalArgumentException si el producto es null
     * @return true si el producto fue eliminado, false si no estaba en el carrito
     */
    public boolean eliminarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        return items.removeIf(item -> item.getProducto().equals(producto));
    }

    /**
     * Modifica la cantidad de un producto en el carrito.
     * @param producto El producto a modificar
     * @param nuevaCantidad La nueva cantidad (debe ser mayor a 0)
     * @throws IllegalArgumentException si el producto es null, la cantidad es menor o igual a 0,
     *         o el producto no está en el carrito
     */
    public void modificarCantidad(Producto producto, int nuevaCantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (nuevaCantidad <= 0) {
            throw new IllegalArgumentException("La nueva cantidad debe ser mayor a 0");
        }

        Optional<ItemCarrito> item = buscarItemPorProducto(producto);
        if (item.isPresent()) {
            item.get().setCantidad(nuevaCantidad);
        } else {
            throw new IllegalArgumentException("El producto no está en el carrito");
        }
    }

    /**
     * Calcula el total de la compra sumando todos los subtotales.
     * @return El total de la compra
     */
    public double calcularTotal() {
        return items.stream()
                .mapToDouble(ItemCarrito::getSubtotal)
                .sum();
    }

    /**
     * Obtiene la cantidad total de items en el carrito.
     * @return El número total de items diferentes en el carrito
     */
    public int getCantidadItems() {
        return items.size();
    }

    /**
     * Obtiene la cantidad total de productos en el carrito (suma de todas las cantidades).
     * @return La cantidad total de productos
     */
    public int getCantidadTotalProductos() {
        return items.stream()
                .mapToInt(ItemCarrito::getCantidad)
                .sum();
    }

    /**
     * Verifica si el carrito está vacío.
     * @return true si el carrito está vacío, false en caso contrario
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Obtiene una copia de la lista de items del carrito.
     * @return Lista de items del carrito
     */
    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Busca un item en el carrito por producto.
     * @param producto El producto a buscar
     * @return Optional con el item si se encuentra, Optional.empty() si no
     */
    private Optional<ItemCarrito> buscarItemPorProducto(Producto producto) {
        return items.stream()
                .filter(item -> item.getProducto().equals(producto))
                .findFirst();
    }

    /**
     * Verifica si un producto está en el carrito.
     * @param producto El producto a verificar
     * @return true si el producto está en el carrito, false en caso contrario
     */
    public boolean contieneProducto(Producto producto) {
        return buscarItemPorProducto(producto).isPresent();
    }
}
