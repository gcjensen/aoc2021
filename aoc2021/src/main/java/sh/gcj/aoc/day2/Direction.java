package sh.gcj.aoc.day2;

public enum Direction {
    FORWARD("forward"),
    DOWN("down"),
    UP("up");

    private final String stringifed;

    Direction(String stringifed) {
       this.stringifed = stringifed;
    }

    public static Direction from(String direction) {
        for (Direction d : Direction.values()) {
            if (d.stringifed.equals(direction)) {
                return d;
            }
        }

        throw new IllegalArgumentException();
    }
}
