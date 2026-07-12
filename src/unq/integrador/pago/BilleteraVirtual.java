package unq.integrador.pago;

public class BilleteraVirtual extends MetodoDePago {
    private double saldo;

    public BilleteraVirtual(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public void validarDatos() {
        // Lógica para validar los datos de la billetera virtual
        System.out.println("Validando datos de la billetera virtual para el usuario: ");
    }

    @Override
    public void reservarFondos() {
        // Lógica para reservar fondos en la billetera virtual
        System.out.println("Reservando fondos en la billetera virtual para el usuario: ");
    }

    @Override
    public void ejecutarTransaccion() {
        // Lógica para ejecutar la transacción de pago
        System.out.println("Ejecutando transacción de pago desde la billetera virtual para el usuario: ");
    }

    @Override
    public void notificarResultado() {
        // Lógica para notificar el resultado del pago
        System.out.println("Notificando resultado del pago desde la billetera virtual para el usuario: ");
    }

    @Override
    public void reembolsarPago(double monto) {
        // Lógica para reembolsar el pago a la billetera virtual
        System.out.println("Reembolsando " + monto + " a la billetera virtual del usuario: ");
    }

}
