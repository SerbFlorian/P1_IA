package heuristics;

import models.Estado;
import models.Mapa;

/**
 * Heurística combinada de Manhattan y diferencia de altura.
 * Se ajustan pesos para balancear la distancia y la altura.
 * 
 * h(n) = α * Manhattan(n) + β * Altura(n)
 * 
 * Se pueden ajustar α y β para encontrar un mejor equilibrio.
 * 
 * Es flexible: Puedes ajustar los pesos ALPHA y BETA según las características
 * del mapa.
 * 
 */
public class Combinada implements Heuristica {
    private static final double ALPHA = 1.0; // Peso para la distancia Manhattan
    private static final double BETA = 0.5; // Peso para la diferencia de altura

    @Override
    public double calcular(Estado estado, Mapa mapa) {
        // Obtener valores de Manhattan y Altura
        double manhattan = new Manhattan().calcular(estado, mapa);
        double altura = new Altura().calcular(estado, mapa);

        // Combinar ambas heurísticas con pesos ajustables
        return (ALPHA * manhattan) + (BETA * altura);
    }
}
