package unq.integrador.pedido.state;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class ConfirmadoTest {
    private PedidoState estado;
    private IPedido pedido;

    @BeforeEach
    void setUp() {
        estado = new Confirmado();
        pedido = mock(IPedido.class);
    }

    @Test
    void testNoPuedeModificarProductos() {
        assertEquals(false, estado.puedeModificarProductos());
    }

    @Test
    void testPasarPedidoAPreparacion() {
        estado.prepararEnvio(pedido);

        verify(pedido).setEstado(isA(EnPreparacion.class));

    }

    @Test
    void testCancelarPedido() {
        estado.cancelarPedido(pedido);

        verify(pedido).setEstado(isA(Cancelado.class));
    }

    @Test
    void testMetodosVacios(){
        estado.procesarPago(pedido, 0.0);
        estado.enviarPedido(pedido);
        estado.entregarPedido(pedido);

        verifyNoInteractions(pedido);
    }
}
