package sh.gcj.aoc.day2;

public class Move {

    private final Direction direction;
    private final int steps;

    public Move(String direction, int steps) {
        this.direction = Direction.from(direction);
        this.steps = steps;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getSteps() {
        return this.steps;
    }
}
