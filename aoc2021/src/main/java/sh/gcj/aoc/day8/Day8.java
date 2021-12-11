package sh.gcj.aoc.day8;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 extends Day<Entry> {

    public Day8() {
        super(8);
    }

    List<String> SEGMENTS = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
    Map<List<String>, Integer> DIGITS = ImmutableMap.of(
        Arrays.asList("a", "b", "c", "e", "f", "g"), 0,
        Arrays.asList("c", "f"), 1,
        Arrays.asList("a", "c", "d", "e", "g"), 2,
        Arrays.asList("a", "c", "d", "f", "g"), 3,
        Arrays.asList("b", "c", "d", "f"), 4,
        Arrays.asList("a", "b", "d", "f", "g"), 5,
        Arrays.asList("a", "b", "d", "e", "f", "g"), 6,
        Arrays.asList("a", "c", "f"), 7,
        Arrays.asList("a", "b", "c", "d", "e", "f", "g"), 8,
        Arrays.asList("a", "b", "c", "d", "f", "g"), 9
    );

    @Override
    public List<Entry> parseInput(Stream<String> input) {
        return input.map(entry -> {
            String[] entryParts =  entry.split(" \\| ");
            List<List<String>> patterns = Arrays.stream(entryParts[0].split(" "))
                .map(seg -> Arrays.asList(seg.split("")))
                .collect(Collectors.toList());

            List<List<String>> output = Arrays.stream(entryParts[1].split(" "))
                .map(seg -> Arrays.asList(seg.split("")))
                .collect(Collectors.toList());

            return new Entry(patterns, output);
        }).collect(Collectors.toList());
    }

    @Override
    public Long solvePart1(List<Entry> input) throws NoSolutionException {
        return input.stream().map(this::countEasyDigits).reduce(0L, Long::sum);
    }

    @Override
    public Integer solvePart2(List<Entry> input) throws NoSolutionException {
        return input.stream().map(this::solveEntry).reduce(0, Integer::sum);
    }

    private Long countEasyDigits(Entry entry) {
        Set<Integer> lengths = Sets.newHashSet(2, 3, 4, 7);
        return entry.getOutput().stream().filter(seq -> lengths.contains(seq.size())).count();
    }

    private Integer solveEntry(Entry entry) {
        // Create a map of each segment to all the segments, which we'll then filter down to find the correct mappings
        Map<String, List<String>> possibilities = new HashMap<>();
        SEGMENTS.forEach(seg -> possibilities.put(seg, SEGMENTS));

        for (List<String> pattern : entry.getPatterns()) {
            List<List<String>> candidateDigits = DIGITS.keySet().stream()
                .filter(segs -> segs.size() == pattern.size())
                .collect(Collectors.toList());

            List<String> intersection = candidateDigits.stream()
                .reduce(SEGMENTS, (a, b) -> a.stream().filter(b::contains).collect(Collectors.toList()));

            // Probably a very stupid way of getting the disjoint union but meh
            Set<String> union = new HashSet<>();
            candidateDigits.forEach(union::addAll);
            List<String> disjoint = SEGMENTS.stream().filter(a -> !union.contains(a)).collect(Collectors.toList());

            for (String segment : intersection) {
                List<String> remainingPossibilities = possibilities.get(segment).stream()
                    .filter(pattern::contains)
                    .collect(Collectors.toList());
                possibilities.put(segment, remainingPossibilities);
            }

            for (String segment : disjoint) {
                List<String> remainingPossibilities = possibilities.get(segment).stream()
                    .filter(a -> !pattern.contains(a))
                    .collect(Collectors.toList());
                possibilities.put(segment, remainingPossibilities);
            }
        }

        List<String> singles = new ArrayList<>();
        while (singles.size() != possibilities.size()) {
            singles = possibilities.values().stream()
                .filter(a -> a.size() == 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());

            List<List<String>> nonSingles = possibilities.values().stream()
                .filter(a -> a.size() != 1)
                .collect(Collectors.toList());

            for (List<String> seg : nonSingles) {
                seg.removeAll(singles);
            }
        }

        Map<String, String> mappings = new HashMap<>();
        possibilities.forEach((segment, wire) -> mappings.put(wire.get(0), segment));

        StringBuilder digitsStr = new StringBuilder();
        for (List<String> wires : entry.getOutput()) {
            List<String> segments = wires.stream().map(mappings::get).collect(Collectors.toList());
            Integer digit = DIGITS.get(segments.stream().sorted().collect(Collectors.toList()));
            digitsStr.append(digit);
        }

        return Integer.parseInt(String.valueOf(digitsStr));
    }
}