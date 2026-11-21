package modelo;

public class Material extends Producto {
    private String unidadMedida;

    public Material(String codigo, String nombre, int cantidad,
                    double precio, String fechaIngreso,
                    String unidadMedida) {
        super(codigo, nombre, cantidad, precio, fechaIngreso);
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Material");
        System.out.println("Unidad de medida: " + unidadMedida);
    }
}



