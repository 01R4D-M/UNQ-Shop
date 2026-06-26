package unq.integrador.producto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import unq.integrador.productos.Paquete;
import unq.integrador.productos.Producto;
import unq.integrador.productos.ProductoIndividual;

import static org.mockito.Mockito.*;


class PaqueteTest {
	
	private Paquete paquete;
	private Paquete paqueteHogar;
	
	private Producto auriculares;
	private Producto parlantes;
	private Producto plancha;
	private Producto aspiradora;
	private Producto lavarropas;
	
	@BeforeEach
	void setUp() {
		paquete = new Paquete ("Musica", "Auriculares + Parlantes", "Audio", 10);
		
		
		
		paqueteHogar = new Paquete("Hogareño", "Plancha + Aspiradora + Lavarropas", "Hogar", 25);
		
		auriculares = mock(ProductoIndividual.class);
		parlantes = mock(ProductoIndividual.class);
		plancha = mock(ProductoIndividual.class);
		aspiradora = mock(ProductoIndividual.class);
		lavarropas = mock(ProductoIndividual.class);
	
		paqueteHogar.agregarProducto(plancha);
		paqueteHogar.agregarProducto(aspiradora);
		paqueteHogar.agregarProducto(lavarropas);
	}
	
	@Test
	void testInicialzacionConstructor() {
		assertEquals("Audio", paquete.getNombre());
		assertEquals("Auriculares + Parlantes", paquete.getDescripcion());
		assertEquals(10, paquete.getPorcentajeDescuento());
	}
	
	@Test
	void testElDescuentoDebeSerMenorOIgualACien() {
		assertThrows(	IllegalArgumentException.class, 
						() -> new Paquete("Gastronomico", "Licuadora + Tostadora", "Cocina", 101));
	}
	
	@Test
	void testElDescuentoDebeSerMayorOIgualACero() {
		assertThrows(	IllegalArgumentException.class, 
						() -> new Paquete("Gastronomico", "Licuadora + Tostadora", "Cocina", -1));
	}
	
	@Test 
	void testSePuedenAgregarProductosAUnPaquete() {
		paquete.agregarProducto(auriculares);
		paquete.agregarProducto(parlantes);
		assertTrue(paquete.contieneProducto(auriculares) && paquete.contieneProducto(parlantes));
	}
	
	@Test 
	void testSePuedenEliminarProductosDeUnPaquete() {
		paquete.agregarProducto(auriculares);
		paquete.eliminarProducto(auriculares);
		assertFalse(paquete.contieneProducto(auriculares));
	}
	
	@Test
	void testUnPaqueteCalculaSuPrecioBaseDeFormaValida() {
		when(auriculares.getPrecioBase()).thenReturn(600.0);
		when(parlantes.getPrecioBase()).thenReturn(400.0);
		paquete.agregarProducto(auriculares);
		paquete.agregarProducto(parlantes);
		assertEquals(1000, paquete.getPrecioBase());
		
	}
	
	@Test
	void testUnPaqueteCalculaSuPrecioFinalValidamente() {
		
		when(auriculares.getPrecioFinal()).thenReturn(600.0);
		when(parlantes.getPrecioFinal()).thenReturn(400.0);
		paquete.agregarProducto(auriculares);
		paquete.agregarProducto(parlantes);
		
		assertEquals(900, paquete.getPrecioFinal());
		
	}
	
	@Test
	void testCalculoPrecioBaseConPaquetesAnidados() {
		when(plancha.getPrecioBase()).thenReturn(400.0);
		when(aspiradora.getPrecioBase()).thenReturn(1000.0);
		when(lavarropas.getPrecioBase()).thenReturn(3600.0);
		when(auriculares.getPrecioBase()).thenReturn(500.0);
		when(parlantes.getPrecioBase()).thenReturn(500.0);
		
		paquete.agregarProducto(paqueteHogar);
		paquete.agregarProducto(auriculares);
		paquete.agregarProducto(parlantes);
		
		assertEquals(6000, paquete.getPrecioBase());
	}
	
	@Test 
	void testCalculoPrecioFinalConPaquetesAnidados() {
		
		when(plancha.getPrecioFinal()).thenReturn(400.0);
		when(aspiradora.getPrecioFinal()).thenReturn(1000.0);
		when(lavarropas.getPrecioFinal()).thenReturn(3600.0);
		when(auriculares.getPrecioFinal()).thenReturn(500.0);
		when(parlantes.getPrecioFinal()).thenReturn(500.0);
		
		paquete.agregarProducto(paqueteHogar);
		paquete.agregarProducto(auriculares);
		paquete.agregarProducto(parlantes);
		
		// paqueteHogar = 5000 - 25% = 3750
		// paquete = (3750 + 1000) - 10% = 4275
		
		assertEquals(4275, paquete.getPrecioFinal());
	}
}
