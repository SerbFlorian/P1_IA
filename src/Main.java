import heuristics.*;
import loadMap.MapData;
import loadMap.MapLoader;
import states.*;
import algorithms.*;

public class Main {
    private static final int OBSTACLE_VALUE = -999; // Valor que representa un obstáculo en el mapa

    /* Colores para la impresión del mapa y rutas */
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m"; // Color verde para la posición inicial
    public static final String ANSI_PINK = "\u001B[35m"; // Color rosa para la posición final
    public static final String ANSI_RED = "\u001B[31m"; // Color rojo para los obstáculos

    public static void main(String[] args) throws Exception {

        // Cargar el mapa desde un archivo de texto
        String filePath = "C:\\Users\\Florian\\Desktop\\P1_IA\\src\\data\\mapa3.txt";
        MapData mapData = MapLoader.loadMapState(filePath);

        // Obtener la matriz del mapa y las posiciones inicial y final
        State[][] map = mapData.map;
        Position startPosition = mapData.start;
        Position endPosition = mapData.end;

        // Definir los estados inicial y final a partir de sus posiciones
        State startState = map[startPosition.getRow()][startPosition.getColumn()];
        State endState = map[endPosition.getRow()][endPosition.getColumn()];

        // Mostrar el mapa base con la posición inicial, final y los obstáculos
        displayMap(map, startPosition, endPosition);

        // Instanciar el algoritmo Best-First Search (BFS)
        BestFirst bestFirst = new BestFirst();

        System.out.println("\nEjecutando Best-First con Heurística de Altura");
        bestFirst.bestFirst(map, startState, endState, new HeuristicAltura(endState), endPosition);

        System.out.println("\nEjecutando Best-First con Heurística Manhattan");
        bestFirst.bestFirst(map, startState, endState, new HeuristicManhattan(endPosition), endPosition);

        System.out.println("\nEjecutando Best-First con Heurística Combinada");
        bestFirst.bestFirst(map, startState, endState, new HeuristicCombined(endPosition), endPosition);

        // Instanciar el algoritmo A*
        Astar aStar = new Astar();

        System.out.println("\nEjecutando A* con Heurística de Altura");
        aStar.astar(map, startState, endState, new HeuristicAltura(endState), endPosition);

        System.out.println("\nEjecutando A* con Heurística Manhattan");
        aStar.astar(map, startState, endState, new HeuristicManhattan(endPosition), endPosition);

        System.out.println("\nEjecutando A* con Heurística Combinada");
        aStar.astar(map, startState, endState, new HeuristicCombined(endPosition), endPosition);
    }

    /**
     * Muestra la matriz del mapa con los valores de altura, la posición inicial, la
     * final y los obstáculos.
     * 
     * @param map   Matriz del mapa.
     * @param start Posición inicial.
     * @param end   Posición final.
     */
    public static void displayMap(State[][] map, Position start, Position end) {
        System.out.println("MAPA BASE");
        for (int row = 0; row < map.length; row++) {
            System.out.print("|");

            for (int col = 0; col < map[row].length; col++) {
                int heightValue = map[row][col].getHeight();
                String cellData;

                // Marcar los obstáculos en rojo
                if (heightValue == OBSTACLE_VALUE) {
                    cellData = ANSI_RED + "X" + ANSI_RESET;
                    // Marcar la posición inicial en verde
                } else if (map[row][col].getPosition().equals(start)) {
                    cellData = ANSI_GREEN + "S" + ANSI_RESET;
                    // Marcar la posición final en rosa
                } else if (map[row][col].getPosition().equals(end)) {
                    cellData = ANSI_PINK + "E" + ANSI_RESET;
                } else {
                    cellData = String.valueOf(heightValue); // Imprimir el valor de altura normal
                }

                if (col > 0) {
                    System.out.print(" ");
                }
                System.out.print(cellData);
            }
            System.out.println("|");
        }
    }
}
