package algorithms;

import heuristics.Heuristic;
import states.*;

import java.util.*;

/**
 * Clase abstracta que define la estructura base para los algoritmos de
 * búsqueda. Proporciona métodos comunes para la generación de sucesores,
 * verificación de estados, y visualización de resultados.
 */
public abstract class Algorithm {
    /* Colores ANSI para la impresión en consola */
    public static final String ANSI_BLUE = "\u001B[34m"; // Azul para el camino
    public static final String ANSI_RESET = "\u001B[0m"; // Restablecer color
    public static final String ANSI_GREEN = "\u001B[32m"; // Verde para la posición inicial
    public static final String ANSI_PINK = "\u001B[35m"; // Rosa para la posición final
    public static final String ANSI_RED = "\u001B[31m"; // Rojo para los obstáculos ('X')

    /**
     * Constructor vacío de la clase Algorithm.
     */
    public Algorithm() {
    }

    /**
     * Verifica si un estado está contenido en un conjunto de estados.
     *
     * @param state Estado a verificar.
     * @param arr   Conjunto de estados.
     * @return true si el estado está en el conjunto, false en caso contrario.
     */
    public Boolean contiene(State state, Set<State> arr) {
        return arr.contains(state);
    }

    /**
     * Genera una lista de estados sucesores a partir de un estado dado en la matriz
     * del mapa. Los sucesores son los estados adyacentes (arriba, abajo, izquierda,
     * derecha)
     * que no son obstáculos.
     *
     * @param st     Estado actual.
     * @param matriz Matriz del mapa con los estados.
     * @return Lista de estados sucesores.
     */
    public ArrayList<State> succesors(State st, State[][] matriz) {
        ArrayList<State> states = new ArrayList<>();
        int x = st.getPosition().getRow();
        int y = st.getPosition().getColumn();
        int size = matriz.length - 1;

        // Comprobación de los posibles movimientos (arriba, abajo, izquierda, derecha)
        if (x > 0 && !fall(matriz[x - 1][y])) {
            State state = matriz[x - 1][y];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }
        if (x < size && !fall(matriz[x + 1][y])) {
            State state = matriz[x + 1][y];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }
        if (y > 0 && !fall(matriz[x][y - 1])) {
            State state = matriz[x][y - 1];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }
        if (y < size && !fall(matriz[x][y + 1])) {
            State state = matriz[x][y + 1];
            state.setPrevious(st);
            state.setPath();
            states.add(state);
        }

        return states;
    }

    /**
     * Verifica si un estado representa un obstáculo o una casilla no transitable.
     * Un estado se considera obstáculo si su altura es menor o igual a -999.
     *
     * @param st Estado a evaluar.
     * @return true si la casilla es un obstáculo, false en caso contrario.
     */
    private Boolean fall(State st) {
        return st.getHeight() <= -999;
    }

    /**
     * Imprime los resultados de la ejecución del algoritmo, incluyendo el número
     * de nodos tratados, el coste del camino encontrado, y el camino en sí.
     *
     * @param algoritmo Nombre del algoritmo utilizado.
     * @param st        Estado final alcanzado.
     * @param treated   Lista de nodos tratados.
     * @param h         Heurística utilizada.
     * @param map       Matriz del mapa.
     * @param found     Indica si se encontró una solución.
     */
    public void printResults(String algoritmo, State st, ArrayList<State> treated, Heuristic h, State[][] map,
            boolean found) {
        System.out.println("Número de nodos tratados: " + treated.size());
        System.out.println("Coste: " + st.getTime());
        if (found) {
            mostrarCamino(st.getPath(), map, st.getPosition());
        } else {
            System.out.println("No se ha podido encontrar el camino");
        }
    }

    /**
     * Muestra el camino encontrado en la matriz del mapa, resaltando posiciones
     * clave como la posición inicial, final y los obstáculos.
     *
     * @param path Lista de estados que forman el camino encontrado.
     * @param map  Matriz del mapa.
     * @param end  Posición final.
     */
    public void mostrarCamino(ArrayList<State> path, State[][] map, Position end) {
        if (path != null && !path.isEmpty()) {
            System.out.println("Camino: ");
            for (int x = 0; x < map.length; x++) {
                System.out.print("|");
                for (int y = 0; y < map[x].length; y++) {
                    String data;
                    int value = map[x][y].getHeight();
                    if (value == -999) {
                        data = ANSI_RED + "X" + ANSI_RESET;
                    } else {
                        data = String.valueOf(value);
                    }

                    State position = checkPosition(path, map[x][y]);

                    if (map[x][y].getPosition().equals(path.get(0).getPosition())) {
                        System.out.print(ANSI_GREEN + data + ANSI_RESET);
                    } else if (map[x][y].getPosition().equals(end)) {
                        System.out.print(ANSI_PINK + data + ANSI_RESET);
                    } else if (position != null) {
                        System.out.print(ANSI_BLUE + data + ANSI_RESET);
                    } else {
                        System.out.print(data);
                    }

                    if (y != map[x].length - 1) {
                        System.out.print(" ");
                    }
                }
                System.out.println("|");
            }
        } else {
            System.out.println("No hay camino!");
        }
    }

    /**
     * Verifica si una posición pertenece a la lista del camino.
     *
     * @param path  Lista de estados en el camino.
     * @param state Estado a verificar.
     * @return El estado si está en el camino, null en caso contrario.
     */
    public static State checkPosition(ArrayList<State> path, State state) {
        for (State s : path) {
            if (s.getPosition().equals(state.getPosition())) {
                return s;
            }
        }
        return null;
    }
}