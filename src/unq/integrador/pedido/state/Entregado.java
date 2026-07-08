package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class Entregado extends PedidoState {
    public boolean puedeModificarProductos() {
        return false;
    }

    public Entregado(IPedido pedido) {
        super(pedido);
    }

    public void cancelarPedido(IPedido pedido) {
    }
}
