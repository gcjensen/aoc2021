package sh.gcj.aoc.day1;

import sh.gcj.aoc.Day;
import org.junit.Test;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.*;

public class Day1Test {
    Day<Integer> day1 = new Day1();

    private final List<Integer> testInput = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);
    private final List<Integer> actualInput = day1.parseInput();

    @Test public void testPart1() throws NoSolutionException {
        assertEquals(7, day1.solvePart1(testInput));
        assertEquals(1393, day1.solvePart1(actualInput));
    }

    @Test public void testPart2() throws NoSolutionException {
        assertEquals(5, day1.solvePart2(testInput));
        assertEquals(1359, day1.solvePart2(actualInput));
    }
}