package unq.integrador.busqueda;

import unq.integrador.productos.IProducto;

public class And extends CriterioCompuesto {
	
	public And(Criterio criterio1, Criterio criterio2) {
		super(criterio1, criterio2);
	}
	@Override
	public boolean cumple(IProducto producto) {
		return this.getCriterio1().cumple(producto) 
				&& this.getCriterio2().cumple(producto);
	}

}
