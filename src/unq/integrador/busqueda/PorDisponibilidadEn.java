package unq.integrador.busqueda;
import unq.integrador.productos.*;

public class PorDisponibilidadEn implements Criterio {
	private UNQShop shop;
	
	public PorDisponibilidadEn(UNQShop shop) {
		super();
		this.shop = shop;
		
	}
	
	public boolean cumple(IProducto producto) {
		return shop.hayStockDe(producto);
	}

}
