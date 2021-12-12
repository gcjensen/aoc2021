package sh.gcj.aoc.day10;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day10Test {
    Day<String> day10 = new Day10();

    private final List<String> testInput = List.of(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]"
    );
    private final List<String> actualInput = day10.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(26397, day10.solvePart1(testInput));
        assertEquals(315693, day10.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        assertEquals(288957L, day10.solvePart2(testInput));
        assertEquals(1870887234L, day10.solvePart2(actualInput));
    }
}