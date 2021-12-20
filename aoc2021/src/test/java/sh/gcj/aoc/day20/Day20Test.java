package sh.gcj.aoc.day20;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day20Test {
    Day<String[]> day20 = new Day20();

    private final List<String> testInput = List.of(
        "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#",
        "",
        "#..#.",
        "#....",
        "##..#",
        "..#..",
        "..###"
    );

    private final List<String[]> actualInput = day20.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(35L, day20.solvePart1(day20.parseInput(testInput.stream())));
        assertEquals(5081L, day20.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        assertEquals(3351L, day20.solvePart2(day20.parseInput(testInput.stream())));
        assertEquals(15088L, day20.solvePart2(actualInput));
    }
}