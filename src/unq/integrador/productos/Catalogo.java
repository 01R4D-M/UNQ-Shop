package unq.integrador.productos;
import java.util.List;
import java.util.ArrayList;
import unq.integrador.busqueda.*;

public class Catalogo {
	private List<IProducto> productos;
	
	public Catalogo() {
		super();
		this.productos = new ArrayList<IProducto>();
		
	}
	
	public void agregarProducto(IProducto producto) {
		if(!this.tieneProducto(producto)) {
			this.productos.add(producto);
		}
		
	}
	
	public void eliminarProducto(IProducto producto) {
		if(this.tieneProducto(producto)) {
			this.productos.remove(producto);
		}
	}
	public boolean tieneProducto(IProducto producto) {
		return this.productos.contains(producto);
	}
	public int ocurrenciasDe(IProducto producto) {
		int contador = 0;
		for(IProducto p: productos) {
			if(p.getNombre().equalsIgnoreCase(producto.getNombre())) {
				contador++;
			}
		}
	return contador;
	}
	public List<IProducto> buscar(Criterio criterio){
		return this.productos.stream()
				.filter(producto -> criterio.cumple(producto))
				.toList();
	}
}
