package unq.integrador.notificacion;

public class NotificadorMail extends PedidoVisitor {
    private MailSender mailSender;

    public NotificadorMail(MailSender mailSender){
        this.mailSender = mailSender;
    }

    public void activarSubsistemaParaPedidoConfirmado(){
        mailSender.enviarMail("", "Pedido confirmado", "El pedido fue confirmado", "");
    }

    public void activarSubsistemaParaPedidoEnviado(){
        mailSender.enviarMail("", "Pedido enviado", "El pedido fue enviado", "");
    }

    public void activarSubsistemaParaPedidoEntregado(){
        mailSender.enviarMail("", "Pedido entregado", "El pedido fue entregado", "");
    }
}
