package sh.gcj.aoc.day3;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day3Test {
    Day<String> day3 = new Day3();

    private final List<String> testInput = List.of(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    );
    private final List<String> actualInput = day3.parseInput();

    @Test public void testPart1() throws NoSolutionException {
        assertEquals(198, day3.solvePart1(testInput));
        assertEquals(1458194, day3.solvePart1(actualInput));
    }

    @Test public void testPart2() throws NoSolutionException {
        assertEquals(230, day3.solvePart2(testInput));
        assertEquals(2829354, day3.solvePart2(actualInput));
    }
}