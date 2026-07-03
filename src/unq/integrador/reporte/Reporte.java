package unq.integrador.reporte;

public abstract class Reporte implements ReporteVisitor {

    protected final StringBuilder texto = new StringBuilder();
    protected final StringBuilder csv = new StringBuilder();
    protected final StringBuilder html = new StringBuilder();

    public String generarComoTextoPlano() { return texto.toString(); }
    public String generarComoCSV() { return csv.toString(); }
    public String generarComoHTML() { return html.toString(); }
}
