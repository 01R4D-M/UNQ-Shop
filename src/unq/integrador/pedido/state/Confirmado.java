package unq.integrador.pedido.state;

import unq.integrador.notificacion.PedidoVisitor;
import unq.integrador.pedido.IPedido;

public class Confirmado extends PedidoState {
    public Confirmado(IPedido pedido) {
        super(pedido);
        pedido.reducirStock();
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

    public void activarSubsistema(PedidoVisitor subsistema) {
        subsistema.activarSubsistemaParaPedidoConfirmado();
    }
}
