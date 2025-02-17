import models.Mapa;
import models.Estado;
import utils.LectorMapa;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "data/mapa1.txt";

        try {
            Mapa mapa = LectorMapa.leerMapaDesdeArchivo(rutaArchivo);
            mapa.imprimirMapa();

            Estado inicio = mapa.getEstadoInicial();
            Estado fin = mapa.getEstadoFinal();

            System.out.println("Inicio: " + inicio);
            System.out.println("Destino: " + fin);

            List<Estado> vecinos = mapa.generarVecinos(inicio);
            System.out.println("\nEstados vecinos del inicio:");
            for (Estado vecino : vecinos) {
                System.out.println(vecino);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
