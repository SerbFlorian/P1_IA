package heuristics;

import states.Position;
import states.State;

/**
 * Heurística 2 - Distancia de Manhattan.
 * 
 * La heurística de Manhattan calcula la distancia entre un estado y la meta
 * utilizando la fórmula:
 * 
 * h(n) = |X_destino - X_actual| + |Y_destino - Y_actual|
 * 
 * Esto se realiza calculando la diferencia absoluta entre las coordenadas (fila
 * y columna) del estado actual y las del destino.
 */
public class HeuristicManhattan implements Heuristic {
    // Coordenadas de la meta (fila y columna)
    private final int goalRow;
    private final int goalColumn;

    /**
     * Constructor de la heurística Manhattan.
     * 
     * Guarda las coordenadas de la meta para evitar accesos repetidos durante las
     * comparaciones.
     * 
     * @param goal La posición del estado objetivo.
     */
    public HeuristicManhattan(Position goal) {
        // Guardamos las coordenadas de la meta para optimizar los cálculos
        this.goalRow = goal.getRow();
        this.goalColumn = goal.getColumn();
    }

    /**
     * Compara dos estados basándose en la heurística de distancia Manhattan.
     * 
     * La comparación se realiza calculando la distancia Manhattan de ambos estados
     * al objetivo y
     * comparando estas distancias.
     * 
     * @param st1 El primer estado a comparar.
     * @param st2 El segundo estado a comparar.
     * @return Un valor negativo si la distancia de Manhattan de st1 es menor que la
     *         de st2,
     *         0 si son iguales, o un valor positivo si la distancia de st1 es mayor
     *         que la de st2.
     */
    @Override
    public int compare(State st1, State st2) {
        // Obtenemos las posiciones de los dos estados solo una vez
        Position pos1 = st1.getPosition();
        Position pos2 = st2.getPosition();

        // Calculamos las distancias Manhattan para ambos estados
        int dist1 = Math.abs(goalRow - pos1.getRow()) + Math.abs(goalColumn - pos1.getColumn());
        int dist2 = Math.abs(goalRow - pos2.getRow()) + Math.abs(goalColumn - pos2.getColumn());

        // Comparamos las distancias calculadas
        return Integer.compare(dist1, dist2);
    }

    /**
     * Calcula la distancia Manhattan entre dos estados dados.
     * 
     * La distancia Manhattan se calcula como la suma de las diferencias absolutas
     * entre las coordenadas de fila y columna de los dos estados.
     * 
     * @param o1 El primer estado a comprobar.
     * @param o2 El segundo estado a comprobar.
     * @return La distancia Manhattan entre las posiciones de los dos estados.
     */
    @Override
    public double checkStates(State o1, State o2) {
        // Obtenemos las posiciones de los dos estados solo una vez
        Position pos1 = o1.getPosition();
        Position pos2 = o2.getPosition();

        // Calculamos y devolvemos la distancia Manhattan
        return Math.abs(pos2.getRow() - pos1.getRow()) + Math.abs(pos2.getColumn() - pos1.getColumn());
    }

    /**
     * Devuelve el tipo de heurística implementada.
     * En este caso, el tipo de heurística es 2, que corresponde a la heurística
     * Manhattan.
     * 
     * @return El valor 2, que representa la heurística de distancia Manhattan.
     */
    @Override
    public int getType() {
        return 2; // Identificador de la heurística Manhattan
    }
}
