package unq.integrador.pedido;

import unq.integrador.IProducto;
import unq.integrador.envio.IEnvio;
import unq.integrador.pago.MetodoDePago;
import unq.integrador.pedido.state.PedidoState;

public interface IPedido {
    public void agregarProducto(IProducto producto);
    public void eliminarProducto(IProducto producto);

    public void pagar();
    public void prepararEnvio();
    public void enviar();
    public void entregar();
    public void cancelar();

    public void reembolsarTotal();
    public void reembolsarSinEnvio();
    
    public IEnvio getMetodoDeEnvio();
    public void setMetodoDeEnvio(IEnvio metodoDeEnvio);

    public MetodoDePago getMetodoDePago();
    public void setMetodoDePago(MetodoDePago metodoDePago);

    public void setEstado(PedidoState estado);
}
