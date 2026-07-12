package unq.integrador.pago;

public class TransferenciaBancaria extends MetodoDePago {

    @Override
    public void validarDatos() {
        System.out.println("Validando datos de la transferencia bancaria...");
    }

    @Override
    public void reservarFondos() {
        System.out.println("Reservando fondos de la cuenta bancaria...");
    }

    @Override
    public void ejecutarTransaccion() {
        System.out.println("Ejecutando la transferencia bancaria...");
    }

    @Override
    public void notificarResultado() {
        System.out.println("Notificando el resultado de la transferencia bancaria...");
    }

    @Override
    public void reembolsarPago(double monto) {
        System.out.println("Reembolsando " + monto + " a través de transferencia bancaria...");
    }

}
