package unq.integrador.pago;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MetodoDePagoTest {

    private MetodoDePago metodoDePago;

    @BeforeEach
    void setUp() {
        metodoDePago = mock(MetodoDePago.class);
    }

    @Test
    void testValidarPagoEjecutaTodosLosPasos() {
        assertDoesNotThrow(() -> metodoDePago.validarPago(1000.0));
    }

    @Test
    void testReembolsarPagoDelegadoAlMetodoConcreto() {
        metodoDePago.reembolsarPago(250.0);
        verify(metodoDePago).reembolsarPago(250.0);
    }
}
