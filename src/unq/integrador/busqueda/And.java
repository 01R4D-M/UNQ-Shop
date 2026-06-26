package unq.integrador.busqueda;

import unq.integrador.productos.Producto;

public class And extends CriterioCompuesto {
	
	public And(Criterio criterio1, Criterio criterio2) {
		super(criterio1, criterio2);
	}
	@Override
	public boolean cumple(Producto producto) {
		return this.getCriterio1().cumple(producto) 
				&& this.getCriterio2().cumple(producto);
	}

}
