package sh.gcj.aoc;

import sh.gcj.aoc.day1.Day1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Aoc {
    public static void main(String[] args) {
        List<Day> days = Arrays.asList(new Day1());

        days.forEach(day -> {
            List input = day.parseInput();
            System.out.printf("Day %d\n  Part 1: %s\n  Part 2: %s\n", day.getDay(), day.solvePart1(input), day.solvePart2(input));
        });
    }
}