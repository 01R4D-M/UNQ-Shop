package unq.integrador.reporte;

import unq.integrador.productos.*;
import unq.integrador.pedido.*;
import java.time.LocalDate;

public class ReporteVentasPorPeriodo extends Reporte {

    private final LocalDate desde;
    private final LocalDate hasta;

    @Override
    public void visitarPedido(IPedido pedido) {
        // armar filas en texto/csv/html
    }

    @Override
    public void visitarCatalogItem(IProducto item) {
        // probablemente no se use en este reporte
    }
}

