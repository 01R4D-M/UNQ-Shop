package unq.integrador.productos;
import java.util.List;
import java.util.ArrayList;

public class Catalogo {
	private List<Producto> productos;
	
	public Catalogo() {
		super();
		this.productos = new ArrayList<Producto>();
		
	}
	
	public void agregarProducto(Producto producto) {
		this.productos.add(producto);
	}
	
	public void eliminarProducto(Producto producto) {
		this.productos.remove(producto);
	}
	public boolean tieneProducto(Producto producto) {
		return this.productos.contains(producto);
	}
	
}
