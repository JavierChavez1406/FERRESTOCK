package modelo;

public class Producto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private double precio;
    private String fechaIngreso;

    public Producto(String codigo, String nombre, int cantidad,
                    double precio, String fechaIngreso) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double calcularValor() {
        return cantidad * precio;
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio: $" + precio);
        System.out.println("Fecha ingreso: " + fechaIngreso);
    }
}

