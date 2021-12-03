package sh.gcj.aoc.day2;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day2Test {
    Day<String> day2 = new Day2();

    private final List<String> testInput = Arrays.asList(
       "forward 5",
       "down 5",
       "forward 8",
       "up 3",
       "down 8",
       "forward 2"
    );
    private final List<String> actualInput = day2.parseInput();

    @Test public void testPart1() throws NoSolutionException {
        assertEquals(150, day2.solvePart1(testInput));
        assertEquals(2272262, day2.solvePart1(actualInput));
    }

    @Test public void testPart2() throws NoSolutionException {
        assertEquals(900, day2.solvePart2(testInput));
        assertEquals(2134882034, day2.solvePart2(actualInput));
    }
}