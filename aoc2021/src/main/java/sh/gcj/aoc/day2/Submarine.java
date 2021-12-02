package sh.gcj.aoc.day2;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

public class Submarine {

    private final Point position = new Point(0, 0);
    private int aim = 0;
    private final List<Move> route;

    public Submarine(List<String> route) {
        this.route = route.stream()
            .map(s -> new Move(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])))
            .collect(Collectors.toList());
    }

    public void drive(boolean withAim) {
        if (withAim) {
            this.route.forEach(m -> move(m.getDirection(), m.getSteps()));
        } else {
            this.route.forEach(m -> moveWithoutAim(m.getDirection(), m.getSteps()));
        }
    }

    private void move(Direction direction, int numSteps) {
        switch (direction) {
            case FORWARD:
                position.move(position.x + numSteps, position.y + aim * numSteps);
                break;
            case DOWN:
                aim += numSteps;
                break;
            case UP:
                aim -= numSteps;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void moveWithoutAim(Direction direction, int numSteps) {
        switch (direction) {
            case FORWARD:
                position.move(position.x + numSteps, position.y);
                break;
            case DOWN:
                position.move(position.x, position.y + numSteps);
                break;
            case UP:
                position.move(position.x, position.y - numSteps);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public Point getPosition() {
        return this.position;
    }
}
