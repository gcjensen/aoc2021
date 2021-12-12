package sh.gcj.aoc.day12;

import javafx.util.Pair;
import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day12Test {
    Day<Pair<String, String>> day12 = new Day12();

    private final List<String> testInput = Arrays.asList(
        "start-A",
        "start-b",
        "A-c",
        "A-b",
        "b-d",
        "A-end",
        "b-end"
    );

    private final List<String> testInput2 = Arrays.asList(
        "dc-end",
        "HN-start",
        "start-kj",
        "dc-start",
        "dc-HN",
        "LN-dc",
        "HN-end",
        "kj-sa",
        "kj-HN",
        "kj-dc"
    );

    private final List<Pair<String, String>> actualInput = day12.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<Pair<String, String>> input = day12.parseInput(testInput.stream());
        assertEquals(10, day12.solvePart1(input));

        input = day12.parseInput(testInput2.stream());
        assertEquals(19, day12.solvePart1(input));

        assertEquals(3887, day12.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<Pair<String, String>> input = day12.parseInput(testInput.stream());
        assertEquals(36, day12.solvePart2(input));

        input = day12.parseInput(testInput2.stream());
        assertEquals(103, day12.solvePart2(input));

        assertEquals(104834, day12.solvePart2(actualInput));
    }
}