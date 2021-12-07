package sh.gcj.aoc.day7;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day7 extends Day<Integer> {

    public Day7() {
        super(7);
    }

    @Override
    public List<Integer> parseInput(Stream<String> input) {
        String inputStr = input.findFirst().map(Object::toString).orElse(null);
        Stream<String> stream = Arrays.stream(inputStr.split(","));
        return stream.map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<Integer> input) throws NoSolutionException {
        return findMinimumFuelForAlignment(input, Function.identity());
    }

    @Override
    public Integer solvePart2(List<Integer> input) throws NoSolutionException {
        return findMinimumFuelForAlignment(input, this::nthTriangleNumber);
    }

    private Integer findMinimumFuelForAlignment(List<Integer> input, Function<Integer, Integer> fuelBurnRateFn) throws NoSolutionException {
        int max = input.stream().max(Integer::compare).orElseThrow(NoSolutionException::new);
        List<Integer> candidates = IntStream.rangeClosed(1, max).boxed().collect(Collectors.toList());
        int minFuelUsage = Integer.MAX_VALUE;
        for (int candidate : candidates) {
            int fuelUsage = input.stream().mapToInt(pos -> fuelBurnRateFn.apply(Math.abs(pos - candidate))).sum();
            minFuelUsage = Math.min(minFuelUsage, fuelUsage);
        }

        return minFuelUsage;
    }

    private int nthTriangleNumber(int num) {
        return (num * num + num) / 2;
    }
}