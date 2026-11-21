package modelo;

public class Herramienta extends Producto {
    private int garantia;

    public Herramienta(String codigo, String nombre, int cantidad,
                       double precio, String fechaIngreso, int garantia) {
        super(codigo, nombre, cantidad, precio, fechaIngreso);
        this.garantia = garantia;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Herramienta");
        System.out.println("Garantia: " + garantia + " meses");
    }
}



