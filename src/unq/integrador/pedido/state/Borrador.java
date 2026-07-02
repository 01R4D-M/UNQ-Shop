package unq.integrador.pedido.state;

import unq.integrador.pedido.IPedido;

public class Borrador extends PedidoState {
    public Borrador(IPedido pedido){
        super(pedido);
    }
    public boolean puedeModificarProductos(){
        return true;
    }

    public void procesarPago(IPedido pedido, double precioFinal) {
        pedido.getMetodoDePago().validarPago(precioFinal);
        pedido.setEstado(new Confirmado(pedido));
        pedido.prepararEnvio();
    }

    public void cancelarPedido(IPedido pedido) {
        pedido.setEstado(new Cancelado(pedido));
    }
}
