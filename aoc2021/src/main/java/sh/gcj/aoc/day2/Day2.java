package sh.gcj.aoc.day1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.day2.Submarine;

public class Day2 extends Day<String> {

    public Day2() {
        super(2);
    }

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    public Integer solvePart1(List<String> input) {
        Submarine sub = new Submarine(input);
        sub.drive(false);
        return sub.getPosition().x * sub.getPosition().y;
    }

    @Override
    public Integer solvePart2(List<String> input) {
        Submarine sub = new Submarine(input);
        sub.drive(true);
        return sub.getPosition().x * sub.getPosition().y;
    }
}