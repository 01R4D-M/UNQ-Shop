package unq.integrador.notificacion;

public interface MailSender {
    public void enviarMail(String direcciónDestino, String título, String mensaje, String adjunto);
}
