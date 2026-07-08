package unq.integrador.envio;

import unq.integrador.pedido.IPedido;

public class EnvioEstandar implements IEnvio {

    // private CorreoArgentino correo;

    // public EnvioEstandar(CorreoArgentina correo) {
    // this.correo = correo;
    // }

    @Override
    public double calcularCosto(double peso, double distancia) {
        return 0;
    }

    @Override
    public int estimarDias(IPedido pedido) {
        return 6; // 5 a 7 días
    }
}
