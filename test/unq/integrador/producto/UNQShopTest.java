package unq.integrador.producto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import unq.integrador.productos.*;

import static org.mockito.Mockito.*;
import unq.integrador.pedido.*;

public class UNQShopTest {
	private UNQShop shopConDepositoMock;
	private UNQShop shopConDepositoReal;
	private Producto producto;
	private Deposito depositoMock;
	private Deposito depositoReal;
	
	@BeforeEach
	void setUp() {
		depositoMock = mock(Deposito.class);
		depositoReal = new Deposito();
		shopConDepositoMock = new UNQShop(depositoMock);
		shopConDepositoReal = new UNQShop(depositoReal);
		producto = mock(Producto.class);
	}
	
	@Test
	void testSeAgreganProductosAlCatalogoDeFormaValida() {
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 5);
		assertTrue(shopConDepositoMock.contieneProductoEnCatalogo(producto));
	}
	@Test 
	void testSeIncrementaElStockDeUnProductoQueEstaEnElCatalogoDeFormaValida() {
		shopConDepositoReal.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shopConDepositoReal.incrementarStock(producto, 10);
		
		assertEquals(20, shopConDepositoReal.getStockDe(producto));
		assertTrue(shopConDepositoReal.contieneProductoEnCatalogo(producto));
	}
	@Test 
	void testSiElProductoNoEstaEnElCatalogo_NoIncrementaElStock() {
		shopConDepositoReal.incrementarStock(producto, 10);
		assertEquals(0, shopConDepositoReal.getStockDe(producto));
		assertFalse(shopConDepositoReal.contieneProductoEnCatalogo(producto));
	}
	@Test
	void testSiElProductoNoEstaEnElCatalogoElShopNoInteractuaConElDeposito(){
		shopConDepositoMock.incrementarStock(producto, 10);
		verifyNoInteractions(depositoMock);
	}
	@Test
	void testNoSePuedeAgregarDosVecesElMismoProductoAlCatalogo(){
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		assertEquals(1, shopConDepositoMock.ocurrenciasDeEnCatalogo(producto));
	}
	@Test
	void testElShopInteractuaConElDepositoAlAgregarStock() {
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		verify(depositoMock).incrementarStock(producto, 10);
	}
	@Test
	void testElShopInteractuaConElDepositoAlDecrementarStock() {
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shopConDepositoMock.decrementarStock(producto, 5);
		verify(depositoMock).decrementarStock(producto, 5);
	}
	@Test
	void testAunqueSeIntenteDecrementarMasStockDelDisponibleElShopInteractuaConElDeposito() {
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 5);
		shopConDepositoMock.decrementarStock(producto, 10);
		verify(depositoMock).decrementarStock(producto, 10);
	}
	@Test
	void testComoElShopNoConoceLaLogicaSobreManejoDeStock_AunqueLaOperacionSeaInvalidaElShopInteractuaConElDeposito() {
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 10);
		shopConDepositoMock.decrementarStock(producto, -5);
		verify(depositoMock).decrementarStock(producto, -5);
	}
	@Test
	void testLosPedidosSeAgreganDeFormaValida() {
		IPedido pedido = mock(IPedido.class);
		shopConDepositoMock.setPedido(pedido);
		assertTrue(shopConDepositoMock.getPedidos().contains(pedido));
	}
}
