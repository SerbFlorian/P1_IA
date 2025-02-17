import models.Mapa;
import models.Estado;
import utils.LectorMapa;
import heuristics.*;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "data/mapa1.txt";

        try {
            // Leer el mapa desde el archivo
            Mapa mapa = LectorMapa.leerMapaDesdeArchivo(rutaArchivo);

            // Obtener el estado inicial y final
            Estado inicio = mapa.getEstadoInicial();
            Estado fin = mapa.getEstadoFinal();

            System.out.println("\nInicio: " + inicio);
            System.out.println("Destino: " + fin);

            // Definir las heurísticas
            Heuristica manhattan = new Manhattan();
            Heuristica altura = new Altura();
            Heuristica combinada = new Combinada();

            // Calcular las heurísticas para el estado inicial
            double hManhattan = manhattan.calcular(inicio, mapa);
            double hAltura = altura.calcular(inicio, mapa);
            double hCombinada = combinada.calcular(inicio, mapa);

            System.out.println("\nHeurísticas para el estado inicial:");
            System.out.println("Manhattan: " + hManhattan);
            System.out.println("Altura: " + hAltura);
            System.out.println("Combinada: " + hCombinada);

            // Simulación del cálculo del camino (aquí deberías usar el resultado de tu
            // algoritmo)
            List<Estado> camino = new ArrayList<>();
            camino.add(inicio); // Agregar el estado inicial

            // Aquí deberías agregar los estados calculados por el algoritmo (ej. A* o
            // Best-First)
            // camino.add(algúnEstadoCalculado);

            // Mostrar el mapa con el camino resaltado
            System.out.println("\nMapa con camino resaltado:");
            mapa.imprimirMapaConCamino(camino);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
