package unq.integrador.busqueda;

import unq.integrador.productos.IProducto;

public class Or extends CriterioCompuesto implements Criterio {
	
	public Or(Criterio criterio1, Criterio criterio2) {
		super(criterio1, criterio2);
	}
	@Override
	public boolean cumple(IProducto producto) {
		return this.getCriterio1().cumple(producto)
				|| this.getCriterio2().cumple(producto);
	}

}
