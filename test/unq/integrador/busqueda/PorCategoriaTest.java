package unq.integrador.busqueda;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


import unq.integrador.productos.*;

class PorCategoriaTest {

	private Criterio porCategoria;
	private IProducto producto;
	
	@BeforeEach
	void setUp() {
		porCategoria = new PorCategoria("Hogar");
		producto = mock(Producto.class);
	}
	
	@Test
	void lasMayusculasNoInfluyenEnLaEvaluacion() {
		when(producto.getCategoria()).thenReturn("HOgAr");
		assertTrue(porCategoria.cumple(producto));
	}
	@Test 
	void devuelveTrueConLasCategoriasSonExactamenteIguales() {
		when(producto.getCategoria()).thenReturn("Hogar");
		assertTrue(porCategoria.cumple(producto));
	}
	@Test
	void siLasCategoriasSonDistintasDevuelveFalse() {
		when(producto.getCategoria()).thenReturn("Electronica");
		assertFalse(porCategoria.cumple(producto));
	}
	@Test
	void siLaCategoriaEsVaciaNoCumple() {
		when(producto.getCategoria()).thenReturn("");
		assertFalse(porCategoria.cumple(producto));
	}
}
