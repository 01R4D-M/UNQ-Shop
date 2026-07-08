package unq.integrador.envio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class RetiroEnSucursalTest {

    private IPedido pedido;
    private RetiroEnSucursal envio;

    @BeforeEach
    void setUp() {
        envio = new RetiroEnSucursal();
    }

    @Test
    void testCalcularCostoEnvio() {
        assertEquals(0.0, envio.calcularCosto(10.0, 5.0), 0.0);
    }

    @Test
    void testEstimarDias() {
        assertEquals(0, envio.estimarDias(pedido));
    }

}
