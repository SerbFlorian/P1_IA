package heuristics;

import models.Estado;
import models.Mapa;

//Todas las heurísticas implementarán esta interfaz para garantizar compatibilidad.
/**
 * Interfaz base para definir heurísticas.
 */
public interface Heuristica {
    /**
     * Calcula la heurística de un estado dado.
     * 
     * @param estado Estado actual.
     * @param mapa   Mapa del problema.
     * @return Valor heurístico estimado desde este estado hasta el destino.
     */
    double calcular(Estado estado, Mapa mapa);
}
