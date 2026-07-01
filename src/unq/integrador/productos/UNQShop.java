package unq.integrador.productos;
import java.util.List;
import java.util.ArrayList;
import unq.integrador.pedido.*;

public class UNQShop {
	private Catalogo catalogo;
	private List<IPedido> pedidos;
	private Deposito deposito;
	
	public UNQShop(Deposito deposito) {
		super();
		this.catalogo = new Catalogo();
		this.pedidos = new ArrayList<IPedido>();
		this.deposito = deposito;
		
	}
	
	public void agregarProductoAlCatalogoYSetearStockInicialEn(Producto producto, int cantidad) {
		if(!this.catalogo.tieneProducto(producto)) {
			this.catalogo.agregarProducto(producto);
			this.incrementarStock(producto, cantidad);
		}
	}
	public void incrementarStock(Producto producto, int cantidad) {					
		if(this.catalogo.tieneProducto(producto)) {
			this.deposito.incrementarStock(producto, cantidad);						
		}
	}
	public void decrementarStock(Producto producto, int cantidad) {
		this.deposito.decrementarStock(producto, cantidad);
	}
	public boolean hayStockDe(Producto producto) {
		return this.deposito.hayStockDe(producto);
	}
	public int getStockDe(Producto producto) {
		return this.deposito.getStockDe(producto);
	}
	public boolean contieneProductoEnCatalogo(Producto producto) {
		return this.catalogo.tieneProducto(producto);
	}

	public List<IPedido> getPedidos() {
		return pedidos;
	}

	public void setPedido(IPedido pedido) {
		this.pedidos.add(pedido);
	}
	
}
