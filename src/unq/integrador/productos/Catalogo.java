package unq.integrador.productos;
import java.util.List;

public class Catalogo {
	private List<Producto> productos;
	
	public Catalogo() {
		super();
		
	}
	
	public void agregarProducto(Producto producto) {
		this.productos.add(producto);
	}
	
	public void eliminarProducto(Producto producto) {
		this.productos.remove(producto);
	}
	
	public int getStockDe(Producto producto){
		 long stock = productos.stream()
				 		.filter(p -> p.getNombre() == producto.getNombre())
				 		.count();
		 return (int) stock;
	}
}
