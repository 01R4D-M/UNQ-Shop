package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class Confirmado extends PedidoState {

    public Confirmado(IPedido pedido) {
        super(pedido);
    }

    public boolean puedeModificarProductos() {
        return false;
    }

    public void prepararEnvio(IPedido pedido) {
        pedido.setEstado(new EnPreparacion(pedido));
        pedido.enviar();
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.setEstado(new Cancelado(pedido));
    }
}
