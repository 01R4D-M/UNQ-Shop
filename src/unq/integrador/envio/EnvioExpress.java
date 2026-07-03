package unq.integrador.envio;

import unq.integrador.pedido.IPedido;

public class EnvioExpress implements IEnvio {

    @Override
    public double calcularCosto(IPedido pedido) {
        return 0;
    }

    @Override
    public int estimarDias(IPedido pedido) {
        return 0;
    }

}
