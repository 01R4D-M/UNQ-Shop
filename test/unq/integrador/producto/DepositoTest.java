package unq.integrador.producto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import unq.integrador.productos.*;

import static org.mockito.Mockito.*;

class DepositoTest {
	
	private Deposito deposito;
	private Producto producto;
	
	@BeforeEach
	void setUp() {
		deposito = new Deposito();
		producto = mock(Producto.class);
	}
	
	@Test
	void testCuandoSeCreaUnDepositoNuevo_NoTieneStockDeNingunProducto() {
		assertEquals(0, deposito.getStockDe(producto));
		assertFalse(deposito.hayStockDe(producto));
	}
	@Test
	void testSiElStockAgregadoEsCeroNoHayStockDeEseProducto() {
		deposito.incrementarStock(producto, 0);
		assertFalse(deposito.hayStockDe(producto));
	}
	@Test
	void testSiElStockAgregadoEsMayorACero_HayStockDeEseProducto() {
		deposito.incrementarStock(producto, 3);
		assertTrue(deposito.hayStockDe(producto));
		assertEquals(3, deposito.getStockDe(producto));
	}
	@Test
	void testElStockDeUnProductoSeDecrementaDeFormaValida() {
		deposito.incrementarStock(producto, 10);
		deposito.decrementarStock(producto, 5);
		assertEquals(5, deposito.getStockDe(producto));
	}
	@Test
	void testSiNoHaySuficienteStockEsteNoSeDecrementa() {
		deposito.incrementarStock(producto, 5);
		deposito.decrementarStock(producto, 10);
		assertEquals(5, deposito.getStockDe(producto));
	}
	@Test
	void testSiSeQuiereDecrementarUnaCantidadDeStockNegativa_NoHaceNada() {
		deposito.incrementarStock(producto, 10);
		deposito.decrementarStock(producto, -5);
		assertEquals(10, deposito.getStockDe(producto));
	}
	@Test
	void testSiSeIntentaIncrementarUnaCAntidadDeStockNegativa_NoHaceNada() {
		deposito.incrementarStock(producto, -3);
		assertEquals(0, deposito.getStockDe(producto));
	}
	
}
