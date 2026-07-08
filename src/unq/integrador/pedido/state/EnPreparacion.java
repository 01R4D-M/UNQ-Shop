package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class EnPreparacion extends PedidoState {
    public boolean puedeModificarProductos() {
        return false;
    }

    public EnPreparacion(IPedido pedido) {
        super(pedido);
    }

    public void enviarPedido(IPedido pedido) {
        pedido.setEstado(new Enviado(pedido));
        pedido.entregar();
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.reembolsarTotal();
        pedido.setEstado(new Cancelado(pedido));
    }
}
