package unq.integrador.notificacion;

public abstract class PedidoVisitor {
    public void activarSubsistemaParaPedidoConfirmado(){}
    public void activarSubsistemaParaPedidoEnviado(){}
    public void activarSubsistemaParaPedidoEntregado(){}
    public void activarSubsistemaParaPedidoCancelado(){}
}
