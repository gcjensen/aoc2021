package sh.gcj.aoc.day9;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day9Test {
    Day<List<Integer>> day9 = new Day9();

    private final List<String> testInput = List.of(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    );
    private final List<List<Integer>> actualInput = day9.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<List<Integer>> input = day9.parseInput(testInput.stream());
        assertEquals(15, day9.solvePart1(input));
        assertEquals(585, day9.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<List<Integer>> input = day9.parseInput(testInput.stream());
        assertEquals(1134, day9.solvePart2(input));
        assertEquals(827904, day9.solvePart2(actualInput));
    }
}