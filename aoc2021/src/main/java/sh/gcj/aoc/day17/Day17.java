package sh.gcj.aoc.day17;

import javafx.util.Pair;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day17 extends Day<String> {
    Pattern TARGET_AREA_PATTERN = Pattern.compile("x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)");

    public Day17() {
        super(17);
    }

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    private Pair<Point, Point> parseTargetArea(String str) {
        var matcher = TARGET_AREA_PATTERN.matcher(str);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }

        var targetMin = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(4)));
        var targetMax = new Point(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));

        return new Pair<>(targetMin, targetMax);
    }

    @Override
    public Integer solvePart1(List<String> input) throws NoSolutionException {
        var target = parseTargetArea(input.get(0));
        var targetMin = target.getKey();
        var targetMax = target.getValue();

        var pos = new Point(0, 0);
        var maxY = 0;

        for (var y = 1; y < Math.abs(targetMax.y); y++) {
            initialX:
            for (var x = 1; x < targetMax.x; x++) {
                var velocity = new Velocity(x, y);
                var localMax = 0;
                pos = new Point(0, 0);
                for (var step = 1; step < Integer.MAX_VALUE; step++) {
                    pos.setLocation(pos.x + velocity.x, pos.y + velocity.y);
                    velocity.x = Math.max(0, velocity.x - 1);
                    velocity.y -= 1;

                    localMax = Math.max(localMax, pos.y);

                    // In target
                    if (pos.x >= targetMin.x && pos.x <= targetMax.x && pos.y <= targetMin.y && pos.y >= targetMax.y) {
                        maxY = Integer.max(maxY, localMax);
                    }

                    if (velocity.x == 0 && pos.y < targetMax.y) {
                        continue initialX;
                    }

                    if (pos.x > targetMax.x || pos.y < targetMax.y) {
                        break;
                    }
                }
            }
        }

        return maxY;
    }


    @Override
    public Integer solvePart2(List<String> input) throws NoSolutionException {
        var target = parseTargetArea(input.get(0));
        var targetMin = target.getKey();
        var targetMax = target.getValue();

        Set<Point> hits = new HashSet<>();
        var pos = new Point(0, 0);

        // Let's just brute force it :)
        for (var y = -1000; y < 1000; y++) {
            initialX:
            for (var x = 1; x < Integer.MAX_VALUE; x++) {
                var velocity = new Velocity(x, y);
                var initialVelocity = new Point(x, y);
                pos = new Point(0, 0);
                for (var step = 1; step < Integer.MAX_VALUE; step++) {
                    pos.setLocation(pos.x + velocity.x, pos.y + velocity.y);
                    velocity.x = Math.max(0, velocity.x - 1);
                    velocity.y -= 1;

                    // We've overshot immediately, so this x value is too high
                    if (step == 1 && pos.x > targetMax.x) {
                        break initialX;
                    }

                    // In target
                    if (pos.x >= targetMin.x && pos.x <= targetMax.x && pos.y <= targetMin.y && pos.y >= targetMax.y) {
                        hits.add(initialVelocity);
                    }

                    if (velocity.x == 0 && pos.y < targetMax.y) {
                        continue initialX;
                    }

                    if (pos.x > targetMax.x || pos.y < targetMax.y) {
                        break;
                    }
                }
            }
        }

        return hits.size();
    }

    private static class Velocity extends Point {
        public Velocity(Integer x, Integer y) {
            super(x, y);
        }
    }
}