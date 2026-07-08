package unq.integrador.busqueda;

import unq.integrador.productos.*;

public class PorPrecioMaximo implements Criterio {
    private double precioMaximo;

    public PorPrecioMaximo(double precioMaximo) {
        super();
        this.setPrecioMaximo(precioMaximo);
    }

    public double getPrecioMaximo() {
        return precioMaximo;
    }

    public void setPrecioMaximo(double precioMaximo) {
        this.precioMaximo = precioMaximo;
    }

    @Override
    public boolean cumple(IProducto producto) {
        return producto.getPrecioBase() <= this.getPrecioMaximo();
    }
}