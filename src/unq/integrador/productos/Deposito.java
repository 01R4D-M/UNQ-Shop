package unq.integrador.productos;
import java.util.Map;
import java.util.HashMap;

public class Deposito {
	private Map<Producto, Integer> stock;
	
	public Deposito() {
		super();
		this.stock = new HashMap<Producto, Integer>();
	}
	
	public void incrementarStock(Producto producto, int cantidad) {					// si el producto no existe en el Map, lo agrega con el stock pasado por parametro
		if(cantidad > 0) {
			this.stock.put(producto, stock.getOrDefault(producto, 0) + cantidad);	// si el producto existe, le suma la cantidad pasada por parametro al stock que ya tiene
		}
	}																				
	public void decrementarStock(Producto producto, int cantidad) {
		int stockActualDeProducto = this.getStockDe(producto);
		if(stockActualDeProducto >= cantidad && cantidad > 0) {
			stock.put(producto, stockActualDeProducto - cantidad);
		}
	}
	public boolean hayStockDe(Producto producto) {
		return stock.getOrDefault(producto, 0) > 0;
	}
	public int getStockDe(Producto producto) {
		return stock.getOrDefault(producto, 0);
	}
	
}
