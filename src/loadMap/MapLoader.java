package loadMap;

import states.State;
import states.Position;
import java.io.*;

public class MapLoader {
    private static final int INVALID_VALUE = -999; // Valor que indica una casilla no válida (representada como 'X' en
                                                   // el archivo)

    /**
     * Carga el estado del mapa desde un archivo de texto.
     *
     * @param filePath Ruta del archivo que contiene la información del mapa.
     * @return Un objeto MapData con el mapa cargado, la posición de inicio y la
     *         posición de fin.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static MapData loadMapState(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath)); // Abrir el archivo para lectura
        String lineContent; // Variable para almacenar cada línea leída
        String[] lineArray; // Arreglo para dividir el contenido de las líneas

        // Leer las dimensiones del mapa (número de filas y columnas)
        lineArray = reader.readLine().split(" ");
        int numRows = Integer.parseInt(lineArray[0]); // Número de filas del mapa
        int numCols = Integer.parseInt(lineArray[1]); // Número de columnas del mapa

        // Inicializar la matriz del mapa con las dimensiones especificadas
        State[][] map = new State[numRows][numCols];

        // Leer la posición de inicio (coordenadas de la celda inicial)
        lineArray = reader.readLine().split(" ");
        Position startPosition = new Position(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]));

        // Leer la posición de destino (coordenadas de la celda final)
        lineArray = reader.readLine().split(" ");
        Position endPosition = new Position(Integer.parseInt(lineArray[0]), Integer.parseInt(lineArray[1]));

        // Leer el contenido del mapa y llenar la matriz de estados
        int rowIndex = 0; // Índice de fila en la matriz
        while ((lineContent = reader.readLine()) != null) { // Leer línea a línea hasta el final del archivo
            lineArray = lineContent.split(" "); // Separar los valores de la línea por espacios
            for (int colIndex = 0; colIndex < lineArray.length; colIndex++) {
                // Si la casilla contiene 'X', se asigna el valor de casilla no válida
                int value = lineArray[colIndex].equals("X") ? INVALID_VALUE : Integer.parseInt(lineArray[colIndex]);
                // Se crea un estado en la posición correspondiente con el valor obtenido
                map[rowIndex][colIndex] = new State(value, new Position(rowIndex, colIndex));
            }
            rowIndex++; // Pasar a la siguiente fila
        }
        reader.close(); // Cerrar el archivo después de la lectura

        // Retornar el mapa cargado junto con las posiciones de inicio y fin
        return new MapData(map, startPosition, endPosition);
    }
}
