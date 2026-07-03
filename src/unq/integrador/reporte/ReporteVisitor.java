package unq.integrador.reporte;

import unq.integrador.pedido.*;
import unq.integrador.productos.*;


public interface ReporteVisitor {
    void visitarPedido(IPedido pedido);
    void visitarCatalogItem(IProducto producto);
}
