package unq.integrador;
import java.util.Map;
import java.util.HashMap;

public class ProductoIndividual extends Producto {
    
	private int sku;
    private String marca;
    private String categoria;
    private double peso;
    private double precioBase;
    private Map<String, Object> atributosDinamicos;
    
    public ProductoIndividual(	String nombre,
    							String descripcion,
    							int sku,
			    				String marca,
			    				String categoria,
			    				double peso,
			    				double precioBase
    				) {
    	super(nombre, descripcion);
    	this.setSku(sku);
    	this.setMarca(marca);
    	this.setCategoria(categoria);
    	this.setPeso(peso);
    	this.setPrecioBase(precioBase);
    	this.atributosDinamicos = new HashMap<String, Object>();
    	
    	
    	
    }
    


	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public double getPrecioFinal() {
		return this.getPrecioBase();
	}
	
	public Object getAtributo(String nombreAtributo) {
		return this.atributosDinamicos.get(nombreAtributo.toLowerCase());
	}
	
	public void setAtributo(String nombreAtributo, Object valor) {
		this.atributosDinamicos.put(nombreAtributo.toLowerCase(), valor);
	}
	
	public void eliminarAtributo(String nombreAtributo) {
		this.atributosDinamicos.remove(nombreAtributo.toLowerCase());
	}
	
	public boolean validarAtributosObligatorios() {
			return this.sku > 0 && this.getNombre() != null;
	}
	
	public boolean validarAtributoDinamico(String nombreAtributo) {
		
		Object valorAValidar = this.getAtributo(nombreAtributo);
		return valorAValidar != null;
	}
}
	
