package unq.integrador.notificacion;

public class GeneradorFactura extends PedidoVisitor {
    public void activarSubsistemaParaPedidoEntregado(){
        this.generarFactura();
    }

    private String generarFactura() {
        return "Factura";
    }
}
