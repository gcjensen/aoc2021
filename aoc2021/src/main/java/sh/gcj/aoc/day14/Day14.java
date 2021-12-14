package sh.gcj.aoc.day14;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day14 extends Day<String> {

    public Day14() {
        super(14);
    }

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    @Override
    public Long solvePart1(List<String> input) throws NoSolutionException {
        return polymerize(input, 10);
    }

    @Override
    public Long solvePart2(List<String> input) throws NoSolutionException {
        return polymerize(input, 40);
    }

    public Long polymerize(List<String> input, Integer steps) {
        var template = new ArrayList<>(Arrays.asList(input.get(0).split("")));
        var rules = new HashMap<String, String>();
        input.stream().skip(2).forEach(r -> rules.put(r.split(" -> ")[0], r.split(" -> ")[1]));

        // We'll maintain counts for each individual element and each pair
        BigMultiset<String> elementCounts = BigMultiset.create();
        BigMultiset<String> pairCounts = BigMultiset.create();

        for (var i = 0; i < template.size() - 1; i++) {
            elementCounts.add(template.get(i));
            pairCounts.add(template.get(i) + template.get(i + 1));
        }
        // Add last element as well because we only iterated to size - 1 above (because pairs)
        elementCounts.add(template.get(template.size() - 1));

        for (var s = 0; s < steps; s++) {
            // For each pair, we need to decrement the count (because we'll be splitting the pair to insert a new
            // element), update the count for whatever the new element is based on the pair, and then also update the
            // counts for both of the new pairs we're creating
            for (var pairCount : pairCounts.copy().entrySet()) {
                var pair = pairCount.getKey();
                var count = pairCount.getValue();

                pairCounts.add(pair, -count);

                var newElement = rules.get(pair);
                elementCounts.add(newElement, count);

                pairCounts.add(pair.split("")[0] + newElement, count);
                pairCounts.add(newElement + pair.split("")[1], count);
            }
        }

        return elementCounts.max() - elementCounts.min();
    }
}