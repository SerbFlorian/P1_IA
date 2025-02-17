package models;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    private int x, y; // Posición en el mapa
    private int altura; // Altura de la casilla
    private double costo; // Costo acumulado (tiempo)
    private List<Estado> camino; // Camino recorrido hasta este estado

    public Estado(int x, int y, int altura, double costo, List<Estado> camino) {
        this.x = x;
        this.y = y;
        this.altura = altura;
        this.costo = costo;
        this.camino = new ArrayList<>(camino);
        this.camino.add(this); // Agregamos el estado actual al camino
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAltura() {
        return altura;
    }

    public double getCosto() {
        return costo;
    }

    public List<Estado> getCamino() {
        return camino;
    }

    /**
     * Genera los estados vecinos aplicando los operadores de movimiento.
     * 
     * @param mapa Objeto que representa el mapa del problema.
     * @return Lista de estados vecinos válidos.
     */
    public List<Estado> generarVecinos(Mapa mapa) {
        List<Estado> vecinos = new ArrayList<>();
        int[][] movimientos = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Arriba, Abajo, Izquierda, Derecha

        for (int[] mov : movimientos) {
            int nuevoX = x + mov[0];
            int nuevoY = y + mov[1];

            if (mapa.esValido(nuevoX, nuevoY)) {
                int nuevaAltura = mapa.getAltura(nuevoX, nuevoY);
                double nuevoCosto = calcularCosto(nuevaAltura);
                vecinos.add(new Estado(nuevoX, nuevoY, nuevaAltura, nuevoCosto, this.camino));
            }
        }
        return vecinos;
    }

    /**
     * Calcula el costo del movimiento según la diferencia de altura.
     * 
     * @param nuevaAltura Altura de la casilla destino.
     * @return Costo del movimiento.
     */
    private double calcularCosto(int nuevaAltura) {
        if (nuevaAltura >= this.altura) {
            return this.costo + (1 + (nuevaAltura - this.altura));
        } else {
            return this.costo + 0.5;
        }
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ") -> Altura: " + altura + ", Costo: " + costo;
    }
}
