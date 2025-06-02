package org.example;

import org.example.carritoCompras.ItemCarrito;
import org.example.carritoCompras.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase ItemCarrito.
 */
public class ItemCarritoTest {

    private Producto producto;
    private ItemCarrito item;

    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Mouse", 25.99);
        item = new ItemCarrito(producto, 2);
    }

    @Test
    void testConstructorValido() {
        assertEquals(producto, item.getProducto());
        assertEquals(2, item.getCantidad());
    }

    @Test
    void testConstructorConProductoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ItemCarrito(null, 1);
        });
    }

    @Test
    void testConstructorConCantidadInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ItemCarrito(producto, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new ItemCarrito(producto, -1);
        });
    }

    @Test
    void testCalcularSubtotal() {
        double subtotalEsperado = 25.99 * 2;
        assertEquals(subtotalEsperado, item.getSubtotal(), 0.01);
    }

    @Test
    void testSetCantidadValida() {
        item.setCantidad(5);
        assertEquals(5, item.getCantidad());
        assertEquals(25.99 * 5, item.getSubtotal(), 0.01);
    }

    @Test
    void testSetCantidadInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            item.setCantidad(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            item.setCantidad(-1);
        });
    }

    @Test
    void testIncrementarCantidad() {
        item.incrementarCantidad(3);
        assertEquals(5, item.getCantidad());
        assertEquals(25.99 * 5, item.getSubtotal(), 0.01);
    }

    @Test
    void testIncrementarCantidadInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            item.incrementarCantidad(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            item.incrementarCantidad(-1);
        });
    }

    @Test
    void testSubtotalConDiferentesCantidades() {
        ItemCarrito item1 = new ItemCarrito(producto, 1);
        ItemCarrito item2 = new ItemCarrito(producto, 3);

        assertEquals(25.99, item1.getSubtotal(), 0.01);
        assertEquals(77.97, item2.getSubtotal(), 0.01);
    }
}

