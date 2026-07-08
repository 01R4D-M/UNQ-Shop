package unq.integrador.envio;

public class Direccion {
    private final String calle;
    private final String numero;
    private final String ciudad;
    private final String provincia;
    private final String codigoPostal;

    public Direccion(String calle, String numero, String ciudad,
                     String provincia, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() { return calle; }
    public String getNumero() { return numero; }
    public String getCiudad() { return ciudad; }
    public String getProvincia() { return provincia; }
    public String getCodigoPostal() { return codigoPostal; }
}