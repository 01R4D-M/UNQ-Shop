package unq.integrador;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class ProductoIndividualTest {

	private ProductoIndividual producto;
	
	@BeforeEach
	void setUp() {
		producto = new ProductoIndividual(
											"television",
											"tele 50 pulgadas",
											400,
											"Samsung",
											"Electronica",
											3,
											5000
											);
	}
	
	@Test
	void testInicializacionValoresConstructor() {
		assertEquals("television", producto.getNombre());
		assertEquals("tele 50 pulgadas", producto.getDescripcion());
		assertEquals(400, producto.getSku());
		assertEquals("Samsung", producto.getMarca());
		assertEquals("Electronica", producto.getCategoria());
		assertEquals(3, producto.getPeso());
		assertEquals(5000, producto.getPrecioFinal());
		
	}
	
	@Test 
	void testLosAtributosDinamicosSeAgreganCorrectamente() {
		producto.setAtributo("ancho", 45);
		assertEquals(45, producto.getAtributo("ancho"));
	}
	
	@Test
	void testLasMayusculasNoInfluyenEnLaBusquedaDeAtributos() {
		producto.setAtributo("LARgo", 10);
		assertEquals(10, producto.getAtributo("larGO"));
	}
	
	@Test 
	void testValidacionAtributosObligatorios() {
		assertTrue(producto.validarAtributosObligatorios());
	}
	
	
}
