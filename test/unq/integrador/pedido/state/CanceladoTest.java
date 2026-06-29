package unq.integrador.pedido.state;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class CanceladoTest {
    private PedidoState estado;
    private IPedido pedido;

    @BeforeEach
    void setUp() {
        estado = new Cancelado();
        pedido = mock(IPedido.class);
    }

    @Test
    void testNoPuedeModificarProductos() {
        assertEquals(false, estado.puedeModificarProductos());
    }

    @Test
    void testMetodosVacios(){
        estado.procesarPago(pedido, 0.0);
        estado.prepararEnvio(pedido);
        estado.enviarPedido(pedido);
        estado.entregarPedido(pedido);
        estado.cancelarPedido(pedido);

        verifyNoInteractions(pedido);
    }
}
