package heuristics;

import states.Position;
import states.State;

public class HeuristicManhattanMinHeight implements Heuristic {
    private final int goalRow;
    private final int goalColumn;
    private final double goalHeight;

    public HeuristicManhattanMinHeight(State goal) {
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
        // Distancia de Manhattan: |x_actual - x_final| + |y_actual - y_final|
        double manhattanDistance = Math.abs(goalRow - pos.getRow()) + Math.abs(goalColumn - pos.getColumn());

        // Diferencia mínima de altura: |altura_actual - altura_final|
        double minHeightDifference = Math.abs(goalHeight - state.getHeight());

        // Heurística: h(n) = |x_actual - x_final| + |y_actual - y_final| +
        // min_height_difference
        return manhattanDistance + minHeightDifference;
    }

    @Override
    public int getType() {
        return 4; // Identificador de la heurística Manhattan con diferencia de altura mínima
    }
}