package sh.gcj.aoc.day5;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 extends Day<Line> {

    public Day5() {
        super(5);
    }

    @Override
    public List<Line> parseInput(Stream<String> input) {
        return input.map(Line::parse).collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<Line> lines) throws NoSolutionException {
        lines = lines.stream().filter(l -> l.isVertical() || l.isHorizontal()).collect(Collectors.toList());

        int width = Collections.max(lines.stream().map(l -> l.end().x).collect(Collectors.toList())) + 1;
        int height = Collections.max(lines.stream().map(l -> l.end().y).collect(Collectors.toList())) + 1;

        Grid grid = new Grid(width, height);
        lines.forEach(grid::draw);
        return grid.getNumberOfOverlaps();
    }

    @Override
    public Integer solvePart2(List<Line> lines) throws NoSolutionException {
        int width = Collections.max(lines.stream().map(l -> l.end().x).collect(Collectors.toList())) + 1;
        int height = Collections.max(lines.stream().map(l -> l.end().y).collect(Collectors.toList())) + 1;

        Grid grid = new Grid(width, height);
        lines.forEach(grid::draw);
        return grid.getNumberOfOverlaps();
    }
}