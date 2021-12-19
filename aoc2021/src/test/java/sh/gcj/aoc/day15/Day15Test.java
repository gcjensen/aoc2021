package sh.gcj.aoc.day15;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;
import sh.gcj.aoc.day15.Day15;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day15Test {
    Day<int[]> day15 = new Day15();

    private final List<String> testInput = List.of(
        "1163751742",
        "1381373672",
        "2136511328",
        "3694931569",
        "7463417111",
        "1319128137",
        "1359912421",
        "3125421639",
        "1293138521",
        "2311944581"
    );
    private final List<int[]> actualInput = day15.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<int[]> input = day15.parseInput(testInput.stream());
        assertEquals(40, day15.solvePart1(input));
        // assertEquals(398, day15.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<int[]> input = day15.parseInput(testInput.stream());
        assertEquals(315, day15.solvePart2(input));

        // Seems the "hipster" library isn't too efficient, so this takes like 2 hours :)
        // assertEquals(-1, day15.solvePart2(actualInput));
    }
}