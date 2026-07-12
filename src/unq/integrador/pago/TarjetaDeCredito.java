package unq.integrador.pago;

public class TarjetaDeCredito extends MetodoDePago {
    private String numero;
    private String titular;
    private String fechaVencimiento;
    private String codigoSeguridad;

    public TarjetaDeCredito(String numero, String titular, String fechaVencimiento, String codigoSeguridad) {
        this.numero = numero;
        this.titular = titular;
        this.fechaVencimiento = fechaVencimiento;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void validarDatos() {
        // Implementación de validación de datos de la tarjeta de crédito
    }

    @Override
    public void reservarFondos() {
        // Implementación de reserva de fondos en la tarjeta de crédito
    }

    @Override
    public void ejecutarTransaccion() {
        // Implementación de ejecución de la transacción con la tarjeta de crédito
    }

    @Override
    public void notificarResultado() {
        // Implementación de notificación del resultado del pago con tarjeta de crédito
    }

    @Override
    public void reembolsarPago(double monto) {
        // Implementación de reembolso del pago realizado con tarjeta de crédito
    }

}
