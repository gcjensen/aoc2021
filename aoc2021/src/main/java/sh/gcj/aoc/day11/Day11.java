package sh.gcj.aoc.day11;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11 extends Day<int[]> {

    private final static int FLASH_POINT = 9;
    private final static int STEPS = 100;

    public Day11() {
        super(11);
    }

    @Override
    public List<int[]> parseInput(Stream<String> input) {
        return input.map(s -> Arrays.stream(s.split(""))
            .mapToInt(Integer::parseInt).toArray())
            .collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<int[]> input) throws NoSolutionException {
        int[][] octopuses = input.toArray(new int[0][]);
        return IntStream.range(0, STEPS).map(i -> step(octopuses)).reduce(0, Integer::sum);
    }

    @Override
    public Integer solvePart2(List<int[]> input) throws NoSolutionException {
        int[][] octopuses = input.toArray(new int[0][]);
        int totalOctoCount = octopuses.length * octopuses[0].length;

        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (step(octopuses) == totalOctoCount) return i;
        }

        throw new NoSolutionException();
    }

    private Integer step(int[][] octopuses) {
        for (int y = 0; y < octopuses.length; y++) {
            for (int x = 0; x < octopuses[y].length; x++) {
                octopuses[y][x]++;
            }
        }

        Set<Point> flashes = new HashSet<>();

        for (int y = 0; y < octopuses.length; y++) {
            for (int x = 0; x < octopuses[y].length; x++) {
                if (octopuses[y][x] > FLASH_POINT && !flashes.contains(new Point(x, y))) {
                   flash(octopuses, flashes, new Point(x, y));
                }
            }
        }

        // Reset all flashed octos back to 0
        flashes.forEach(o -> octopuses[o.y][o.x] = 0);

        return flashes.size();
    }

    private void flash(int[][] octopuses, Set<Point> flashes, Point octo) {
        flashes.add(octo);

        List<Point> adjacentOctos = Arrays.asList(
            new Point(octo.x, octo.y - 1),
            new Point(octo.x + 1, octo.y - 1),
            new Point(octo.x + 1, octo.y),
            new Point(octo.x + 1, octo.y + 1),
            new Point(octo.x, octo.y + 1),
            new Point(octo.x - 1, octo.y + 1),
            new Point(octo.x - 1, octo.y),
            new Point(octo.x - 1, octo.y - 1)
        );

        adjacentOctos.stream()
            .filter(o -> o.x >= 0 && o.x < octopuses[0].length && o.y >= 0 && o.y < octopuses.length)
            .forEach(o -> {
                // Bump the energy level of each adjacent octo, and flash any that have passed the threshold (and that
                // haven't already flashed in this step)
                octopuses[o.y][o.x]++;
                if (octopuses[o.y][o.x] > FLASH_POINT && !flashes.contains(o)) {
                    flash(octopuses, flashes, o);
                }
            });
    }
}