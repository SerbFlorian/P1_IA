package heuristics;

import states.Position;
import states.State;

/**
 * Implementación de una heurística que combina la distancia euclidiana y la
 * diferencia
 * máxima de altura entre el estado actual y el estado objetivo. Esta heurística
 * es útil
 * en problemas donde tanto la posición como la altura son factores importantes
 * para
 * determinar el costo estimado.
 */
public class HeuristicEuclideanMaxHeight implements Heuristic {
    private final int goalRow; // Fila del estado objetivo
    private final int goalColumn; // Columna del estado objetivo
    private final double goalHeight; // Altura del estado objetivo

    /**
     * Constructor que inicializa la heurística con el estado objetivo.
     *
     * @param goal Estado objetivo al que se desea llegar.
     */
    public HeuristicEuclideanMaxHeight(State goal) {
        Position goalPosition = goal.getPosition();
        this.goalRow = goalPosition.getRow(); // Obtiene la fila del objetivo
        this.goalColumn = goalPosition.getColumn(); // Obtiene la columna del objetivo
        this.goalHeight = goal.getHeight(); // Obtiene la altura del objetivo
    }

    /**
     * Compara dos estados según su valor heurístico. Este método es utilizado para
     * ordenar los estados en una cola de prioridad.
     *
     * @param st1 Primer estado a comparar.
     * @param st2 Segundo estado a comparar.
     * @return Un valor negativo si st1 tiene un valor heurístico menor que st2,
     *         cero
     *         si son iguales, o un valor positivo si st1 tiene un valor heurístico
     *         mayor.
     */
    @Override
    public int compare(State st1, State st2) {
        double h1 = calculateHeuristic(st1); // Calcula la heurística para st1
        double h2 = calculateHeuristic(st2); // Calcula la heurística para st2
        return Double.compare(h1, h2); // Compara los valores heurísticos
    }

    /**
     * Calcula el valor heurístico de un estado. Este método es utilizado para
     * obtener
     * el costo estimado desde un estado dado hasta el estado objetivo.
     *
     * @param o1 Estado para el cual se calcula la heurística.
     * @return Valor heurístico del estado.
     */
    @Override
    public double checkStates(State o1, State o2) {
        return calculateHeuristic(o1); // Retorna la heurística del estado o1
    }

    /**
     * Calcula la heurística para un estado dado. La heurística combina la distancia
     * euclidiana entre la posición actual y la posición objetivo, y la diferencia
     * máxima de altura entre la altura actual y la altura objetivo.
     *
     * @param state Estado para el cual se calcula la heurística.
     * @return Valor heurístico del estado.
     */
    private double calculateHeuristic(State state) {
        Position pos = state.getPosition();
        // Distancia Euclidiana: sqrt((x_actual - x_final)^2 + (y_actual - y_final)^2)
        double euclideanDistance = Math.sqrt(
                Math.pow(goalRow - pos.getRow(), 2) + Math.pow(goalColumn - pos.getColumn(), 2));

        // Diferencia máxima de altura: |altura_actual - altura_final|
        double maxHeightDifference = Math.abs(goalHeight - state.getHeight());

        // Heurística: h(n) = distancia_euclidiana + diferencia_máxima_de_altura
        return euclideanDistance + maxHeightDifference;
    }

    /**
     * Retorna el tipo de heurística. Este método es útil para identificar la
     * heurística
     * utilizada en el algoritmo.
     *
     * @return Entero que representa el tipo de heurística.
     */
    @Override
    public int getType() {
        return 2; // Identificador de la heurística Euclidiana con altura máxima
    }
}