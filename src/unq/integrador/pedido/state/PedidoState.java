package unq.integrador.pedido.state;

import unq.integrador.notificacion.PedidoVisitor;
import unq.integrador.pedido.IPedido;

public abstract class PedidoState {
    public PedidoState(IPedido pedido) {
        pedido.notificarSubsistemas();
    }
    public abstract boolean puedeModificarProductos();
    public void procesarPago(IPedido pedido, double precioFinal) {}
    public void prepararEnvio(IPedido pedido){}
    public void enviarPedido(IPedido pedido){}
    public void entregarPedido(IPedido pedido){}
    public abstract void cancelarPedido(IPedido pedido);
    public void activarSubsistema(PedidoVisitor subsistema){};
}
