package algorithms;

import heuristics.Heuristic;
import states.*;
import java.util.*;

/**
 * Implementación del algoritmo A* para la búsqueda de caminos en un mapa
 * representado por una matriz de estados.
 */
public class Astar extends Algorithm {

    /**
     * Ejecuta el algoritmo A* para encontrar el camino más corto desde un estado
     * inicial hasta un estado objetivo.
     *
     * @param map          Matriz de estados que representa el mapa.
     * @param ini          Estado inicial desde donde comienza la búsqueda.
     * @param end          Estado objetivo al que se desea llegar.
     * @param heuristic    Heurística utilizada para calcular el costo estimado al
     *                     objetivo.
     * @param goalPosition Posición del objetivo final.
     */
    public void astar(State[][] map, State ini, State end, Heuristic heuristic, Position goalPosition) {
        // Cola de prioridad para manejar los estados pendientes, ordenados por el costo
        // estimado F
        PriorityQueue<State> pending = new PriorityQueue<>(Comparator.comparingDouble(State::getF));
        // Mapa para almacenar los mejores costos encontrados para cada estado
        Map<State, Double> bestCosts = new HashMap<>();
        // Bandera para controlar la impresión de resultados
        boolean alreadyPrinted = false;

        // Inicialización del estado inicial
        ini.setFirstF(); // Calcula el valor inicial de F
        ini.setTime(); // Establece el tiempo inicial sin argumentos
        bestCosts.put(ini, ini.getTime()); // Guarda el costo inicial en el mapa de mejores costos
        pending.add(ini); // Agrega el estado inicial a la cola de prioridad

        boolean found = false; // Bandera para indicar si se ha encontrado el objetivo

        // Bucle principal de búsqueda
        while (!found && !pending.isEmpty()) {
            State st = pending.poll(); // Extrae el estado con el menor costo estimado F

            // Si se alcanza el estado objetivo, se finaliza la búsqueda
            if (st.getPosition().cmp(end.getPosition())) {
                found = true;
                if (!alreadyPrinted) {
                    printResults("A*", st, new ArrayList<>(bestCosts.keySet()), heuristic, map, found, alreadyPrinted,
                            goalPosition);
                    alreadyPrinted = true;
                }
                break;
            }

            // Explora los estados vecinos del estado actual
            for (State neighbour : succesors(st, map)) {
                double newCost = st.getTime() + neighbour.getCost(); // Calcula el nuevo costo acumulado

                // Si el vecino no ha sido visitado o se encuentra un mejor costo, se actualiza
                if (!bestCosts.containsKey(neighbour) || newCost < bestCosts.get(neighbour)) {
                    neighbour.setPrevious(st); // Establece el estado anterior para reconstruir el camino
                    neighbour.setTime(); // Actualiza el tiempo del estado vecino
                    neighbour.setF(newCost, heuristic, end); // Calcula el nuevo valor F usando la heurística
                    bestCosts.put(neighbour, newCost); // Guarda el nuevo costo en el mapa de mejores costos
                    pending.add(neighbour); // Añade el vecino a la cola de prioridad
                }
            }
        }

        // Si el objetivo no fue encontrado, se imprimen los resultados con el estado
        // inicial
        if (!alreadyPrinted) {
            printResults("A*", ini, new ArrayList<>(bestCosts.keySet()), heuristic, map, found, alreadyPrinted,
                    goalPosition);
        }
    }
}