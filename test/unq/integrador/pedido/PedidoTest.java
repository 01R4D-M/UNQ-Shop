package unq.integrador.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.productos.Deposito;
import unq.integrador.productos.IProducto;
import unq.integrador.envio.IEnvio;
import unq.integrador.pago.MetodoDePago;
import unq.integrador.pedido.state.PedidoState;

public class PedidoTest {
    private IPedido pedido;
    private Deposito deposito;
    private IProducto producto;
    private PedidoState estado;
    private IEnvio metodoDeEnvio;
    private MetodoDePago metodoDePago;
    
    @BeforeEach
    void setUp() {
        deposito = mock(Deposito.class);
        pedido = new Pedido(deposito);
        producto = mock(IProducto.class);
        estado = mock(PedidoState.class);
        metodoDeEnvio = mock(IEnvio.class);
        metodoDePago = mock(MetodoDePago.class);

        pedido.setEstado(estado);
        pedido.setMetodoDeEnvio(metodoDeEnvio);
    }

    @Test
    void testAgregarProductoAlPedidoCuandoElEstadoLoPermiteYHayStock(){
        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(10);
        
        pedido.agregarProducto(producto);

        when(producto.getPeso()).thenReturn(10.0);
        when(producto.getPrecioFinal()).thenReturn(1000.0);
        when(metodoDeEnvio.calcularCosto(10.0, 0.0)).thenReturn(50.0);

        pedido.pagar();
        
        // El precio final es 1050, 1000 del producto y 50 del envío
        verify(estado).procesarPago(pedido, 1050.0);
    }

    @Test
    void testAgregarProductoCuandoElEstadoNoLoPermite() {
        when(estado.puedeModificarProductos()).thenReturn(false);
        when(deposito.getStockDe(producto)).thenReturn(10);
        
        pedido.agregarProducto(producto);
        
        when(metodoDeEnvio.calcularCosto(0.0, 0.0)).thenReturn(50.0);
        
        pedido.pagar();

        // Solo se cobra el costo del envío porque el pedido no tiene prodcutos
        verify(estado).procesarPago(pedido, 50.0);
    }

    @Test
    void testAgregarProductoCuandoNoHayStock() {
        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(0);
        
        pedido.agregarProducto(producto);
        
        when(metodoDeEnvio.calcularCosto(0.0, 0.0)).thenReturn(50.0);
        
        pedido.pagar();

        // Solo se cobra el costo del envío porque el pedido no tiene prodcutos
        verify(estado).procesarPago(pedido, 50.0);
    }

    @Test
    void testEliminarProductoCuandoElEstadoLoPermiteYElProductoExisteEnElPedido() {
        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(10);
        
        pedido.agregarProducto(producto);
        pedido.eliminarProducto(producto);
        
        when(metodoDeEnvio.calcularCosto(0.0, 0.0)).thenReturn(50.0);
        
        pedido.pagar();

        verify(estado).procesarPago(pedido, 50.0);
    }

    @Test
    void testEliminarProductoCuandoElEstadoNoLoPermite() {
        when(estado.puedeModificarProductos()).thenReturn(true, false);
        when(deposito.getStockDe(producto)).thenReturn(10);
        
        pedido.agregarProducto(producto);
        pedido.eliminarProducto(producto);
        
        when(producto.getPeso()).thenReturn(10.0);
        when(producto.getPrecioFinal()).thenReturn(1000.0);
        when(metodoDeEnvio.calcularCosto(10.0, 0.0)).thenReturn(50.0);
        
        pedido.pagar();

        // Como no se eliminó el producto, se procesa el pago con su valor sumado
        verify(estado).procesarPago(pedido, 1050.0);
    }

    @Test
    void testEliminarProductoCuandoElProductoNoExisteEnElPedido() {
        when(estado.puedeModificarProductos()).thenReturn(true);
        
        pedido.eliminarProducto(producto);
        
        when(producto.getPeso()).thenReturn(10.0);
        when(producto.getPrecioFinal()).thenReturn(1000.0);
        when(metodoDeEnvio.calcularCosto(0.0, 0.0)).thenReturn(50.0);
        
        pedido.pagar();

        verify(estado).procesarPago(pedido, 50.0);
    }

    @Test
    void testPrepararEnvio() {
        pedido.prepararEnvio();
        verify(estado).prepararEnvio(pedido);
    }

    @Test
    void testEnviarPedido() {
        pedido.enviar();
        verify(estado).enviarPedido(pedido);
    }

    @Test
    void testEntregarPaquete() {
        pedido.entregar();
        verify(estado).entregarPedido(pedido);
    }

    @Test
    void testCancelarPedido() {
        pedido.cancelar();
        verify(estado).cancelarPedido(pedido);
    }

    @Test
    void testReembolsarCostoTotal() {
        pedido.setMetodoDePago(metodoDePago);

        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(10);

        pedido.agregarProducto(producto);

        when(producto.getPeso()).thenReturn(10.0);
        when(producto.getPrecioFinal()).thenReturn(1000.0);
        when(metodoDeEnvio.calcularCosto(10.0, 0.0)).thenReturn(50.0);

        pedido.reembolsarTotal();

        verify(metodoDePago).reembolsarPago(1050.0);
    }

    @Test
    void testReembolsarSinCostoDeEnvio() {
        pedido.setMetodoDePago(metodoDePago);

        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(10);
        
        pedido.agregarProducto(producto);

        when(producto.getPeso()).thenReturn(10.0);
        when(producto.getPrecioFinal()).thenReturn(1000.0);

        pedido.reembolsarSinEnvio();

        verify(metodoDePago).reembolsarPago(1000.0);

    }

    @Test
    void testSettersYGettersMetodoDeEnvio() {
        pedido.setMetodoDeEnvio(metodoDeEnvio);
        assertEquals(metodoDeEnvio, pedido.getMetodoDeEnvio());
    }

    @Test
    void testSettersYGettersMetodoDePago() {
        pedido.setMetodoDePago(metodoDePago);
        assertEquals(metodoDePago, pedido.getMetodoDePago());
    }

    @Test
    void testSeReduceElStock() {
        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(10);

        pedido.agregarProducto(producto);
        pedido.agregarProducto(producto);

        pedido.reducirStock();

        verify(deposito).decrementarStock(producto, 2);
    }
    
    @Test
    void testSeReponeElStock() {
        when(estado.puedeModificarProductos()).thenReturn(true);
        when(deposito.getStockDe(producto)).thenReturn(10);

        pedido.agregarProducto(producto);
        pedido.agregarProducto(producto);

        pedido.reponerStock();

        verify(deposito).incrementarStock(producto, 2);
    }
}
