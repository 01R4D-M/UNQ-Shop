package unq.integrador.busqueda;
import unq.integrador.productos.*;


public interface Criterio {
	public boolean cumple(Producto producto);
}
