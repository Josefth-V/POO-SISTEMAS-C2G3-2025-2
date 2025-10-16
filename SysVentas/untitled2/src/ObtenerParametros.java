import java.util.ArrayList;

public class ObtenerParametros {

    public static ArrayList<String> obtenerValores(String url) {
        ArrayList<String> valores = new ArrayList<>();

        int indice = url.indexOf("?");
        if (indice == -1) return valores;


        String parametros = url.substring(indice + 1);

        String[] pares = parametros.split("&");

        for (String par : pares) {
            int posIgual = par.indexOf("=");
            if (posIgual != -1 && posIgual < par.length() - 1) {
                valores.add(par.substring(posIgual + 1));
            }
        }

        return valores;
    }

    public static void main(String[] args) {
        String url = "https://retosdeprogramacion.com?year=2023&challenge=0";
        System.out.println(obtenerValores(url));
    }
}
