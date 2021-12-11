package sh.gcj.aoc.day10;

import com.google.common.collect.ImmutableMap;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day10 extends Day<String> {

    public Day10() {
        super(10);
    }

    private final Map<Character, Character> PAIRS = ImmutableMap.of(
        '(', ')',
        '[', ']',
        '{', '}',
        '<', '>'
    );

    final Map<Character, Integer> ERROR_SCORES = ImmutableMap.of(
        ')', 3,
        ']', 57,
        '}', 1197,
        '>', 25137
    );

    final Map<Character, Integer> AUTOCOMPLETE_SCORES = ImmutableMap.of(
        ')', 1,
        ']', 2,
        '}', 3,
        '>', 4
    );

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<String> input) throws NoSolutionException {
        return input.stream().map(this::scoreIfCorrupted).reduce(0, Integer::sum);
    }

    @Override
    public Long solvePart2(List<String> input) throws NoSolutionException {
        List<Long> totalScores = input.stream()
            .map(this::scoreToAutocomplete)
            .filter(Objects::nonNull)
            .sorted(Long::compare)
            .collect(Collectors.toList());

        return totalScores.get(totalScores.size() / 2);
    }

    private Integer scoreIfCorrupted(String line) {
        Stack<Character> stack = new Stack<>();
        for (Character symbol : line.toCharArray()) {
            if (PAIRS.containsKey(symbol)) {
                stack.push(symbol);
            } else {
                if (!symbol.equals(PAIRS.get(stack.pop()))) {
                    return ERROR_SCORES.get(symbol);
                }
            }
        }

        return 0;
    }

    private Long scoreToAutocomplete(String line) {
        Stack<Character> stack = new Stack<>();
        for (Character symbol : line.toCharArray()) {
            if (PAIRS.containsKey(symbol)) {
                stack.push(symbol);
            } else {
                // Return null for corrupt lines
                if (!symbol.equals(PAIRS.get(stack.pop()))) {
                    return null;
                }
            }
        }

        // Return null for already-complete lines
        if (stack.empty()) {
            return null;
        }

        long score = 0;
        while (!stack.empty()) {
            score = (score * 5) +  AUTOCOMPLETE_SCORES.get(PAIRS.get(stack.pop()));
        }

        return score;
    }
}