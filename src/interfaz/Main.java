package interfaz;

import modelo.*;
import negocio.Inventario;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Inventario inventario = new Inventario();

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n===== FERRESTOCK =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Buscar producto");
            System.out.println("4. Vender producto");
            System.out.println("5. Agregar stock");
            System.out.println("6. Mostrar stock bajo");
            System.out.println("7. Valor del inventario");
            System.out.println("8. Productos destacados");
            System.out.println("0. Salir");

            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1:
                    registrar();
                    break;
                case 2:
                    inventario.mostrarProductos();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    vender();
                    break;
                case 5:
                    agregar();
                    break;
                case 6:
                    inventario.mostrarStockBajo();
                    break;
                case 7:
                    System.out.println("Total: $" +
                            inventario.calcularTotal());
                    break;
                case 8:
                    inventario.mostrarDestacados();
                    break;
                case 0:
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }

        } while (opcion != 0);
    }

    public static void registrar() {

        System.out.println("1. Herramienta");
        System.out.println("2. Material");

        int tipo = leerEntero("Tipo: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println("ERROR: Tipo incorrecto.");
            return;
        }

        System.out.print("Codigo: ");
        String codigo = sc.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();

        if (codigo.isEmpty() || nombre.isEmpty()) {
            System.out.println("ERROR: Datos obligatorios vacios.");
            return;
        }

        int cantidad = leerEntero("Cantidad: ");
        double precio = leerDouble("Precio: ");

        if (cantidad < 0 || precio <= 0) {
            System.out.println("ERROR: Cantidad o precio invalido.");
            return;
        }

        String fecha = leerFecha("Fecha ingreso (AAAAMMDD): ");

        LocalDate hoy = LocalDate.now();

        int fechaHoy = hoy.getYear() * 10000 +
                hoy.getMonthValue() * 100 +
                hoy.getDayOfMonth();

        if (Integer.parseInt(fecha) > fechaHoy) {
            System.out.println("ERROR: Fecha de ingreso futura.");
            return;
        }

        Producto producto;

        if (tipo == 1) {

            int garantia = leerEntero("Garantia en meses: ");

            if (garantia < 0) {
                System.out.println("ERROR: Garantia invalida.");
                return;
            }

            producto = new Herramienta(
                    codigo, nombre, cantidad,
                    precio, fecha, garantia
            );

        } else {

            System.out.print("Unidad de medida: ");
            String unidad = sc.nextLine().trim();

            if (unidad.isEmpty()) {
                System.out.println("ERROR: Unidad obligatoria.");
                return;
            }

            producto = new Material(
                    codigo, nombre, cantidad,
                    precio, fecha, unidad
            );
        }

        if (inventario.registrarProducto(producto)) {
            System.out.println("Producto registrado.");
        }
    }

    public static void buscar() {

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();

        Producto p = inventario.buscarProducto(codigo);

        if (p != null) {
            p.mostrarInformacion();
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void vender() {

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();

        int cantidad = leerEntero("Cantidad a vender: ");

        if (inventario.venderProducto(codigo, cantidad)) {
            System.out.println("Venta realizada.");
        } else {
            System.out.println("ERROR: Stock o producto invalido.");
        }
    }

    public static void agregar() {

        System.out.print("Codigo: ");
        String codigo = sc.nextLine();

        int cantidad = leerEntero("Cantidad a agregar: ");

        if (inventario.agregarStock(codigo, cantidad)) {
            System.out.println("Stock actualizado.");
        }
    }

    public static int leerEntero(String mensaje) {

        int numero;

        System.out.print(mensaje);

        while (!sc.hasNextInt()) {
            System.out.println("ERROR: Ingrese un entero.");
            sc.nextLine();
            System.out.print(mensaje);
        }

        numero = sc.nextInt();
        sc.nextLine();

        return numero;
    }

    public static double leerDouble(String mensaje) {

        double numero;

        System.out.print(mensaje);

        while (!sc.hasNextDouble()) {
            System.out.println("ERROR: Ingrese un numero valido.");
            sc.nextLine();
            System.out.print(mensaje);
        }

        numero = sc.nextDouble();
        sc.nextLine();

        return numero;
    }

    public static String leerFecha(String mensaje) {

        while (true) {

            int fecha = leerEntero(mensaje);

            int anio = fecha / 10000;
            int mes = (fecha / 100) % 100;
            int dia = fecha % 100;

            int maxDias = 31;

            if (anio >= 1000 && anio <= 9999 &&
                    mes >= 1 && mes <= 12) {

                if (mes == 4 || mes == 6 ||
                        mes == 9 || mes == 11) {
                    maxDias = 30;
                }

                if (mes == 2) {
                    maxDias = 28;

                    if (anio % 400 == 0 ||
                            (anio % 4 == 0 && anio % 100 != 0)) {
                        maxDias = 29;
                    }
                }

                if (dia >= 1 && dia <= maxDias) {
                    return "" + fecha;
                }
            }

            System.out.println("ERROR: Fecha invalida.");
        }
    }
}

