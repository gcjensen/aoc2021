package sh.gcj.aoc.day4;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day4Test {
    Day<String> day4 = new Day4();

    private final List<String> testInput = List.of(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        "8  2 23  4 24",
        "21  9 14 16  7",
        "6 10  3 18  5",
        "1 12 20 15 19",
        "",
        "3 15  0  2 22",
        "9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        "2  0 12  3  7"
    );
    private final List<String> actualInput = day4.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(4512, day4.solvePart1(testInput));
        assertEquals(16674, day4.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        assertEquals(1924, day4.solvePart2(testInput));
        assertEquals(7075, day4.solvePart2(actualInput));
    }
}