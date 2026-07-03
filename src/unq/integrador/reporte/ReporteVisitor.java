package unq.integrador.reporte;

public interface ReporteVisitor {
    void visitarPedido(Pedido pedido);
    void visitarCatalogItem(CatalogItem item);
}
