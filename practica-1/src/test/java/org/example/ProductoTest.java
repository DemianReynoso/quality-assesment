package org.example;

import org.example.carritoCompras.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Producto.
 */
public class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto(1, "Laptop", 999.99);
    }

    @Test
    void testConstructorValido() {
        assertEquals(1, producto.getId());
        assertEquals("Laptop", producto.getNombre());
        assertEquals(999.99, producto.getPrecio(), 0.01);
    }

    @Test
    void testConstructorConPrecioInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto(1, "Producto", 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Producto(1, "Producto", -10.5);
        });
    }

    @Test
    void testConstructorConNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto(1, null, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Producto(1, "", 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Producto(1, "   ", 100);
        });
    }

    @Test
    void testSetNombreValido() {
        producto.setNombre("MacBook Pro");
        assertEquals("MacBook Pro", producto.getNombre());
    }

    @Test
    void testSetNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombre(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            producto.setNombre("");
        });
    }

    @Test
    void testSetPrecioValido() {
        producto.setPrecio(1299.99);
        assertEquals(1299.99, producto.getPrecio(), 0.01);
    }

    @Test
    void testSetPrecioInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            producto.setPrecio(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            producto.setPrecio(-50);
        });
    }

    @Test
    void testEquals() {
        Producto producto1 = new Producto(1, "Laptop", 999.99);
        Producto producto2 = new Producto(1, "MacBook", 1299.99);
        Producto producto3 = new Producto(2, "Laptop", 999.99);

        assertEquals(producto1, producto2); // Mismo ID
        assertNotEquals(producto1, producto3); // Diferente ID
    }
}
