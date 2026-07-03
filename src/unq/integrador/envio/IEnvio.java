package unq.integrador.envio;

import unq.integrador.pedido.*;

public interface IEnvio {
    public double calcularCosto(IPedido pedido);
    // public double calcularCosto(double peso, double distancia);
    public int estimarDias(IPedido pedido);
}
