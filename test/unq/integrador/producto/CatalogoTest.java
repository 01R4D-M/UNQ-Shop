package unq.integrador.producto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.List;

import unq.integrador.productos.*;
import unq.integrador.busqueda.*;


class CatalogoTest {
	
	private Catalogo catalogo;
	private Producto productoMock;
	
	@BeforeEach
	void setUp() {
		catalogo = new Catalogo();
		productoMock = mock(Producto.class);
	}
	
	@Test 
	void losProductosSeAgreganAlCatalogoDeFormaValida() {
		catalogo.agregarProducto(productoMock);
		
		assertTrue(catalogo.tieneProducto(productoMock));
	}
	@Test
	void losProductoSeEliminanValidamente() {
		catalogo.agregarProducto(productoMock);
		catalogo.eliminarProducto(productoMock);
		
		assertFalse(catalogo.tieneProducto(productoMock));
	}
	@Test
	void siUnProductoYaExisteEnElCatalogoNoSeAgregaOtraVez() {
		catalogo.agregarProducto(productoMock);
		catalogo.agregarProducto(productoMock);
		
		assertEquals(1, catalogo.ocurrenciasDe(productoMock));
	}
	@Test
	void siSeIntentaEliminarUnProductoQueNoEstaEnElCatalogo_ElCatalogoNoHaceNada() {
		Producto otroProducto = mock(Producto.class);
		catalogo.agregarProducto(productoMock);
		catalogo.eliminarProducto(otroProducto);
		
		assertTrue(catalogo.tieneProducto(productoMock));
		assertFalse(catalogo.tieneProducto(otroProducto));
	}
	@Test 
	void seRealizanBusquedasEnUnCatalogoEnFuncionDeUnCriterioDeFormaValida() {
		Criterio criterioMock = mock(Criterio.class);
		Producto productoMock2 = mock(Producto.class);
		Producto productoMock3 = mock(Producto.class);
		Producto productoMock4 = mock(Producto.class);
		Producto productoMock5 = mock(Producto.class);
		
		catalogo.agregarProducto(productoMock);
		catalogo.agregarProducto(productoMock2);
		catalogo.agregarProducto(productoMock3);
		catalogo.agregarProducto(productoMock4);
		catalogo.agregarProducto(productoMock5);
		
		when(criterioMock.cumple(productoMock)).thenReturn(true);
		when(criterioMock.cumple(productoMock4)).thenReturn(true);
		
		when(criterioMock.cumple(productoMock2)).thenReturn(false);
		when(criterioMock.cumple(productoMock3)).thenReturn(false);
		when(criterioMock.cumple(productoMock5)).thenReturn(false);
		
		assertTrue(catalogo.buscar(criterioMock)
												.containsAll(List.of(productoMock,productoMock4)));
		
	}
}
