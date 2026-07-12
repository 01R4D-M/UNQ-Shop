package unq.integrador.pedido.state;

import unq.integrador.notificacion.PedidoVisitor;
import unq.integrador.pedido.IPedido;

public class Entregado extends PedidoState {
    public Entregado(IPedido pedido) {
        super(pedido);
    }

    public boolean puedeModificarProductos() {
        return false;
    }

    public void cancelarPedido(IPedido pedido) {
    }

    public void activarSubsistema(PedidoVisitor subsistema) {
        subsistema.activarSubsistemaParaPedidoEntregado();
    }
}
