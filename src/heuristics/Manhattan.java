package heuristics;

import models.Estado;
import models.Mapa;

/**
 * Heurística basada en la distancia Manhattan entre el estado actual y el
 * destino.
 * Se calcula como la suma de las diferencias absolutas de coordenadas X e Y.
 * 
 * h(n) = |x_destino - x_actual| + |y_destino - y_actual|
 * 
 * Admisible y consistente, ya que nunca sobreestima el costo real del camino.
 * 
 */
public class Manhattan implements Heuristica {

    @Override
    public double calcular(Estado estado, Mapa mapa) {
        int xActual = estado.getX();
        int yActual = estado.getY();
        int xDestino = mapa.getDestinoX();
        int yDestino = mapa.getDestinoY();

        return Math.abs(xDestino - xActual) + Math.abs(yDestino - yActual);
    }
}
