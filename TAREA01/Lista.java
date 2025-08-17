package TAREA01;
/**
 * Tema01: ArrayList
 * @author JOSEFTH MIDWAR VILCA ÑACA
 */
import java.util.ArrayList;
import java.util.Scanner;

class Persona {
    String n;
    String a;
    String d;
    int e;
    double t;

    public Persona(String nombre, String apellidos, String dni, int edad, double talla) {
        n = nombre;
        a = apellidos;
        d = dni;
        e= edad;
        t = talla;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-15s %-10s %-5d %-6.2f",
                n, a, d, e, t);
    }
}

public class Lista {
    static ArrayList<Persona> lista = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("--- Personas ---");
            System.out.println("1. Agregar");
            System.out.println("2. Eliminar");
            System.out.println("3. Editar");
            System.out.println("4. Listar");
            System.out.println("5. Buscar");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            if (opcion == 1) {
                agregar();
            } else if (opcion == 2) {
                eliminar();
            } else if (opcion == 3) {
                editar();
            } else if (opcion == 4) {
                listar();
            } else if (opcion == 5) {
                buscar();
            } else if (opcion == 6) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción inválida.");
            }

        } while (opcion != 6);
    }

    static void agregar() {
        String continuar;
        do {
            System.out.print("Ingrese DNI: ");
            String dni = sc.nextLine();

            // Validar que el DNI no se repita en el codigo
            for (Persona p: lista){
                if (p.d.equals(dni)) {
                    System.out.println("✖ Error: DNI repetido.");
                    return;
                }
            }

            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese apellidos: ");
            String apellidos = sc.nextLine();
            System.out.print("Ingrese edad: ");
            int edad = sc.nextInt();
            System.out.print("Ingrese talla: ");
            float talla = (float) sc.nextDouble();
            sc.nextLine();

            lista.add(new Persona(nombre, apellidos, dni, edad, talla));
            System.out.println("Persona agregada.");

            System.out.print("¿Agregar otra persona? ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }

    static void eliminar() {
        String continuar;
        do {
            System.out.print("Ingrese DNI a eliminar: ");
            String dni = sc.nextLine();
            Persona personaEncontrada = null;

            for (Persona p : lista) {
                if (p.d.equals(dni)) {
                    personaEncontrada = p;
                    break;
                }
            }

            if (personaEncontrada != null) {
                lista.remove(personaEncontrada);
                System.out.println("Persona eliminada.");
            } else {
                System.out.println("No encontrada.");
            }

            System.out.print("¿Eliminar otra persona?");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }

    static void editar() {
        String continuar;
        do {
            System.out.print("Ingrese DNI a editar: ");
            String dni = sc.nextLine();
            Persona personaEncontrada = null;

            for (Persona p : lista) {
                if (p.d.equals(dni)) {
                    personaEncontrada = p;
                    break;
                }
            }

            if (personaEncontrada != null) {
                System.out.print("Nuevo nombre: ");
                personaEncontrada.n = sc.nextLine();
                System.out.print("Nuevos apellidos: ");
                personaEncontrada.a = sc.nextLine();
                System.out.print("Nueva edad: ");
                personaEncontrada.e = sc.nextInt();
                System.out.print("Nueva talla: ");
                personaEncontrada.t = sc.nextDouble();
                sc.nextLine();

                System.out.println("Persona editada.");
            } else {
                System.out.println("No encontrada.");
            }

            System.out.print("¿Editar otra persona?");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
    }

    static void listar() {
        if (lista.isEmpty()) {
            System.out.println("No hay personas.");
        } else {
            System.out.println("\n--- Lista de Personas ---");
            System.out.printf("%-10s %-15s %-10s %-5s %-6s\n",
                    "Nombre", "Apellidos", "DNI", "Edad", "Talla");
            System.out.println("***********************************************");
            for (Persona p : lista) {
                System.out.println(p);
            }
        }
    }

    static void buscar() {
        System.out.print("Ingrese búsqueda (use * como comodín): ");
        String criterio = sc.nextLine().toLowerCase();

        boolean encontrado = false;
        for (Persona p : lista) {
            String nombre = (String) p.n;
            String apellidos = (String) p.a;

            // Buscar: empieza con, termina con o contiene
            if ((criterio.startsWith("*") && criterio.endsWith("*") &&
                 (nombre.contains(criterio.replace("*", "")) ||
                  apellidos.contains(criterio.replace("*", ""))))
             || (criterio.startsWith("*") &&
                 (nombre.endsWith(criterio.replace("*", "")) ||
                  apellidos.endsWith(criterio.replace("*", ""))))
             || (criterio.endsWith("*") &&
                 (nombre.startsWith(criterio.replace("*", "")) ||
                  apellidos.startsWith(criterio.replace("*", ""))))) {
                System.out.println(p);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron coincidencias.");
        }
    }
}
