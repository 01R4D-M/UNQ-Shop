package unq.integrador.envio;

import unq.integrador.pedido.IPedido;

public class EnvioExpress implements IEnvio {

    private IPedido pedido;
    private double porcentajeFijo = 0.1;

    @Override
    public double calcularCosto(double peso, double distancia) {
        // return pedido.getPrecioFinal() * porcentajeFijo;
        return 10;
    }

    @Override
    public int estimarDias(IPedido pedido) {
        return 0;
    }

}
