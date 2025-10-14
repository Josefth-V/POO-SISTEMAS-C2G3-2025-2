public class CapitalizarPalabras {

    public static String capitalizar(String texto) {
        String resultado = "";
        boolean nuevaPalabra = true;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (nuevaPalabra && c >= 'a' && c <= 'z') {

                c = (char) (c - ('a' - 'A'));
                nuevaPalabra = false;
            } else if (c == ' ') {
                nuevaPalabra = true;
            } else {
                nuevaPalabra = false;
            }

            resultado += c;
        }

        return resultado; // 👈 faltaba esto
    }

    public static void main(String[] args) {
        String texto = "hola mundo desde java";
        System.out.println(capitalizar(texto)); // → Hola Mundo Desde Java
    }
}
