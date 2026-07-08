package unq.integrador.envio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class EnvioEstandarTest {
    private EnvioEstandar envio;
    private IPedido pedido;

    @BeforeEach
    void setUp() {
        envio = new EnvioEstandar();
    }

    @Test
    void testCalcularCostoEnvio() {
        assertEquals(0.0, envio.calcularCosto(10.0, 5.0), 0.0);
    }

    @Test
    void testEstimarDias() {
        assertEquals(6, envio.estimarDias(pedido));
    }
}
