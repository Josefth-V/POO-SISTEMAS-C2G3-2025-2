import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SepararParesImpares {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        Queue<Integer> colaPares = new LinkedList<>();
        Queue<Integer> colaImpares = new LinkedList<>();

        System.out.print("¿Cuántos números desea ingresar?: ");
        int cantidad = entrada.nextInt();

        for (int i = 1; i <= cantidad; i++) {
            System.out.print("Ingrese el número " + i + ": ");
      
    }
}
