package heuristics;

import states.Position;
import states.State;

public class HeuristicManhattanCliffPenalty implements Heuristic {
    private final int goalRow;
    private final int goalColumn;

    public HeuristicManhattanCliffPenalty(State goal) {
        Position goalPosition = goal.getPosition();
        this.goalRow = goalPosition.getRow();
        this.goalColumn = goalPosition.getColumn();
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

        // Heurística: h(n) = |x_actual - x_final| + |y_actual - y_final|
        return manhattanDistance;
    }

    @Override
    public int getType() {
        return 3; // Identificador de la heurística Manhattan
    }
}