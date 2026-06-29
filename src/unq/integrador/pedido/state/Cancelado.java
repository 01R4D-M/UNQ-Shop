package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class Cancelado extends PedidoState {
    public boolean puedeModificarProductos() {
        return false;
    }

    public void cancelarPedido(IPedido pedido) {}
}
