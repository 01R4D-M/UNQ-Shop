package unq.integrador.pedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unq.integrador.productos.Deposito;
import unq.integrador.productos.IProducto;
import unq.integrador.envio.IEnvio;
import unq.integrador.notificacion.GeneradorFactura;
import unq.integrador.notificacion.MailSender;
import unq.integrador.notificacion.NotificadorMail;
import unq.integrador.notificacion.PedidoVisitor;
import unq.integrador.notificacion.SistemaFidelizacion;
import unq.integrador.pago.MetodoDePago;
import unq.integrador.pedido.state.Borrador;
import unq.integrador.pedido.state.PedidoState;

public class Pedido implements IPedido {
    private Map<IProducto, Integer> productos;
    private IEnvio metodoDeEnvio;
    private MetodoDePago metodoDePago;
    private List<PedidoVisitor> subsistemas;
    private PedidoState estado;
    private Deposito deposito;

    public Pedido(Deposito deposito){
        this.productos = new HashMap<IProducto,Integer>();
        this.subsistemas = new ArrayList<PedidoVisitor>();
        this.estado = new Borrador(this);
        this.deposito = deposito;

        MailSender mailSender = new MailSender() {
            public void enviarMail(String direcciónDestino, String título, String mensaje, String adjunto){};
        };
        // sabemos que esto no es correcto, pero al ser un Singleton que en
        // este modelo no tiene implementacion y por lo tanto tampoco ningun efecto,
        // lo hacemos de esta forma. En otro contexto, esta variable recibiría
        // una instancia de una clase concreta que implementa MailSender.

        PedidoVisitor notificadorMail = new NotificadorMail(mailSender);
        PedidoVisitor generadorFactura = new GeneradorFactura();
        PedidoVisitor sistemaFidelizacion = new SistemaFidelizacion();

        this.agregarSubsistema(notificadorMail);
        this.agregarSubsistema(generadorFactura);
        this.agregarSubsistema(sistemaFidelizacion);
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

    public void agregarSubsistema(PedidoVisitor subsistema) {
        this.subsistemas.add(subsistema);
    }

    public void eliminarSubsistema(PedidoVisitor subsistema) {
        this.subsistemas.remove(subsistema);
    }

    public void notificarSubsistemas() {
        this.subsistemas.stream().forEach(s -> this.estado.activarSubsistema(s));
    }
}
