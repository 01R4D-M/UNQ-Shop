package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import unq.integrador.productos.*;

class PorPrecioMaximoTest {

	private Criterio porPrecioMaximo;
	private IProducto producto;
	
	@BeforeEach
	void setUp() {
		porPrecioMaximo = new PorPrecioMaximo(1000);
		producto = mock(IProducto.class);
	}
	
	@Test
	void siElPrecioEsMenorAlMaximoDevuelveTrue() {
		double unPrecio = 900;
		when(producto.getPrecioBase()).thenReturn(unPrecio);
		
		assertTrue(porPrecioMaximo.cumple(producto));
		
	}
	@Test
	void siElPrecioEsIgualDevuelveTrue() {
		double unPrecio = 1000;
		when(producto.getPrecioBase()).thenReturn(unPrecio);
		
		assertTrue(porPrecioMaximo.cumple(producto));
		
	}
	@Test
	void siElPrecioEsMayorDevuelveFalse() {
		double unPrecio = 1001;
		when(producto.getPrecioBase()).thenReturn(unPrecio);
		
		assertFalse(porPrecioMaximo.cumple(producto));
		
	}
	@Test
	void elCriterioInteractuaConElProductoPidiendoleSuPrecioBase() {
		double unPrecio = 900;
		when(producto.getPrecioBase()).thenReturn(unPrecio);
		
		assertTrue(porPrecioMaximo.cumple(producto));
		verify(producto).getPrecioBase();
	}

}
