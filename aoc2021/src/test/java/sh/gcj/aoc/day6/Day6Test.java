package sh.gcj.aoc.day6;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;
import sh.gcj.aoc.day1.Day1;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day6Test {
    Day<Integer> day6 = new Day6();

    private final List<String> testInput = Arrays.asList("3,4,3,1,2");
    private final List<Integer> actualInput = day6.parseInput();

    @Test public void testPart1() throws NoSolutionException {
        List<Integer> input = day6.parseInput(testInput.stream());
        assertEquals(5934L, day6.solvePart1(input));
        assertEquals(386640L, day6.solvePart1(actualInput));
    }

    @Test public void testPart2() throws NoSolutionException {
        List<Integer> input = day6.parseInput(testInput.stream());
        assertEquals(26984457539L, day6.solvePart2(input));
        assertEquals(1733403626279L, day6.solvePart2(actualInput));
    }
}