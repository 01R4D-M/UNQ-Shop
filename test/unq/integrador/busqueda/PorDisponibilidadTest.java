package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import unq.integrador.productos.*;

class PorDisponibilidadTest {
	private Criterio porDisponibilidad;
	private IProducto producto;
	private UNQShop shop;
	
	@BeforeEach
	void setUp() {
		shop = mock(UNQShop.class);
		producto = mock(IProducto.class);
		
		porDisponibilidad = new PorDisponibilidadEn(shop);
	}
	
	@Test
	void siHayStockDeUnProductoDevuelveTrue() {
		when(shop.hayStockDe(producto)).thenReturn(true);
		
		assertTrue(porDisponibilidad.cumple(producto));
		verify(shop).hayStockDe(producto);
	}
	@Test
	void siNoHayStockDeUnProductoDevuelveFalse() {
		when(shop.hayStockDe(producto)).thenReturn(false);
		
		assertFalse(porDisponibilidad.cumple(producto));
		verify(shop).hayStockDe(producto);
	}
}
