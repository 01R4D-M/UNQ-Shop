package unq.integrador.envio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class EnvioExpressTest {

    private IPedido pedido;
    private EnvioExpress envio;

    @BeforeEach
    void setUp() {
        envio = new EnvioExpress();
    }

    @Test
    void testCalcularCostoEnvio() {
        assertEquals(10.0, envio.calcularCosto(10.0, 5.0), 0.0);
    }

    @Test
    void testEstimarDias() {
        assertEquals(0, envio.estimarDias(pedido));
    }

}
