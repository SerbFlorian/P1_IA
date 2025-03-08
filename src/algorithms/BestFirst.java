package algorithms;

import states.*;
import heuristics.*;

import java.util.*;

/**
 * Implementación simplificada y optimizada del algoritmo Best-First Search.
 */
public class BestFirst extends Algorithm {

    /**
     * Ejecuta el algoritmo Best-First Search para encontrar un camino desde el
     * estado inicial hasta el estado objetivo.
     *
     * @param map Matriz de estados que representa el mapa.
     * @param ini Estado inicial desde donde comienza la búsqueda.
     * @param end Estado objetivo al que se desea llegar.
     * @param h   Heurística utilizada para calcular la prioridad de los estados.
     */
    public void bestFirst(State[][] map, State ini, State end, Heuristic h) {
        // Lista para manejar los estados pendientes
        ArrayList<State> pending = new ArrayList<>();

        // Lista para almacenar los estados ya tratados
        ArrayList<State> treated = new ArrayList<>();

        pending.add(ini); // Agrega el estado inicial a la lista de pendientes
        boolean found = false;

        // Bucle principal de búsqueda
        while (!found && !pending.isEmpty()) {
            // Extrae el estado con el menor valor heurístico (el primero en la lista
            // ordenada)
            State st = pending.get(0);
            pending.remove(0);

            // Si se alcanza el estado objetivo, se finaliza la búsqueda
            if (st.getPosition().cmp(end.getPosition())) {
                found = true;
                st.setTime(); // Calcula el tiempo acumulado
                printResults("Best First", st, treated, h, map, found);
                st.resetTime(); // Reinicia el tiempo para futuras búsquedas
                return;
            }

            // Explora los estados vecinos del estado actual
            for (State neighbour : succesors(st, map)) {
                if (!treated.contains(neighbour) && !pending.contains(neighbour)) {
                    pending.add(neighbour); // Agrega el vecino a la lista de pendientes
                }
            }

            // Ordena la lista de pendientes según la heurística
            Collections.sort(pending, Comparator.comparingDouble(state -> h.checkStates(state, end)));

            treated.add(st); // Marca el estado actual como tratado
        }

        // Si no se encuentra solución, imprimir los resultados con el estado inicial
        printResults("Best First", ini, treated, h, map, found);
    }
}