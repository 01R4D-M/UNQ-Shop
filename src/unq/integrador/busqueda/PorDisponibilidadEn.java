package unq.integrador.busqueda;
import unq.integrador.productos.*;

public class PorDisponibilidadEn implements Criterio {
	private int disponibilidad;
	
	public PorDisponibilidadEn(Producto producto, Catalogo catalogo) {
		super();
		this.setDisponibilidad(catalogo.getStockDe(producto));
		
	}
	
	public boolean cumple(Producto producto) {
		return this.getDisponibilidad() > 0;
	}

	public int getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

}
