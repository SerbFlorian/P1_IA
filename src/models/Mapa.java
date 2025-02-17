package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapa que representa el terreno y proporciona funcionalidades de búsqueda.
 */
public class Mapa {
    private int filas;
    private int columnas;
    private int inicioX, inicioY;
    private int destinoX, destinoY;
    private int[][] mapa;

    public Mapa(int filas, int columnas, int inicioX, int inicioY, int destinoX, int destinoY, int[][] mapa) {
        this.filas = filas;
        this.columnas = columnas;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.mapa = mapa;
    }

    /**
     * Imprime el mapa en la consola con representación de precipicios y resalta el
     * camino con color.
     * El estado final se resalta en azul.
     */
    public void imprimirMapaConCamino(List<Estado> camino) {
        System.out.println("Mapa con camino resaltado:");

        // Convertir la lista de camino en un set para comprobaciones rápidas
        List<String> caminoStrings = new ArrayList<>();
        for (Estado estado : camino) {
            caminoStrings.add(estado.getX() + "," + estado.getY());
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String key = i + "," + j;

                // Si la casilla está en el camino, usar color verde
                if (caminoStrings.contains(key)) {
                    System.out.print("\033[32m" + mapa[i][j] + " \033[0m"); // Verde para el camino
                }
                // Si estamos en el estado final, usar color azul
                else if (i == destinoX && j == destinoY) {
                    System.out.print("\033[34m" + mapa[i][j] + " \033[0m"); // Azul para el estado final
                }
                // Si es un precipicio, usar color rojo
                else if (mapa[i][j] == -1) {
                    System.out.print("\033[31mX  \033[0m"); // Rojo para el precipicio
                } else {
                    System.out.printf("%-3d", mapa[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Verifica si una posición está dentro de los límites del mapa.
     * 
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return True si la posición es válida, false si está fuera del mapa o es un
     *         precipicio.
     */
    public boolean esValido(int x, int y) {
        return x >= 0 && x < filas && y >= 0 && y < columnas && !esPrecipicio(x, y);
    }

    /**
     * Obtiene la altura de una celda en el mapa.
     * 
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return Altura de la casilla.
     */
    public int getAltura(int x, int y) {
        return mapa[x][y];
    }

    /**
     * Verifica si una casilla es un precipicio.
     * 
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return True si la casilla es un precipicio, false en caso contrario.
     */
    public boolean esPrecipicio(int x, int y) {
        return mapa[x][y] == -1;
    }

    /**
     * Obtiene el estado inicial del problema.
     * 
     * @return Estado inicial.
     */
    public Estado getEstadoInicial() {
        return new Estado(inicioX, inicioY, getAltura(inicioX, inicioY), 0, new ArrayList<>());
    }

    /**
     * Obtiene el estado objetivo del problema.
     * 
     * @return Estado final.
     */
    public Estado getEstadoFinal() {
        return new Estado(destinoX, destinoY, getAltura(destinoX, destinoY), 0, new ArrayList<>());
    }

    /**
     * Genera los estados vecinos a partir de un estado actual.
     * 
     * @param estado Estado actual.
     * @return Lista de estados vecinos válidos.
     */
    public List<Estado> generarVecinos(Estado estado) {
        List<Estado> vecinos = new ArrayList<>();
        int[][] movimientos = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // Arriba, Abajo, Izquierda, Derecha

        for (int[] mov : movimientos) {
            int nuevoX = estado.getX() + mov[0];
            int nuevoY = estado.getY() + mov[1];

            if (esValido(nuevoX, nuevoY)) {
                int nuevaAltura = getAltura(nuevoX, nuevoY);
                double nuevoCosto = calcularCosto(estado.getAltura(), nuevaAltura, estado.getCosto());
                vecinos.add(new Estado(nuevoX, nuevoY, nuevaAltura, nuevoCosto, estado.getCamino()));
            }
        }
        return vecinos;
    }

    /**
     * Calcula el costo del movimiento basado en la diferencia de altura.
     * 
     * @param alturaActual Altura de la casilla de origen.
     * @param alturaNueva  Altura de la casilla de destino.
     * @param costoActual  Costo acumulado hasta ahora.
     * @return Nuevo costo acumulado.
     */
    private double calcularCosto(int alturaActual, int alturaNueva, double costoActual) {
        if (alturaNueva >= alturaActual) {
            return costoActual + (1 + (alturaNueva - alturaActual));
        } else {
            return costoActual + 0.5;
        }
    }

    // Getters básicos
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public int getDestinoX() {
        return destinoX;
    }

    public int getDestinoY() {
        return destinoY;
    }
}
