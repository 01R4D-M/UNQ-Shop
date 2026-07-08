package unq.integrador.envio;

import unq.integrador.pedido.IPedido;

public interface IEnvio {

    public double calcularCosto(double peso, double distancia);

    public int estimarDias(IPedido pedido);

    // public double getPrecioFinal(IPedido pedido);
}
