package loadMap;

import states.State;
import states.Position;

public class MapData {
    public State[][] map; // Matriz que representa el mapa con los diferentes estados de cada celda
    public Position start; // Posición de inicio dentro del mapa
    public Position end; // Posición de destino dentro del mapa

    /**
     * Constructor de la clase MapData.
     * 
     * @param map   Matriz de estados que representa el mapa.
     * @param start Posición inicial en el mapa.
     * @param end   Posición final o destino en el mapa.
     */
    public MapData(State[][] map, Position start, Position end) {
        this.map = map; // Asignación de la matriz del mapa
        this.start = start; // Asignación de la posición de inicio
        this.end = end; // Asignación de la posición de destino
    }
}
