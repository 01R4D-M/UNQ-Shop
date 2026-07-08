package unq.integrador.productos;
import java.util.List;
import java.util.ArrayList;

public class Paquete extends Producto {
    
	private double porcentajeDescuento;
	private List<Producto> productos;
	
	public Paquete(String nombre, String descripcion, String categoria, int descuento) {
		super(nombre, descripcion, categoria);
		this.setPorcentajeDescuento(descuento);
		this.productos = new ArrayList<Producto>();
	}

	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(double porcentajeDescuento) {  
		
		if(porcentajeDescuento >= 0 && porcentajeDescuento <= 100) {
			this.porcentajeDescuento = porcentajeDescuento;
		}	
	}
	
	public void agregarProducto(Producto producto) {
		 this.productos.add(producto);
	 }
	 
	public void eliminarProducto(IProducto producto) {
		this.productos.remove(producto);
	}
	
	public double getPrecioFinal() {
		double totalPrecios = 	productos.stream()
								.mapToDouble(p -> p.getPrecioFinal())
								.sum();
		return totalPrecios * (1 - (this.getPorcentajeDescuento() / 100));
	}
	
	public double getPrecioBase() {
		double totalPrecios = 	productos.stream()
				.mapToDouble(p -> p.getPrecioBase())
				.sum();
		return totalPrecios;
	}

	public double getPeso() {
		return this.productos.stream().mapToDouble(p -> p.getPeso()).sum();
	}
	
	public boolean contieneProducto(Producto producto) {
		return this.productos.contains(producto);
	}
}
