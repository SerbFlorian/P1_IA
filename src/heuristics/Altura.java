package heuristics;

import models.Estado;
import models.Mapa;

/**
 * Heurística basada en la diferencia de altura entre el estado actual y el
 * destino.
 * 
 * h(n) = |altura_destino - altura_actual|
 * 
 * Puede no ser admisible, ya que no siempre representa el camino óptimo en
 * términos de tiempo.
 * 
 */
public class Altura implements Heuristica {

    @Override
    public double calcular(Estado estado, Mapa mapa) {
        int alturaActual = estado.getAltura();
        int alturaDestino = mapa.getAltura(mapa.getDestinoX(), mapa.getDestinoY());

        return Math.abs(alturaDestino - alturaActual);
    }
}
