package org.example;

import org.example.carritoCompras.Carrito;
import org.example.carritoCompras.ItemCarrito;
import org.example.carritoCompras.Producto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para la clase Carrito.
 */
public class CarritoTest {

    private Carrito carrito;
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        producto1 = new Producto(1, "Laptop", 1000.00);
        producto2 = new Producto(2, "Mouse", 25.50);
        producto3 = new Producto(3, "Teclado", 75.00);
    }

    @Test
    void testCarritoVacioAlInicializar() {
        assertTrue(carrito.estaVacio());
        assertEquals(0, carrito.getCantidadItems());
        assertEquals(0, carrito.getCantidadTotalProductos());
        assertEquals(0.0, carrito.calcularTotal(), 0.01);
    }

    @Test
    void testAgregarProducto() {
        carrito.agregarProducto(producto1, 1);

        assertFalse(carrito.estaVacio());
        assertEquals(1, carrito.getCantidadItems());
        assertEquals(1, carrito.getCantidadTotalProductos());
        assertTrue(carrito.contieneProducto(producto1));
    }

    @Test
    void testAgregarProductoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            carrito.agregarProducto(null, 1);
        });
    }

    @Test
    void testAgregarProductoExistente() {
        carrito.agregarProducto(producto1, 2);
        carrito.agregarProducto(producto1, 3);

        assertEquals(1, carrito.getCantidadItems());
        assertEquals(5, carrito.getCantidadTotalProductos());

        // Verificar que la cantidad se incrementó correctamente
        ItemCarrito item = carrito.getItems().get(0);
        assertEquals(5, item.getCantidad());
    }

    @Test
    void testEliminarProducto() {
        carrito.agregarProducto(producto1, 2);
        carrito.agregarProducto(producto2, 1);

        assertTrue(carrito.eliminarProducto(producto1));
        assertEquals(1, carrito.getCantidadItems());
        assertFalse(carrito.contieneProducto(producto1));
        assertTrue(carrito.contieneProducto(producto2));
    }

    @Test
    void testModificarCantidad() {
        carrito.agregarProducto(producto1, 2);
        carrito.modificarCantidad(producto1, 5);

        ItemCarrito item = carrito.getItems().get(0);
        assertEquals(5, item.getCantidad());
        assertEquals(5, carrito.getCantidadTotalProductos());
    }

    @Test
    void testModificarCantidadInvalida() {
        carrito.agregarProducto(producto1, 2);

        assertThrows(IllegalArgumentException.class, () -> {
            carrito.modificarCantidad(producto1, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            carrito.modificarCantidad(producto1, -1);
        });
    }

    @Test
    void testModificarCantidadProductoNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            carrito.modificarCantidad(null, 5);
        });
    }

    @Test
    void testCalcularTotal() {
        carrito.agregarProducto(producto1, 1); // 1000.00
        carrito.agregarProducto(producto2, 2); // 51.00
        carrito.agregarProducto(producto3, 1); // 75.00

        double totalEsperado = 1000.00 + 51.00 + 75.00;
        assertEquals(totalEsperado, carrito.calcularTotal(), 0.01);
    }
    @Test
    void testGetCantidadTotalProductos() {
        carrito.agregarProducto(producto1, 2);
        carrito.agregarProducto(producto2, 3);
        carrito.agregarProducto(producto3, 1);

        assertEquals(6, carrito.getCantidadTotalProductos());
    }

    @Test
    void testContieneProducto() {
        carrito.agregarProducto(producto1, 1);

        assertTrue(carrito.contieneProducto(producto1));
        assertFalse(carrito.contieneProducto(producto2));
    }
}
