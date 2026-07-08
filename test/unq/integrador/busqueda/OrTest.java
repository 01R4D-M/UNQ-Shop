package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import unq.integrador.productos.*;



class OrTest {
	private Criterio or;
	private Criterio unCriterio;
	private Criterio otroCriterio;
	private IProducto producto;
	
	
	@BeforeEach
	void setUp() {
		unCriterio = mock(Criterio.class);
		otroCriterio = mock(Criterio.class);
		producto = mock(Producto.class);
		
		or = new Or(unCriterio,otroCriterio);
	}
	@Test
	void siAmbosCriteriosSonTrueDevuelveTrue(){
		when(unCriterio.cumple(producto)).thenReturn(true);
		when(otroCriterio.cumple(producto)).thenReturn(true);
		
		assertTrue(or.cumple(producto));
	}
	@Test
	void siUnCriterioEsTrueYOtroEsFalseDevuelveTrue() {
		when(unCriterio.cumple(producto)).thenReturn(true);
		when(otroCriterio.cumple(producto)).thenReturn(false);
		
		assertTrue(or.cumple(producto));
	}
	@Test
	void siUnCriterioEsFalseYOtroEsTrueDevuelveTrue() {
		when(unCriterio.cumple(producto)).thenReturn(false);
		when(otroCriterio.cumple(producto)).thenReturn(true);
		
		assertTrue(or.cumple(producto));
	}
	@Test
	void siAmbosCriteriosSonFalseDevuelveFalse() {
		when(unCriterio.cumple(producto)).thenReturn(false);
		when(otroCriterio.cumple(producto)).thenReturn(false);
		
		assertFalse(or.cumple(producto));
	}
}
