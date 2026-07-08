package unq.integrador.pedido.state;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class EnPreparacionTest {
    private PedidoState estado;
    private IPedido pedido;

    @BeforeEach
    void setUp() {
        pedido = mock(IPedido.class);
        estado = new EnPreparacion(pedido);
    }

    @Test
    void testInicializacion() {
        verify(pedido).notificarSubsistemas();
    }

    @Test
    void testNoPuedeModificarProductos() {
        assertEquals(false, estado.puedeModificarProductos());
    }

    @Test
    void enviarPedido() {
        estado.enviarPedido(pedido);

        verify(pedido).setEstado(isA(Enviado.class));
        verify(pedido).entregar();
    }

    @Test
    void testCancelarPedido() {
        estado.cancelarPedido(pedido);

        verify(pedido).reembolsarTotal();
        verify(pedido).setEstado(isA(Cancelado.class));
    }

    @Test
    void testMetodosVacios(){
        verify(pedido).notificarSubsistemas();

        estado.procesarPago(pedido, 0.0);
        estado.prepararEnvio(pedido);
        estado.entregarPedido(pedido);

        verifyNoMoreInteractions(pedido);
    }
}
