package org.example.carritoCompras;

/**
 * Clase que representa un item dentro del carrito de compras.
 * Contiene un producto y la cantidad de ese producto en el carrito.
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor para crear un nuevo item del carrito.
     * @param producto El producto a agregar
     * @param cantidad La cantidad del producto (debe ser mayor a 0)
     * @throws IllegalArgumentException si la cantidad es menor o igual a 0 o el producto es null
     */
    public ItemCarrito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Calcula el subtotal del item (precio unitario × cantidad).
     * @return El subtotal del item
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    // Getters
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Setter para cantidad con validación
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.cantidad = cantidad;
    }

    /**
     * Incrementa la cantidad del item en la cantidad especificada.
     * @param cantidad Cantidad a incrementar (debe ser mayor a 0)
     */
    public void incrementarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a incrementar debe ser mayor a 0");
        }
        this.cantidad += cantidad;
    }

    @Override
    public String toString() {
        return String.format("ItemCarrito{producto=%s, cantidad=%d, subtotal=%.2f}",
                producto, cantidad, getSubtotal());
    }
}
