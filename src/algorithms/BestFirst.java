package algorithms;

import states.*;
import heuristics.*;

import java.util.*;

/**
 * Implementación del algoritmo Best-First Search para la búsqueda de caminos en
 * un mapa representado por una matriz de estados.
 */
public class BestFirst extends Algorithm {

    /**
     * Ejecuta el algoritmo Best-First Search para encontrar un camino desde el
     * estado inicial hasta el estado objetivo.
     *
     * @param map          Matriz de estados que representa el mapa.
     * @param ini          Estado inicial desde donde comienza la búsqueda.
     * @param end          Estado objetivo al que se desea llegar.
     * @param h            Heurística utilizada para calcular la prioridad de los
     *                     estados.
     * @param goalPosition Posición del objetivo final.
     */
    public void bestFirst(State[][] map, State ini, State end, Heuristic h, Position goalPosition) {
        // Cola de prioridad para manejar los estados pendientes, ordenados por el valor
        // heurístico
        PriorityQueue<State> pending = new PriorityQueue<>(Comparator.comparingDouble(st -> h.checkStates(st, end)));
        // Conjunto para almacenar los estados ya visitados y evitar procesarlos
        // nuevamente
        Set<State> visited = new HashSet<>();

        boolean alreadyPrinted = false;
        @SuppressWarnings("unused")
        boolean found = false;

        pending.add(ini); // Agrega el estado inicial a la cola de prioridad
        visited.add(ini); // Marca el estado inicial como visitado

        // Bucle principal de búsqueda
        while (!pending.isEmpty()) {
            State st = pending.poll(); // Extrae el estado con el menor valor heurístico

            // Si se alcanza el estado objetivo, se finaliza la búsqueda
            if (st.getPosition().cmp(end.getPosition())) {
                found = true;
                printResults("Best First", st, new ArrayList<>(visited), h, map, true, alreadyPrinted, goalPosition);
                return;
            }

            // Explora los estados vecinos del estado actual
            for (State neighbour : succesors(st, map)) {
                if (visited.add(neighbour)) { // Solo se añade si no ha sido visitado antes
                    pending.add(neighbour); // Agrega el vecino a la cola de prioridad
                }
            }
        }

        // Si no se encuentra solución, imprimir los resultados con el estado inicial
        printResults("Best First", ini, new ArrayList<>(visited), h, map, false, alreadyPrinted, goalPosition);
    }
}
