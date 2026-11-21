
package negocio;

import modelo.Producto;
import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos;
    private Producto[] destacados;
    private int totalDestacados;

    public Inventario() {
        productos = new ArrayList<>();
        destacados = new Producto[5];
        totalDestacados = 0;
    }

    public boolean registrarProducto(Producto producto) {

        if (producto == null) {
            System.out.println("ERROR: Producto invalido.");
            return false;
        }

        if (producto.getCodigo() == null ||
                producto.getCodigo().trim().isEmpty() ||
                producto.getNombre() == null ||
                producto.getNombre().trim().isEmpty()) {
            System.out.println("ERROR: Datos obligatorios vacios.");
            return false;
        }

        if (buscarProducto(producto.getCodigo()) != null) {
            System.out.println("ERROR: Codigo duplicado.");
            return false;
        }

        if (producto.getCantidad() < 0 ||
                producto.getPrecio() <= 0 ||
                !Double.isFinite(producto.calcularValor())) {
            System.out.println("ERROR: Cantidad o precio invalido.");
            return false;
        }

        productos.add(producto);

        if (totalDestacados < destacados.length) {
            destacados[totalDestacados] = producto;
            totalDestacados++;
        }

        return true;
    }

    public Producto buscarProducto(String codigo) {

        if (codigo == null) {
            return null;
        }

        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }

        return null;
    }

    public void mostrarProductos() {

        if (productos.isEmpty()) {
            System.out.println("Inventario vacio.");
            return;
        }

        for (Producto p : productos) {
            p.mostrarInformacion();
            System.out.println("--------------------");
        }
    }

    public void mostrarDestacados() {

        System.out.println("PRODUCTOS DESTACADOS");

        if (totalDestacados == 0) {
            System.out.println("No hay productos destacados.");
            return;
        }

        for (int i = 0; i < totalDestacados; i++) {
            destacados[i].mostrarInformacion();
            System.out.println("--------------------");
        }
    }

    public boolean venderProducto(String codigo, int cantidad) {

        Producto p = buscarProducto(codigo);

        if (p == null || cantidad <= 0 ||
                cantidad > p.getCantidad()) {
            return false;
        }

        p.setCantidad(p.getCantidad() - cantidad);
        return true;
    }

    public boolean agregarStock(String codigo, int cantidad) {

        Producto p = buscarProducto(codigo);

        if (p == null || cantidad <= 0) {
            System.out.println("ERROR: Producto o cantidad invalida.");
            return false;
        }

        if (cantidad > Integer.MAX_VALUE - p.getCantidad()) {
            System.out.println("ERROR: Cantidad demasiado grande.");
            return false;
        }

        double nuevoValor = (p.getCantidad() + (double) cantidad)
                * p.getPrecio();

        if (!Double.isFinite(nuevoValor)) {
            System.out.println("ERROR: Valor demasiado grande.");
            return false;
        }

        p.setCantidad(p.getCantidad() + cantidad);
        return true;
    }

    public void mostrarStockBajo() {

        boolean encontrado = false;

        for (Producto p : productos) {
            if (p.getCantidad() <= 5) {
                p.mostrarInformacion();
                System.out.println("--------------------");
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay productos con stock bajo.");
        }
    }

    public double calcularTotal() {

        double total = 0;

        for (Producto p : productos) {
            total = total + p.calcularValor();
        }

        return total;
    }
}




