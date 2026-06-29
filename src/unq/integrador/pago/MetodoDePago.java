package unq.integrador.pago;

public abstract class MetodoDePago {
    public void validarPago(double monto){
        this.validarDatos();
        this.reservarFondos();
        this.ejecutarTransaccion();
        this.notificarResultado();
    }

    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
    public abstract void notificarResultado();

    public abstract void reembolsarPago(double monto);
}
