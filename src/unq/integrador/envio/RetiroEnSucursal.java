package unq.integrador.envio;

import unq.integrador.pedido.IPedido;

public class RetiroEnSucursal implements IEnvio {

    @Override
    public double calcularCosto(double peso, double distancia) {
        return 0;
    }

    @Override
    public int estimarDias(IPedido pedido) {
        return 0;
    }

}
