package unq.integrador.pago;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public class MetodoDePagoTest {

    @Test
    void testValidarPagoEjecutaTodosLosPasos() {
        MetodoDePago metodoDePago = new MetodoDePago() {
            @Override
            public void validarDatos() {
            }

            @Override
            public void reservarFondos() {
            }

            @Override
            public void ejecutarTransaccion() {
            }

            @Override
            public void notificarResultado() {
            }

            @Override
            public void reembolsarPago(double monto) {
            }
        };

        assertDoesNotThrow(() -> metodoDePago.validarPago(1000.0));
    }

    @Test
    void testReembolsarPagoDelegadoAlMetodoConcreto() {
        MetodoDePago metodoDePago = mock(MetodoDePago.class);

        metodoDePago.reembolsarPago(250.0);

        verify(metodoDePago).reembolsarPago(250.0);
    }
}
