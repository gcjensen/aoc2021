package sh.gcj.aoc.day13;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13 extends Day<String> {
    Pattern DOTS_PATTERN = Pattern.compile("(\\d+),(\\d+)");
    Pattern FOLD_PATTERN = Pattern.compile("(\\w)=(\\d+)");

    public Day13() {
        super(13);
    }

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<String> input) throws NoSolutionException {
        var dotsAndFolds = parsePage(input);
        var dots = dotsAndFolds.dots();

        fold(dots, dotsAndFolds.folds().get(0));
        return dots.size();
    }

    @Override
    public String solvePart2(List<String> input) throws NoSolutionException {
        var dotsAndFolds = parsePage(input);
        var dots = dotsAndFolds.dots();

        dotsAndFolds.folds().forEach(fold -> fold(dots, fold));
        return drawPage(dots);
    }

    private DotsAndFolds parsePage(List<String> page) {
        var dots = new HashSet<Point>();
        var folds = new ArrayList<Fold>();

        for (var line : page) {
            var dm = DOTS_PATTERN.matcher(line);
            var fm = FOLD_PATTERN.matcher(line);
            if (dm.find()) {
                dots.add(new Point(Integer.parseInt(dm.group(1)), Integer.parseInt(dm.group(2))));
            } else if (fm.find()) {
                folds.add(new Fold(Axis.valueOf(fm.group(1).toUpperCase()), Integer.parseInt(fm.group(2))));
            }
        }

        return new DotsAndFolds(dots, folds);
    }

    private String drawPage(Set<Point> dots) {
        var maxX = 0;
        var maxY = 0;
        for (var dot : dots) {
            maxX = Math.max(maxX, dot.x);
            maxY = Math.max(maxY, dot.y);
        }

        var page = new StringBuilder();
        for (var y = 0; y <= maxY; y++) {
            for (var x = 0; x <= maxX; x++) {
                page.append(dots.contains(new Point(x, y)) ? "█" : " ");
            }
            page.append("\n");
        }

        return page.toString();
    }

    private void fold(Set<Point> dots, Fold fold) {
        for (Point dot : new HashSet<>(dots)) {
            switch (fold.direction()) {
                case Y:
                    if (dot.y > fold.line()) {
                        dots.add(new Point(dot.x, 2 * fold.line() - dot.y));
                        dots.remove(dot);
                    }
                    break;
                case X:
                    if (dot.x > fold.line()) {
                        dots.add(new Point(2 * fold.line() - dot.x, dot.y));
                        dots.remove(dot);
                    }
                    break;
            }
        }
    }

    private record DotsAndFolds (Set<Point> dots, List<Fold> folds) {}

    private record Fold (Axis direction, Integer line) {}

    private enum Axis {
        X(),
        Y();
    }
}

