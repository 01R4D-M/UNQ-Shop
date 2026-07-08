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
	private Catalogo catalogoMock;
	
	@BeforeEach
	void setUp() {
		depositoMock = mock(Deposito.class);
		depositoReal = new Deposito();
		catalogoMock = mock(Catalogo.class);
		shopConDepositoMock = new UNQShop(depositoMock, catalogoMock);
		shopConDepositoReal = new UNQShop(depositoReal, catalogoMock);
		producto = mock(Producto.class);
	}
	
	@Test
	void testElShopInteractuaConElCatalogoYElDepositoAlAgregarUnNuevoProductoAlCatalogo() {
		when(catalogoMock.tieneProducto(producto)).thenReturn(true);
		shopConDepositoMock.agregarProductoAlCatalogoYSetearStockInicialEn(producto, 5);
		
		verify(catalogoMock).agregarProducto(producto);
		verify(depositoMock).incrementarStock(producto, 5);
	}
	@Test 
	void testSeIncrementaElStockDeUnProductoQueEstaEnElCatalogoDeFormaValida() {
		when(catalogoMock.tieneProducto(producto)).thenReturn(true);
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
	void testElShopInteractuaConElDepositoAlAgregarStock() {
		when(catalogoMock.tieneProducto(producto)).thenReturn(true);
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
	void testComoElShopNoConoceLaLogicaSobreManejoDeStock_AunqueSeIntenteDecrementarMasStockDelDisponibleElShopInteractuaConElDeposito() {
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
