package unq.integrador.envio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.pedido.IPedido;

public class DireccionTest {

    private Direccion direccion;
    private IPedido pedido;

    @BeforeEach
    void setUp() {
        direccion = new Direccion("Calle Falsa", "123", "Springfield", "Buenos Aires", "12345");
    }

    @Test
    void testGetters() {
        assertEquals("Calle Falsa 123", direccion.getCalle());
        assertEquals("Springfield", direccion.getCiudad());
        assertEquals("12345", direccion.getCodigoPostal());
        assertEquals("Buenos Aires", direccion.getProvincia());

    }

}
