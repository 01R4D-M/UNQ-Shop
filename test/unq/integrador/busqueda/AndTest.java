package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import unq.integrador.productos.*;

class AndTest {
	private Criterio and;
	private Criterio unCriterio;
	private Criterio otroCriterio;
	private IProducto producto;
	
	@BeforeEach
	void setUp() {
		unCriterio = mock(Criterio.class);
		otroCriterio = mock(Criterio.class);
		producto = mock(Producto.class);
		
		and = new And(unCriterio,otroCriterio);
	}
	@Test
	void siLosDosCriteriosSonFalseDevuelveFalse() {
		when(unCriterio.cumple(producto)).thenReturn(false);
		when(otroCriterio.cumple(producto)).thenReturn(false);
		
		assertFalse(and.cumple(producto));
	}
	@Test
	void siLosDosCriteriosSonTrueDevuelveTrue() {
		when(unCriterio.cumple(producto)).thenReturn(true);
		when(otroCriterio.cumple(producto)).thenReturn(true);
		
		assertTrue(and.cumple(producto));
	}
	@Test
	void siUnCriterioEsFalseYOtroEsTrueDevuelveFalse() {
		when(unCriterio.cumple(producto)).thenReturn(false);
		when(otroCriterio.cumple(producto)).thenReturn(true);
		
		assertFalse(and.cumple(producto));
	}
	@Test
	void siUnCriterioEsTrueYOtroEsFalseDevuelveFalse() {
		when(unCriterio.cumple(producto)).thenReturn(true);
		when(otroCriterio.cumple(producto)).thenReturn(false);
		
		assertFalse(and.cumple(producto));
	}

}
