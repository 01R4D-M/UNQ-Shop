package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import unq.integrador.productos.*;

class PorNombreTest {

	private Criterio porNombre;
	private IProducto producto;
	
	@BeforeEach
	void setUp() {
		porNombre = new PorNombre("Celular");
		producto = mock(IProducto.class);
		
	}
	@Test
	void siElNombreEsExactamenteIgualEntonesCumple() {
		when(producto.getNombre()).thenReturn("Celular");
		
		assertTrue(porNombre.cumple(producto));
		verify(producto).getNombre();
	}
	@Test
	void devuelveTrueSinImportarLasMayusculas() {
		when(producto.getNombre()).thenReturn("CeLULar");
		
		assertTrue(porNombre.cumple(producto));
		verify(producto).getNombre();
	}
	@Test
	void siElNombreEsDiferenteNoCumple() {
		when(producto.getNombre()).thenReturn("Computadora");
		
		assertFalse(porNombre.cumple(producto));
		verify(producto).getNombre();
	}

}
