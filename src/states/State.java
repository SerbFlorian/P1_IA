package states;

import heuristics.Heuristic;
import java.util.ArrayList;

/**
 * La clase `State` representa un estado dentro de un algoritmo de búsqueda en
 * un mapa o cuadrícula.
 * Cada estado tiene una posición, un costo asociado, un camino hacia el estado
 * inicial, y un nodo previo en el camino óptimo.
 * Esta clase también maneja la lógica de cálculo de costos y tiempos en el
 * algoritmo.
 */
public class State {
    private int height; // Altura del nodo en el mapa (usado para evaluar costos)
    private Position position; // Posición del nodo en la cuadrícula
    private ArrayList<State> path; // Camino desde el nodo inicial hasta este nodo
    private State previous; // Nodo anterior en el camino óptimo
    @SuppressWarnings("unused")
    private double f, g; // Valores F (costo total estimado) y G (costo desde el nodo inicial)
    private double time; // Tiempo acumulado de tránsito (usado como costo en este caso)

    /**
     * Constructor de la clase `State`.
     * Inicializa la altura, la posición y establece valores predeterminados para
     * los atributos del estado.
     * 
     * @param height La altura del nodo.
     * @param pos    La posición del nodo en la cuadrícula.
     */
    public State(int height, Position pos) {
        this.height = height;
        this.position = pos;
        this.path = new ArrayList<>();
        this.time = 0;
    }

    // Getters y Setters
    /**
     * Obtiene la altura del nodo.
     * 
     * @return La altura del nodo.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Establece la altura del nodo.
     * 
     * @param height La nueva altura para el nodo.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Obtiene la posición del nodo.
     * 
     * @return La posición del nodo.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Establece la posición del nodo.
     * 
     * @param position La nueva posición para el nodo.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Obtiene el camino recorrido desde el nodo inicial hasta este nodo.
     * 
     * @return Una lista de los nodos que conforman el camino.
     */
    public ArrayList<State> getPath() {
        return path;
    }

    /**
     * Obtiene el nodo previo en el camino óptimo.
     * 
     * @return El nodo anterior en el camino.
     */
    public State getPrevious() {
        return previous;
    }

    /**
     * Asigna el nodo previo solo si mejora el costo total.
     * Calcula el costo desde el nodo actual hasta el nodo anterior y actualiza el
     * camino
     * y el tiempo si es más eficiente.
     * 
     * @param previous El nodo previo en el camino.
     */
    public void setPrevious(State previous) {
        if (this.previous == null || previous.getCost() + calculateStepTime(previous) < this.getCost()) {
            this.previous = previous;
            setPath(); // Se actualiza el camino con la nueva ruta óptima
            setTime(); // Se recalcula el tiempo total
        }
    }

    /**
     * Establece el camino óptimo hasta este nodo.
     * Actualiza la lista de nodos en el camino, empezando desde el nodo inicial.
     */
    public void setPath() {
        this.path.clear(); // Eliminar caminos previos
        if (this.previous != null) {
            this.path.addAll(this.previous.getPath()); // Copiar el camino previo
            this.path.add(this.previous); // Añadir el nodo anterior
        }
    }

    /**
     * Calcula el valor G, que representa la distancia Manhattan desde el nodo
     * inicial hasta el nodo actual.
     * 
     * @param ini El nodo inicial desde el cual calcular la distancia.
     */
    public void calculateG(State ini) {
        this.g = Math.abs(ini.getPosition().getRow() - this.getPosition().getRow()) +
                Math.abs(ini.getPosition().getColumn() - this.getPosition().getColumn());
    }

    /**
     * Obtiene el valor F del nodo.
     * 
     * @return El valor F, que es la suma de G y el valor de la heurística.
     */
    public double getF() {
        return this.f;
    }

    /**
     * Inicializa el valor F del nodo con el valor de la altura.
     * Este valor será modificado más tarde según los cálculos de G y la heurística.
     */
    public void setFirstF() {
        this.f = this.getHeight();
    }

    /**
     * Calcula el valor F utilizando el valor G y la heurística proporcionada.
     * 
     * @param g   El valor G, que es el costo desde el nodo inicial.
     * @param h   La heurística utilizada para estimar el costo al objetivo.
     * @param end El estado final (objetivo) del problema.
     */
    public void setF(double g, Heuristic h, State end) {
        this.f = g + h.checkStates(this, end);
    }

    /**
     * Obtiene el tiempo acumulado de tránsito desde el nodo inicial.
     * Este tiempo se usa como el costo en este caso.
     * 
     * @return El tiempo acumulado de tránsito.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Obtiene el costo del nodo, que en este caso es igual al tiempo acumulado de
     * tránsito.
     * 
     * @return El costo del nodo (tiempo acumulado).
     */
    public double getCost() {
        return this.time;
    }

    /**
     * Calcula el tiempo de transición entre el nodo actual y el nodo anterior.
     * La penalización se aplica si el nodo actual está más alto que el anterior.
     * 
     * @param prev El nodo anterior en el camino.
     * @return El tiempo de transición entre los nodos.
     */
    private double calculateStepTime(State prev) {
        if (prev == null)
            return 0; // Nodo inicial no tiene tiempo de transición
        double diff = this.height - prev.getHeight(); // Diferencia de altura
        return (diff >= 0) ? (1 + diff) : 0.5; // Penalización por subida, beneficio por bajada
    }

    /**
     * Recalcula el tiempo total acumulado desde el nodo inicial hasta este nodo.
     * Si el nodo no tiene un nodo previo, el tiempo es 0.
     */
    public void setTime() {
        if (this.previous == null) {
            this.time = 0; // Si no hay nodo anterior, el tiempo es 0
        } else {
            this.time = this.previous.getTime() + calculateStepTime(this.previous); // Sumar el tiempo acumulado
        }
    }

    /**
     * Reinicia el tiempo acumulado a 0.
     * Este método es útil para la revaluación de tiempos en ciertos algoritmos.
     */
    public void resetTime() {
        this.time = 0;
    }
}
