package unq.integrador.pedido.state;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pago.MetodoDePago;
import unq.integrador.pedido.IPedido;

public class BorradorTest {
    private PedidoState estado;
    private IPedido pedido;
    private MetodoDePago metodoDePago;

    @BeforeEach
    void setUp() {
        estado = new Borrador();
        pedido = mock(IPedido.class);
        metodoDePago = mock(MetodoDePago.class);
    }

    @Test
    void testPuedeModificarProductos() {
        assertEquals(true, estado.puedeModificarProductos());
    }

    @Test
    void testProcesoDePago() {
        when(pedido.getMetodoDePago()).thenReturn(metodoDePago);

        estado.procesarPago(pedido, 1000.0);

        verify(metodoDePago).validarPago(1000.0);
        verify(pedido).setEstado(isA(Confirmado.class));
        verify(pedido).prepararEnvio();
    }

    @Test
    void testCancelarPedido() {
        estado.cancelarPedido(pedido);

        verify(pedido).setEstado(isA(Cancelado.class));
    }

    @Test
    void testMetodosVacios(){
        estado.prepararEnvio(pedido);
        estado.enviarPedido(pedido);
        estado.entregarPedido(pedido);

        verifyNoInteractions(pedido);
    }

}
