package heuristics;

import states.*;

/**
 * Heurística de Diferencia de Altura.
 * 
 * Esta heurística calcula la diferencia de altura entre el estado actual y el
 * estado objetivo.
 * Se utiliza comúnmente en problemas de búsqueda donde la altura es una métrica
 * relevante para la solución.
 */
public class HeuristicAltura implements Heuristic {

    // Altura del estado objetivo para realizar cálculos rápidos sin necesidad de
    // recalcularla
    private final double goalHeight;

    /**
     * Constructor de la heurística que guarda la altura del objetivo para evitar
     * cálculos repetidos.
     * 
     * @param goal El estado objetivo cuyo valor de altura se utilizará en los
     *             cálculos.
     */
    public HeuristicAltura(State goal) {
        // Guardamos la altura del objetivo para optimizar el cálculo en futuras
        // comparaciones
        this.goalHeight = goal.getHeight();
    }

    /**
     * Compara dos estados basándose en la diferencia de altura con el estado
     * objetivo.
     * 
     * @param st1 El primer estado a comparar.
     * @param st2 El segundo estado a comparar.
     * @return Un valor negativo si la diferencia de altura de st1 es menor que la
     *         de st2,
     *         0 si son iguales, o un valor positivo si la diferencia de st1 es
     *         mayor que la de st2.
     */
    @Override
    public int compare(State st1, State st2) {
        // Calculamos la diferencia de altura de ambos estados con respecto al objetivo
        double diff1 = Math.abs(goalHeight - st1.getHeight());
        double diff2 = Math.abs(goalHeight - st2.getHeight());

        // Comparación eficiente utilizando los valores absolutos de las diferencias
        return Double.compare(diff1, diff2);
    }

    /**
     * Calcula la diferencia de altura entre el estado objetivo y un estado dado.
     * 
     * @param o1 El primer estado a comprobar.
     * @param o2 El segundo estado a comprobar (no se usa en este caso).
     * @return La diferencia de altura entre el objetivo y el primer estado (o1).
     */
    @Override
    public double checkStates(State o1, State o2) {
        // Calculamos la diferencia de altura entre el estado objetivo y el primer
        // estado
        return Math.abs(goalHeight - o1.getHeight());
    }

    /**
     * Devuelve el tipo de heurística implementada.
     * En este caso, el tipo de heurística es 1 (diferencia de altura).
     * 
     * @return El valor 1, que representa la heurística de diferencia de altura.
     */
    @Override
    public int getType() {
        return 1;
    }
}
