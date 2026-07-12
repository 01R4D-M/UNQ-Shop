package unq.integrador.pedido.state;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class EnviadoTest {
    private PedidoState estado;
    private IPedido pedido;

    @BeforeEach
    void setUp() {
        pedido = mock(IPedido.class);
        estado = new Enviado(pedido);
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
    void testEntregarPaquete() {
        estado.entregarPedido(pedido);

        verify(pedido).setEstado(isA(Entregado.class));
    }

    @Test
    void testCancelarPedido() {
        estado.cancelarPedido(pedido);

        verify(pedido).reponerStock();
        verify(pedido).reembolsarSinEnvio();
        verify(pedido).setEstado(isA(Cancelado.class));
    }

    @Test
    void testMetodosVacios() {
        verify(pedido).notificarSubsistemas();

        estado.procesarPago(pedido, 0.0);
        estado.prepararEnvio(pedido);
        estado.enviarPedido(pedido);

        verifyNoMoreInteractions(pedido);
    }
}
