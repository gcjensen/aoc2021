package sh.gcj.aoc.day16;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day16Test {
    Day<Character> day16 = new Day16();

    private final List<Character> actualInput = day16.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        assertEquals(16, day16.solvePart1(day16.parseInput(List.of("8A004A801A8002F478").stream())));
        assertEquals(12, day16.solvePart1(day16.parseInput(List.of("620080001611562C8802118E34").stream())));
        assertEquals(23, day16.solvePart1(day16.parseInput(List.of("C0015000016115A2E0802F182340").stream())));
        assertEquals(31, day16.solvePart1(day16.parseInput(List.of("A0016C880162017C3686B18A3D4780").stream())));

        assertEquals(879, day16.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        assertEquals(3L, day16.solvePart2(day16.parseInput(List.of("C200B40A82").stream())));
        assertEquals(7L, day16.solvePart2(day16.parseInput(List.of("880086C3E88112").stream())));
        assertEquals(1L, day16.solvePart2(day16.parseInput(List.of("9C0141080250320F1802104A08").stream())));

        assertEquals(539051801941L, day16.solvePart2(actualInput));
    }
}