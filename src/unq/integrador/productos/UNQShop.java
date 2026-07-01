package unq.integrador.productos;
import java.util.List;
import java.util.ArrayList;
import unq.integrador.pedido.*;

public class UNQShop {
	private Catalogo catalogo;
	private List<IPedido> pedidos;
	private Deposito deposito;
	
	public UNQShop(Deposito deposito, Catalogo catalogo) {
		super();
		this.catalogo = catalogo;
		this.pedidos = new ArrayList<IPedido>();
		this.deposito = deposito;
		
	}
	
	public void agregarProductoAlCatalogoYSetearStockInicialEn(IProducto producto, int cantidad) {
			this.catalogo.agregarProducto(producto);
			this.incrementarStock(producto, cantidad);
		
	}
	public void incrementarStock(IProducto producto, int cantidad) {					
		if(this.catalogo.tieneProducto(producto)) {
			this.deposito.incrementarStock(producto, cantidad);						
		}
	}
	public void decrementarStock(IProducto producto, int cantidad) {
		this.deposito.decrementarStock(producto, cantidad);
	}
	public boolean hayStockDe(IProducto producto) {
		return this.deposito.hayStockDe(producto);
	}
	public int getStockDe(IProducto producto) {
		return this.deposito.getStockDe(producto);
	}
	public boolean contieneProductoEnCatalogo(IProducto producto) {
		return this.catalogo.tieneProducto(producto);
	}
	public int ocurrenciasDeEnCatalogo(IProducto producto) {
		return this.catalogo.ocurrenciasDe(producto);
	}

	public List<IPedido> getPedidos() {
		return pedidos;
	}

	public void setPedido(IPedido pedido) {
		this.pedidos.add(pedido);
	}
	
}
