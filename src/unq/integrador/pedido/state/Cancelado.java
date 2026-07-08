package unq.integrador.pedido.state;

import unq.integrador.notificacion.PedidoVisitor;
import unq.integrador.pedido.IPedido;

public class Cancelado extends PedidoState {

    public Cancelado(IPedido pedido) {
        super(pedido);
    }

    public boolean puedeModificarProductos() {
        return false;
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.setEstado(new Cancelado(pedido));
    }

    public void activarSubsistema(PedidoVisitor subsistema) {
        subsistema.activarSubsistemaParaPedidoCancelado();
    }

}
