package states;

/**
 * La clase `Position` representa una posición en una cuadrícula o mapa, usando
 * las coordenadas de fila y columna.
 * Proporciona métodos para acceder y modificar las coordenadas de la posición,
 * así como compararlas con otras posiciones.
 */
public class Position {

    // Coordenadas de la posición: fila y columna
    private int row, column;

    /**
     * Constructor para inicializar una posición con fila y columna.
     * 
     * @param row    La fila de la posición.
     * @param column La columna de la posición.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Representación en cadena de la posición.
     * 
     * Este método devuelve una cadena que representa la posición de manera legible,
     * útil para depuración o impresión de información.
     * 
     * @return Una cadena que representa la posición con su fila y columna.
     */
    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }

    /**
     * Obtiene la fila de la posición.
     * 
     * @return La fila de la posición.
     */
    public int getRow() {
        return row;
    }

    /**
     * Establece una nueva fila para la posición.
     * 
     * @param row La nueva fila para la posición.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Obtiene la columna de la posición.
     * 
     * @return La columna de la posición.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Establece una nueva columna para la posición.
     * 
     * @param column La nueva columna para la posición.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Compara si dos posiciones son iguales.
     * 
     * Este método sobrecarga el método `equals` de `Object` para comparar dos
     * objetos de tipo `Position`.
     * Retorna `true` si las posiciones tienen las mismas coordenadas de fila y
     * columna, `false` en caso contrario.
     * 
     * @param obj El objeto con el que se va a comparar la posición.
     * @return `true` si las posiciones son iguales, `false` en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Si es la misma instancia, retorna verdadero
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Si el objeto es null o de una clase diferente, retorna falso
        }
        Position other = (Position) obj; // Convertir el objeto a una instancia de Position
        return this.row == other.row && this.column == other.column; // Comparar las coordenadas
    }

    /**
     * Método alternativo para comparar si dos posiciones son iguales.
     * 
     * Este método realiza la misma comparación que `equals`, pero de una forma más
     * explícita para que
     * se pueda usar directamente en otros contextos.
     * 
     * @param position La posición con la que se va a comparar la instancia actual.
     * @return `true` si las posiciones son iguales, `false` en caso contrario.
     */
    public boolean cmp(Position position) {
        return (this.row == position.row) && (this.column == position.column);
    }
}
