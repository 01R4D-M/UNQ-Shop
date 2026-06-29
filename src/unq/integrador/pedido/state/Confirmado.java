package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class Confirmado extends PedidoState {
    public boolean puedeModificarProductos() {
        return false;
    }

    public void prepararEnvio(IPedido pedido) {
        pedido.setEstado(new EnPreparacion());
        pedido.enviar();
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.setEstado(new Cancelado());
    }
}
