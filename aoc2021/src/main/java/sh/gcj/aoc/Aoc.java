package sh.gcj.aoc;

import java.util.Collections;
import java.util.List;

public class Aoc {
    public static void main(String[] args) {
        List<Day> days = Collections.emptyList();

        days.forEach(day -> {
            List input = day.parseInput();
            System.out.printf("Day %d\n  Part 1: %s\n  Part 2: %s\n", day.getDay(), day.solvePart1(input), day.solvePart2(input));
        });
    }
}