import java.util.Scanner;

public class Main {

    // Función para sumar
    public static double sumar(double a, double b) {
        return a + b;
    }

    // Función para restar
    public static double restar(double a, double b) {
        return a - b;
    }

    // Función para multiplicar
    public static double multiplicar(double a, double b) {
        return a * b;
    }

    // Función para dividir
    public static double dividir(double a, double b) {
        if (b != 0) {
            return a / b;
        } else {
            System.out.println(" Error: no se puede dividir entre 0");
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Ingrese el primer número: ");
        double num1 = sc.nextDouble();

        System.out.print("Ingrese el segundo número: ");
        double num2 = sc.nextDouble();


        System.out.println("\n--- Calculadora ---");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.print("Elija una opción: ");
        int opcion = sc.nextInt();

        double resultado = 0;


        if (opcion == 1) {
            resultado = sumar(num1, num2);
        } else if (opcion == 2) {
            resultado = restar(num1, num2);
        } else if (opcion == 3) {
            resultado = multiplicar(num1, num2);
        } else if (opcion == 4) {
            resultado = dividir(num1, num2);
        } else {
            System.out.println("Opción no válida");
            return;
        }

        System.out.println(" El resultado es: " + resultado);
    }
}



















