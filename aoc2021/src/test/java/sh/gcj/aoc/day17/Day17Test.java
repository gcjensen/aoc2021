package sh.gcj.aoc.day17;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day17Test {
    Day<String> day17 = new Day17();

    private final List<String> testInput = List.of("target area: x=20..30, y=-10..-5");
    private final List<String> actualInput = day17.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(45, day17.solvePart1(testInput));
        assertEquals(10878, day17.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
         assertEquals(112, day17.solvePart2(testInput));
         assertEquals(4716, day17.solvePart2(actualInput));
    }
}