package unq.integrador;

public abstract class Producto implements IProducto {
    
	private String nombre;
	private String descripcion;
	
	public Producto(String nombre, String descripcion) {
		super();
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public abstract double getPrecioBase();
	public abstract double getPrecioFinal();
}
