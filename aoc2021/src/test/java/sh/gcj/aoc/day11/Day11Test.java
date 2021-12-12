package sh.gcj.aoc.day11;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day11Test {
    Day<int[]> day11 = new Day11();

    private final List<String> testInput = List.of(
        "5483143223",
        "2745854711",
        "5264556173",
        "6141336146",
        "6357385478",
        "4167524645",
        "2176841721",
        "6882881134",
        "4846848554",
        "5283751526"
    );

    private final List<int[]> actualInput = day11.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<int[]> input = day11.parseInput(testInput.stream());
        assertEquals(1656, day11.solvePart1(input));
        assertEquals(1681, day11.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<int[]> input = day11.parseInput(testInput.stream());
        assertEquals(195, day11.solvePart2(input));
        assertEquals(276, day11.solvePart2(actualInput));
    }
}