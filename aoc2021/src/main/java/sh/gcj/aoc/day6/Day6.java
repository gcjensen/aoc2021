package sh.gcj.aoc.day6;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;
import sh.gcj.aoc.day5.Grid;
import sh.gcj.aoc.day5.Line;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 extends Day<Integer> {

    public Day6() {
        super(6);
    }

    @Override
    public List<Integer> parseInput(Stream<String> input) {
        String inputStr = input.findFirst().map(Object::toString).orElse(null);
        Stream<String> stream = Arrays.stream(inputStr.split(","));
        return stream.map(Integer::parseInt).collect(Collectors.toList());
    }

    public Long solvePart1(List<Integer> input) throws NoSolutionException {
        int days = 80;
        int spawnInterval = 7;

        return simulateFish(input, days, spawnInterval);
    }

    @Override
    public Long solvePart2(List<Integer> input) throws NoSolutionException {
        int days = 256;
        int spawnInterval = 7;

        return simulateFish(input, days, spawnInterval);
    }

    private Long simulateFish(List<Integer> fishAges, int days, int spawnInterval) throws NoSolutionException {
        // Use age buckets to keep a count of the number of fish at that age
        LinkedList<Long> ageBuckets = new LinkedList<>(Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L));
        fishAges.forEach(age -> ageBuckets.set(age, ageBuckets.get(age) + 1));

        // Each day, get the number of fish that are spawning new fish (the first item in the list), and add that number
        // to both the spawn interval age and the new fish age
        for (int i = 0; i < days; i++) {
            long numNewFish = ageBuckets.pop();
            ageBuckets.addLast(numNewFish);
            ageBuckets.set(spawnInterval - 1, ageBuckets.get(spawnInterval - 1) + numNewFish);
        }

        // The sum of the list items is now the total number of fish
        return ageBuckets.stream().reduce(Long::sum).orElseThrow(NoSolutionException::new);
    }
}