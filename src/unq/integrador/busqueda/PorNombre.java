package unq.integrador.busqueda;
import unq.integrador.productos.*;

public class PorNombre implements Criterio {
	private String nombre;
	
	public PorNombre(String nombre) {
		super();
		this.setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean cumple(Producto producto) {
		return producto.getNombre().equalsIgnoreCase(this.getNombre());
	}
}
