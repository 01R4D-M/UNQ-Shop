package unq.integrador.pedido.state;

import unq.integrador.notificacion.PedidoVisitor;
import unq.integrador.pedido.IPedido;

public class Enviado extends PedidoState {
    public Enviado(IPedido pedido) {
        super(pedido);
    }

    public boolean puedeModificarProductos() {
        return false;
    }

    public void entregarPedido(IPedido pedido) {
        pedido.setEstado(new Entregado(pedido));
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.reembolsarSinEnvio();
        pedido.reponerStock();
        pedido.setEstado(new Cancelado(pedido));
    }

    public void activarSubsistema(PedidoVisitor subsistema) {
        subsistema.activarSubsistemaParaPedidoEnviado();
    }
}
