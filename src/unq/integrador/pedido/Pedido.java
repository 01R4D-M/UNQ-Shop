package unq.integrador.pedido;

import java.util.ArrayList;
import java.util.List;

import unq.integrador.productos.IProducto;
import unq.integrador.envio.IEnvio;
import unq.integrador.notificacion.PedidoObserver;
import unq.integrador.pago.MetodoDePago;
import unq.integrador.pedido.state.Borrador;
import unq.integrador.pedido.state.PedidoState;

public class Pedido implements IPedido {
    private List<IProducto> productos;
    private IEnvio metodoDeEnvio;
    private MetodoDePago metodoDePago;
    private List<PedidoObserver> subsistemas;
    private PedidoState estado;

    public Pedido(){
        productos = new ArrayList<IProducto>();
        subsistemas = new ArrayList<PedidoObserver>();
        estado = new Borrador();
    }

    public void agregarProducto(IProducto producto) {
        if (this.estado.puedeModificarProductos()){
            this.productos.add(producto);
        }
    }

    public void eliminarProducto(IProducto producto) {
        if (this.estado.puedeModificarProductos()){
            this.productos.remove(producto);
        }
    }

    public void pagar() {
        this.estado.procesarPago(this, this.getPrecioFinal() + this.getCostoEnvio());
    }

    public void prepararEnvio () {
        this.estado.prepararEnvio(this);
    }

    public void enviar() {
        this.estado.enviarPedido(this);
    }

    public void entregar() {
        this.estado.entregarPedido(this);
    }

    public void cancelar() {
        this.estado.cancelarPedido(this);
    }

    public void reembolsarTotal() {
        this.metodoDePago.reembolsarPago(this.getPrecioFinal() + this.getCostoEnvio());
    }

    public void reembolsarSinEnvio() {
        this.metodoDePago.reembolsarPago(this.getPrecioFinal());
    }

    public IEnvio getMetodoDeEnvio() {
        return this.metodoDeEnvio;
    }

    public void setMetodoDeEnvio(IEnvio metodoDeEnvio) {
        this.metodoDeEnvio = metodoDeEnvio;
    }

    public MetodoDePago getMetodoDePago(){
        return this.metodoDePago;
    }

    public void setMetodoDePago(MetodoDePago metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public void setEstado(PedidoState estado) {
        this.estado = estado;
    }

    private double getPeso() {
        return this.productos.stream().mapToDouble(p -> p.getPeso()).sum();
    }

    private double calcularDistancia() {
        return 0;
    }

    private double getPrecioFinal() {
        return this.productos.stream().mapToDouble(p -> p.getPrecioFinal()).sum();
    }

    private double getCostoEnvio() {
        return this.metodoDeEnvio.calcularCosto(this.getPeso(), this.calcularDistancia());
    }
}
