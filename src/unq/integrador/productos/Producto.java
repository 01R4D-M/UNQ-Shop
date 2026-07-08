package unq.integrador.productos;

public abstract class Producto implements IProducto {

	private String nombre;
	private String descripcion;
	private String categoria;

	public Producto(String nombre, String descripcion, String categoria) {
		super();
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
		this.setCategoria(categoria);
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public abstract double getPrecioBase();

	public abstract double getPrecioFinal();
}
