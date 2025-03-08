package heuristics;

import states.Position;
import states.State;

public class HeuristicEuclideanMaxHeight implements Heuristic {
    private final int goalRow;
    private final int goalColumn;
    private final double goalHeight;

    public HeuristicEuclideanMaxHeight(State goal) {
        Position goalPosition = goal.getPosition();
        this.goalRow = goalPosition.getRow();
        this.goalColumn = goalPosition.getColumn();
        this.goalHeight = goal.getHeight();
    }

    @Override
    public int compare(State st1, State st2) {
        double h1 = calculateHeuristic(st1);
        double h2 = calculateHeuristic(st2);
        return Double.compare(h1, h2);
    }

    @Override
    public double checkStates(State o1, State o2) {
        return calculateHeuristic(o1);
    }

    private double calculateHeuristic(State state) {
        Position pos = state.getPosition();
        // Distancia Euclidiana: sqrt((x_actual - x_final)^2 + (y_actual - y_final)^2)
        double euclideanDistance = Math.sqrt(
                Math.pow(goalRow - pos.getRow(), 2) + Math.pow(goalColumn - pos.getColumn(), 2));

        // Diferencia máxima de altura: |altura_actual - altura_final|
        double maxHeightDifference = Math.abs(goalHeight - state.getHeight());

        // Heurística: h(n) = sqrt((x_actual - x_final)^2 + (y_actual - y_final)^2) +
        // max_height_difference
        return euclideanDistance + maxHeightDifference;
    }

    @Override
    public int getType() {
        return 2; // Identificador de la heurística Euclidiana con altura máxima
    }
}