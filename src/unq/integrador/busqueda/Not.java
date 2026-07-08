package unq.integrador.busqueda;

import unq.integrador.productos.IProducto;

public class Not implements Criterio {
    private Criterio criterio;

    public Not(Criterio criterio) {
        super();
        this.setCriterio(criterio);
    }

    public Criterio getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterio criterio) {
        this.criterio = criterio;
    }

    public boolean cumple(IProducto producto) {
        return !this.getCriterio().cumple(producto);
    }
}