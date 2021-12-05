package sh.gcj.aoc.day5;

import java.awt.*;
import java.util.Arrays;

public class Line {
    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public static Line parse(String segment) {
        String[] ends = segment.split(" -> ");

        int[] a = Arrays.stream(ends[0].split(",")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(ends[1].split(",")).mapToInt(Integer::parseInt).toArray();

        Point start, end;
        // If it's a vertical line, set the start as the min y point and end as max y point
        if (a[0] == b[0]) {
           start = new Point(a[0], Math.min(a[1], b[1]));
           end = new Point(b[0], Math.max(a[1], b[1]));
        } else {
            // If it's a horizontal or diagonal, set the start as the min x point and end as max x point. This means we
            // can also increment x, even when it's diagonal
            if (a[0] < b[0]) {
                start = new Point(a[0], a[1]);
                end = new Point(b[0], b[1]);
            } else {
                start = new Point(b[0], b[1]);
                end = new Point(a[0], a[1]);
            }
        }

        return new Line(start, end);
    }

    public Point start() {
        return start;
    }

    public Point end() {
        return end;
    }

    public boolean isHorizontal() {
        return start.y == end.y;
    }

    public boolean isVertical() {
        return start.x == end.x;
    }
}
