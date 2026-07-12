package unq.integrador.busqueda;

import unq.integrador.productos.IProducto;

public abstract class CriterioCompuesto implements Criterio {

    protected Criterio criterio1;
    protected Criterio criterio2;

    public CriterioCompuesto(Criterio criterio1, Criterio criterio2) {
        this.setCriterio1(criterio1);
        this.setCriterio2(criterio2);
    }

    @Override
    public abstract boolean cumple(IProducto producto);

    public Criterio getCriterio1() {
        return criterio1;
    }

    public void setCriterio1(Criterio criterio1) {
        this.criterio1 = criterio1;
    }

    public Criterio getCriterio2() {
        return criterio2;
    }

    public void setCriterio2(Criterio criterio2) {
        this.criterio2 = criterio2;
    }

}
