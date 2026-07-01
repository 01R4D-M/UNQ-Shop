package unq.integrador.pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unq.integrador.productos.Deposito;
import unq.integrador.productos.IProducto;
import unq.integrador.envio.IEnvio;
import unq.integrador.notificacion.PedidoObserver;
import unq.integrador.pago.MetodoDePago;
import unq.integrador.pedido.state.Borrador;
import unq.integrador.pedido.state.PedidoState;

public class Pedido implements IPedido {
    private Map<IProducto, Integer> productos;
    private IEnvio metodoDeEnvio;
    private MetodoDePago metodoDePago;
    private List<PedidoObserver> subsistemas;
    private PedidoState estado;
    private Deposito deposito;

    public Pedido(Deposito deposito){
        this.productos = new HashMap<IProducto,Integer>();
        this.subsistemas = new ArrayList<PedidoObserver>();
        this.estado = new Borrador();
        this.deposito = deposito;
    }

    public void agregarProducto(IProducto producto) {
        int stockProducto = this.deposito.getStockDe(producto);
        int cantProducto = this.productos.getOrDefault(producto, 0);

        if (this.estado.puedeModificarProductos() && stockProducto > cantProducto){
            this.productos.put(producto, cantProducto + 1);
        }
    }

    public void eliminarProducto(IProducto producto) {
        int cantProducto = this.productos.getOrDefault(producto, 0);

        if (this.estado.puedeModificarProductos() && cantProducto > 0){
            this.productos.put(producto, cantProducto - 1);
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
        return
            this.productos
            .entrySet()
            .stream()
            .mapToDouble(p -> p.getKey().getPeso() * p.getValue())
            .sum();
    }

    private double calcularDistancia() {
        return 0;
    }

    private double getPrecioFinal() {
        return
            this.productos
            .entrySet()
            .stream()
            .mapToDouble(p -> p.getKey().getPrecioFinal() * p.getValue())
            .sum();
    }

    private double getCostoEnvio() {
        return this.metodoDeEnvio.calcularCosto(this.getPeso(), this.calcularDistancia());
    }

    public void reducirStock() {
        this.productos
        .entrySet()
        .stream()
        .forEach(p -> this.deposito.decrementarStock(p.getKey(), p.getValue()));
    }

    public void reponerStock() {
        this.productos
        .entrySet()
        .stream()
        .forEach(p -> this.deposito.incrementarStock(p.getKey(), p.getValue()));
    }
}
