package sh.gcj.aoc.day9;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day9 extends Day<List<Integer>> {

    public Day9() {
        super(9);
    }

    @Override
    public List<List<Integer>> parseInput(Stream<String> input) {
        return input.map(s -> Arrays.stream(s.split(""))
            .map(Integer::parseInt).collect(Collectors.toList()))
            .collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<List<Integer>> input) throws NoSolutionException {
        return findLowPoints(input).stream().map(p -> input.get(p.y).get(p.x) + 1).reduce(0, Integer::sum);
    }

    @Override
    public Integer solvePart2(List<List<Integer>> input) throws NoSolutionException {
        List<Point> lowPoints = findLowPoints(input);
        List<Integer> basinSizes = new ArrayList<>();
        for (Point point : lowPoints) {
            basinSizes.add(findBasins(input, point, new HashSet<>()).size());
        }

        basinSizes.sort(Collections.reverseOrder());
        return basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
    }

    private Set<Point> findBasins(List<List<Integer>> heightmap, Point point, Set<Point> basins) {
        int height = heightmap.get(point.y).get(point.x);
        // Heights of 9 don't count as being in a basin
        if (height == 9) {
            return basins;
        }

        basins.add(point);

        // Find the heights of all the adjacent points (using 0 to "exclude" points that are out of bounds (i.e. the
        // current point is on the edge)
        int top = (point.y - 1) >= 0 ? heightmap.get(point.y - 1).get(point.x) : 0;
        int right = (point.x + 1) < heightmap.get(point.y).size() ? heightmap.get(point.y).get(point.x + 1) : 0;
        int bottom = (point.y + 1) < heightmap.size() ? heightmap.get(point.y + 1).get(point.x) : 0;
        int left = (point.x - 1) >= 0 ? heightmap.get(point.y).get(point.x - 1) : 0;

        // For each adjecent point that's higher, continue to find the basin recursively
        if (top > height) basins = findBasins(heightmap, new Point(point.x, point.y - 1), basins);
        if (right > height) basins = findBasins(heightmap, new Point(point.x + 1, point.y), basins);
        if (bottom > height) basins = findBasins(heightmap, new Point(point.x, point.y + 1), basins);
        if (left > height) basins = findBasins(heightmap, new Point(point.x - 1, point.y), basins);

        return basins;
    }

    private List<Point> findLowPoints(List<List<Integer>> heightmap) {
        List<Point> lowPoints = new ArrayList<>();
        for (int y = 0; y < heightmap.size(); y++)  {
            List<Integer> row = heightmap.get(y);

            for (int x = 0; x < row.size(); x++)  {
                // Find the heights of all the adjacent points (using MAX_VALUE to "exclude" points that are out of
                // bounds (i.e. the current point is on the edge)
                int top = (y - 1) >= 0 ? heightmap.get(y - 1).get(x) : Integer.MAX_VALUE;
                int right = (x + 1) < row.size() ? heightmap.get(y).get(x + 1) : Integer.MAX_VALUE;
                int bottom = (y + 1) < heightmap.size() ? heightmap.get(y + 1).get(x) : Integer.MAX_VALUE;
                int left = (x - 1) >= 0 ? heightmap.get(y).get(x - 1) : Integer.MAX_VALUE;

                // Add to our list of low points if all adjacent points are higher
                int height = row.get(x);
                if (height < top && height < right && height < bottom && height < left) {
                    lowPoints.add(new Point(x, y));
                }
            }
        }

        return lowPoints;
    }
}