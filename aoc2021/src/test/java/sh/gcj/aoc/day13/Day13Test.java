package sh.gcj.aoc.day13;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day13Test {
    Day<String> day13 = new Day13();

    private final List<String> testInput = List.of(
        "6,10",
        "0,14",
        "9,10",
        "0,3",
        "10,4",
        "4,11",
        "6,0",
        "6,12",
        "4,1",
        "0,13",
        "10,12",
        "3,4",
        "3,0",
        "8,4",
        "1,10",
        "2,14",
        "8,10",
        "9,0",
        "",
        "fold along y=7",
        "fold along x=5"
    );
    private final List<String> actualInput = day13.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(17, day13.solvePart1(testInput));
        assertEquals(837, day13.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        var expected = """
            █████
            █   █
            █   █
            █   █
            █████ 
            """;
        assertEquals(expected, day13.solvePart2(testInput));

        expected = """
            ████ ███  ████  ██  █  █  ██  █  █ █  █
            █    █  █    █ █  █ █ █  █  █ █  █ █  █
            ███  █  █   █  █    ██   █    ████ █  █
            █    ███   █   █ ██ █ █  █    █  █ █  █
            █    █    █    █  █ █ █  █  █ █  █ █  █
            ████ █    ████  ███ █  █  ██  █  █  ██\s
            """;
        assertEquals(expected, day13.solvePart2(actualInput));
    }
}