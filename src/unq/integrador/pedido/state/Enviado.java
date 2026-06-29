package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class Enviado extends PedidoState {
    public boolean puedeModificarProductos() {
        return false;
    }

    public void entregarPedido(IPedido pedido) {
        pedido.setEstado(new Entregado());
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.reembolsarSinEnvio();
        pedido.setEstado(new Cancelado());
    }
}
