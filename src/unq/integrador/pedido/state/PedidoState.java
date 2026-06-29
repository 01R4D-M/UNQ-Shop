package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public abstract class PedidoState {
    public abstract boolean puedeModificarProductos();
    public void procesarPago(IPedido pedido, double precioFinal) {}
    public void prepararEnvio(IPedido pedido){}
    public void enviarPedido(IPedido pedido){}
    public void entregarPedido(IPedido pedido){}
    public abstract void cancelarPedido(IPedido pedido);
}
