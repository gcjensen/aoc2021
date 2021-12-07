package sh.gcj.aoc.day1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sh.gcj.aoc.Day;

public class Day1 extends Day<Integer> {

    public Day1() {
        super(1);
    }

    @Override
    public List<Integer> parseInput(Stream<String> input) {
        return input.map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<Integer> input) {
        return countIncreases(input, 1);
    }

    @Override
    public Integer solvePart2(List<Integer> input) {
        return countIncreases(input, 3);
    }

    private Integer countIncreases(List<Integer> input, Integer windowSize) {
        int increases = 0;
        int previous = Integer.MAX_VALUE;
        for (int i = 0; i <= input.size() - windowSize; i++) {
            int window = input.subList(i, i + windowSize).stream().reduce(0, Integer::sum);
            if (window > previous) {
                increases++;
            }
            previous = window;
        }

        return increases;
    }
}