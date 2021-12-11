package sh.gcj.aoc.day7;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day7Test {
    Day<Integer> day7 = new Day7();

    private final List<String> testInput = Collections.singletonList("16,1,2,0,4,2,7,1,2,14");
    private final List<Integer> actualInput = day7.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<Integer> input = day7.parseInput(testInput.stream());
        assertEquals(37, day7.solvePart1(input));
        assertEquals(355764, day7.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<Integer> input = day7.parseInput(testInput.stream());
        assertEquals(168, day7.solvePart2(input));
        assertEquals(99634572, day7.solvePart2(actualInput));
    }
}