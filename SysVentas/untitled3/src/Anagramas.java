import java.util.Arrays;

public class Anagramas {

    public static boolean sonAnagramas(String palabra1, String palabra2) {

        palabra1 = palabra1.replaceAll("\\s+", "").toLowerCase();
        palabra2 = palabra2.replaceAll("\\s+", "").toLowerCase();

        if (palabra1.length() != palabra2.length()) {
            return false;
        }

        char[] arreglo1 = palabra1.toCharArray();
        char[] arreglo2 = palabra2.toCharArray();

        Arrays.sort(arreglo1);
        Arrays.sort(arreglo2);

        return Arrays.equals(arreglo1, arreglo2);
    }

    public static void main(String[] args) {
        String palabraA = "congreso";
        String palabraB = "sogrecon";

        System.out.println(sonAnagramas(palabraA, palabraB));
    }
}
