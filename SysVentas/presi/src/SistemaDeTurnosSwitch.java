import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SistemaDeTurnosSwitch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> colaClientes = new LinkedList<>();
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE TURNOS ===");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Mostrar clientes en espera");
            System.out.println("3. Atender cliente");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del cliente: ");
                    String nombre = sc.nextLine();
                    colaClientes.add(nombre);
                    System.out.println(" Cliente agregado a la cola.");
                    break;

                case 2:
                    if (colaClientes.isEmpty()) {
                        System.out.println(" No hay clientes en espera.");
                    } else {
                        System.out.println(" Clientes en espera: " + colaClientes);
                    }
                    break;

                case 3:
                    if (colaClientes.isEmpty()) {
                        System.out.println(" No hay clientes para atender.");
                    } else {
                        String clienteAtendido = colaClientes.poll();
                        System.out.println("🧍‍♂️ Atendiendo a: " + clienteAtendido);
                        if (!colaClientes.isEmpty()) {
                            System.out.println("Clientes restantes: " + colaClientes);
                        } else {
                            System.out.println(" Todos los clientes han sido atendidos.");
                        }
                    }
                    break;
                case 4:
                    System.out.println(" Saliendo del sistema...");
                    break;

                default:
                    System.out.println(" Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 4);

        sc.close();
    }
}

