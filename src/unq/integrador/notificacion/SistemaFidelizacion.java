package unq.integrador.notificacion;

public class SistemaFidelizacion extends PedidoVisitor {
    public void activarSubsistemaParaPedidoCancelado(){
        this.generarCupon();
    }

    public String generarCupon() {
        return "Cupon";
    }
}
