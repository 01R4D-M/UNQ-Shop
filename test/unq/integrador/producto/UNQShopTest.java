package unq.integrador.producto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import unq.integrador.productos.*;

import static org.mockito.Mockito.*;
import unq.integrador.pedido.*;

public class UNQShopTest {
	private UNQShop shop;
	private Producto producto;
	
	@BeforeEach
	void setUp() {
		shop = new UNQShop(new Deposito());
		producto = mock(Producto.class);
	}
	
	@Test 
	void testSeIncrementaElStockDeUnProductoExistenteDeFormaValida() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shop.incrementarStock(producto, 10);
		
		assertEquals(20, shop.getStockDe(producto));
		assertTrue(shop.contieneProductoEnCatalogo(producto));
	}
	@Test 
	void testSiElProductoNoEstaEnElCatalogo_NoSePuedeIncrementarElStock() {
		shop.incrementarStock(producto, 10);
		assertEquals(0, shop.getStockDe(producto));
		assertFalse(shop.contieneProductoEnCatalogo(producto));
	}
	@Test
	void testNoSePuedeAgregarDosVecesElMismoProductoAlCatalogo(){
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		assertEquals(10, shop.getStockDe(producto));
	}
	@Test
	void testSiElStockAgregadoEsCeroNoHayStockDeEseProducto() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 0);
		assertFalse(shop.hayStockDe(producto));
	}
	@Test
	void testSiElStockAgregadoEsMayorACero_HayStockDeEseProducto() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 3);
		assertTrue(shop.hayStockDe(producto));
		assertEquals(3, shop.getStockDe(producto));
	}
	@Test
	void testElStockDeUnProductoSeDecrementaDeFormaValida() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shop.decrementarStock(producto, 5);
		assertEquals(5, shop.getStockDe(producto));
	}
	@Test
	void testSiNoHaySuficienteStockEsteNoSeDecrementa() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 5);
		shop.decrementarStock(producto, 10);
		assertEquals(5, shop.getStockDe(producto));
	}
	@Test
	void testSiSeQuiereDecrementarUnaCantidadDeStockNegativa_NoHaceNada() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shop.decrementarStock(producto, -5);
		assertEquals(10, shop.getStockDe(producto));
	}
	@Test
	void testSiElStockDeUnProductoEsCeroNoEstaDisponible() {
		shop.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 0);
		assertFalse(shop.hayStockDe(producto));
	}
	@Test
	void testLosPedidosSeAgreganDeFormaValida() {
		IPedido pedido = mock(IPedido.class);
		shop.setPedido(pedido);
		assertTrue(shop.getPedidos().contains(pedido));
	}
}
