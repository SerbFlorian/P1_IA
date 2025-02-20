package heuristics;

import states.Position;
import states.State;

/**
 * Heurística Combinada (Manhattan + Altura Ponderada).
 * 
 * Esta heurística combina la distancia de Manhattan y la altura de un estado
 * para calcular su valor heurístico.
 * La fórmula de la heurística es:
 * 
 * h(n) = α * Manhattan(n) + β * Height(n)
 * 
 * donde α = 1.0 y β = 0.5 son factores de ponderación.
 */
public class HeuristicCombined implements Heuristic {
    // Factor de ponderación para la distancia Manhattan
    private static final double ALPHA = 1.0;
    // Factor de ponderación para la altura
    private static final double BETA = 0.5;

    // Coordenadas de la meta (fila y columna)
    private final int goalRow;
    private final int goalColumn;

    /**
     * Constructor de la heurística combinada.
     * 
     * Se guarda la posición de la meta para evitar cálculos repetidos durante las
     * comparaciones.
     * 
     * @param goal La posición del estado objetivo.
     */
    public HeuristicCombined(Position goal) {
        // Guardamos las coordenadas de la meta (fila y columna) para optimizar los
        // cálculos
        this.goalRow = goal.getRow();
        this.goalColumn = goal.getColumn();
    }

    /**
     * Compara dos estados basándose en la heurística combinada (Manhattan + Altura
     * Ponderada).
     * 
     * @param o1 El primer estado a comparar.
     * @param o2 El segundo estado a comparar.
     * @return Un valor negativo si la heurística del estado o1 es menor que la de
     *         o2,
     *         0 si son iguales, o un valor positivo si la heurística de o1 es mayor
     *         que la de o2.
     */
    @Override
    public int compare(State o1, State o2) {
        // Obtenemos las posiciones de los dos estados solo una vez
        Position pos1 = o1.getPosition();
        Position pos2 = o2.getPosition();

        // Calculamos la distancia Manhattan para ambos estados
        int manhattan1 = Math.abs(goalRow - pos1.getRow()) + Math.abs(goalColumn - pos1.getColumn());
        int manhattan2 = Math.abs(goalRow - pos2.getRow()) + Math.abs(goalColumn - pos2.getColumn());

        // Calculamos la heurística para ambos estados (Manhattan + Altura ponderada)
        double dist1 = ALPHA * manhattan1 + BETA * o1.getHeight();
        double dist2 = ALPHA * manhattan2 + BETA * o2.getHeight();

        // Comparamos las heurísticas calculadas
        return Double.compare(dist1, dist2);
    }

    /**
     * Calcula la heurística combinada entre dos estados dados.
     * Este valor es la distancia Manhattan ponderada por la constante α y la altura
     * ponderada por la constante β.
     * 
     * @param o1 El primer estado a comprobar.
     * @param o2 El segundo estado a comprobar (no se utiliza en este caso, solo se
     *           usa o1).
     * @return El valor de la heurística combinada para el estado o1.
     */
    @Override
    public double checkStates(State o1, State o2) {
        // Obtenemos las posiciones de los dos estados solo una vez
        Position pos1 = o1.getPosition();
        Position pos2 = o2.getPosition();

        // Calculamos la distancia Manhattan entre los dos estados
        int manhattan = Math.abs(pos2.getRow() - pos1.getRow()) + Math.abs(pos2.getColumn() - pos1.getColumn());

        // Calculamos la heurística combinada (Manhattan + Altura ponderada)
        return ALPHA * manhattan + BETA * o1.getHeight();
    }

    /**
     * Devuelve el tipo de heurística implementada.
     * En este caso, el tipo de heurística es 4, que corresponde a la heurística
     * combinada.
     * 
     * @return El valor 4, que representa la heurística combinada.
     */
    @Override
    public int getType() {
        return 4; // Identificador de la heurística combinada
    }
}
