package unq.integrador.envio;

import java.math.BigDecimal;

import unq.integrador.pedido.*;

public class EnvioEstandar implements IEnvio {

    // private CorreoArgentino correo;

//     public EnvioEstandar(CorreoArgentina correo) {
//        this.correo = correo;
//    } 

    @Override
    public BigDecimal calcularCosto(Pedido pedido) {
        float peso = pedido.getItems().stream()
                .map(CatalogItem::getPeso)
                .reduce(0f, Float::sum);
        Direccion direccion = pedido.getCliente().getDireccion();
        float costo = correo.estimarEnvio(peso, direccion);
        return BigDecimal.valueOf(costo);
    }

    @Override
    public int estimarDias(Pedido pedido) {
        return 5 + (int) (Math.random() * 3); // 5 a 7 días
    }
}
