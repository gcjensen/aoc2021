package sh.gcj.aoc.day21;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day21Test {
    Day<Integer> day21 = new Day21();

    private final List<String> testInput = List.of(
        "Player 1 starting position: 4",
        "Player 2 starting position: 8"
    );
    private final List<Integer> actualInput = day21.parseInput();

    @Test public void testPart1() throws NoSolutionException {
        assertEquals(739785L, day21.solvePart1(day21.parseInput(testInput.stream())));
        assertEquals(888735L, day21.solvePart1(actualInput));
    }

    @Test public void testPart2() throws NoSolutionException {
        assertEquals(444356092776315L, day21.solvePart2(day21.parseInput(testInput.stream())));
        assertEquals(647608359455719L, day21.solvePart2(actualInput));
    }
}