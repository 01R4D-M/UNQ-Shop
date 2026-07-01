package unq.integrador.busqueda;
import unq.integrador.productos.IProducto;

public class PorCategoria implements Criterio {
	private String categoria;
	
	public PorCategoria(String categoria) {
		super();
		this.setCategoria(categoria);
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public boolean cumple(IProducto producto) {
		return producto.getCategoria().equalsIgnoreCase(this.getCategoria());
	}
}
