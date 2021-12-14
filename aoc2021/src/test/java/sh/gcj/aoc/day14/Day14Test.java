package sh.gcj.aoc.day14;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day14Test {
    Day<String> day14 = new Day14();

    private final List<String> testInput = List.of(
        "NNCB",
        "",
        "CH -> B",
        "HH -> N",
        "CB -> H",
        "NH -> C",
        "HB -> C",
        "HC -> B",
        "HN -> C",
        "NN -> C",
        "BH -> H",
        "NC -> B",
        "NB -> B",
        "BN -> B",
        "BB -> N",
        "BC -> B",
        "CC -> N",
        "CN -> C"
    );

    private final List<String> actualInput = day14.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(1588L, day14.solvePart1(testInput));
        assertEquals(2899L, day14.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        for (var i = 0; i < 100; i ++) {
            assertEquals(2188189693529L, day14.solvePart2(testInput));
            assertEquals(3528317079545L, day14.solvePart2(actualInput));
        }
    }
}