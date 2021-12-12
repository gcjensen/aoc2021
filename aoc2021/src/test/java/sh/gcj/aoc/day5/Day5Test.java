package sh.gcj.aoc.day5;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day5Test {
    Day<Line> day5 = new Day5();

    private final List<String> testInput = List.of(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    );
    private final List<Line> actualInput = day5.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<Line> input = day5.parseInput(testInput.stream());
        assertEquals(5, day5.solvePart1(input));
        assertEquals(6311, day5.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<Line> input = day5.parseInput(testInput.stream());
        assertEquals(12, day5.solvePart2(input));
        assertEquals(19929, day5.solvePart2(actualInput));
    }
}