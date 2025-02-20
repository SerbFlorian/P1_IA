package heuristics;

import java.util.Comparator;
import states.*;

/**
 * Interfaz que define los métodos que deben implementar las heurísticas para
 * comparar estados.
 * Esta interfaz extiende Comparator<State> y permite realizar comparaciones
 * entre instancias de la clase State.
 */
public interface Heuristic extends Comparator<State> {

    /**
     * Compara dos estados dados. Este método es implementado a través de la
     * interfaz Comparator.
     * 
     * @param o1 El primer estado a comparar.
     * @param o2 El segundo estado a comparar.
     * @return Un valor negativo si o1 es menor que o2, 0 si son iguales, o un valor
     *         positivo si o1 es mayor que o2.
     */
    @Override
    int compare(State o1, State o2);

    /**
     * Compara dos objetos para determinar si son iguales.
     * 
     * @param obj El objeto a comparar con la instancia actual.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    boolean equals(Object obj);

    /**
     * Realiza una comprobación entre dos estados y devuelve un valor basado en el
     * criterio de la heurística.
     * Este valor se puede usar para medir la "calidad" o "distancia" entre los
     * estados.
     * 
     * @param o1 El primer estado a comprobar.
     * @param o2 El segundo estado a comprobar.
     * @return Un valor numérico que representa el resultado de la comprobación
     *         entre los dos estados.
     */
    double checkStates(State o1, State o2);

    /**
     * Obtiene el tipo de heurística que se está utilizando.
     * Este método proporciona una identificación del tipo de heurística,
     * permitiendo diferenciar entre las diferentes implementaciones.
     * 
     * @return Un entero que representa el tipo de heurística.
     */
    public int getType();
}
