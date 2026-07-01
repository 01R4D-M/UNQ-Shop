package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import unq.integrador.productos.*;

class NotTest {

	private Criterio not;
	private Criterio unCriterio;
	private IProducto producto;
	
	@BeforeEach
	void setUp() {
		unCriterio = mock(Criterio.class);
		producto = mock(Producto.class);
		
		not = new Not(unCriterio);
	}
	@Test
	void siUnCriterioEsTrueDevuelveFalse() {
		when(unCriterio.cumple(producto)).thenReturn(true);
		
		assertFalse(not.cumple(producto));
	}
	@Test 
	void siUnCriterioEsFalseDevuelveTrue() {
		when(unCriterio.cumple(producto)).thenReturn(false);
		
		assertTrue(not.cumple(producto));
	}

}
