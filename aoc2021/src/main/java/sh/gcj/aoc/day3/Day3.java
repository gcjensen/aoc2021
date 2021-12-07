package sh.gcj.aoc.day3;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 extends Day<String> {

    public Day3() {
        super(3);
    }

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<String> input) {
        return checkPowerConsumption(input);
    }

    @Override
    public Integer solvePart2(List<String> input) throws NoSolutionException {
        return calculateLifeSupportRating(input);
    }

    public Integer calculateLifeSupportRating(final List<String> input) throws NoSolutionException {
        int bits = input.get(0).length();

        List<String> possibleOxygenRatings = new ArrayList<>(input);
        List<String> possibleCo2ScrubberRatings = new ArrayList<>(input);

        for (int i = 0; i < bits; i++) {
            int pos = i;

            int mostCommon = findMostCommonBitAtPosition(possibleOxygenRatings, i);
            if (possibleOxygenRatings.size() > 1) {
                possibleOxygenRatings = possibleOxygenRatings.stream()
                    .filter(n -> n.split("")[pos].equals(Integer.toString(mostCommon)))
                    .collect(Collectors.toList());
            }

            int leastCommon = flip(findMostCommonBitAtPosition(possibleCo2ScrubberRatings, i));
            if (possibleCo2ScrubberRatings.size() > 1) {
                possibleCo2ScrubberRatings = possibleCo2ScrubberRatings.stream()
                    .filter(n -> n.split("")[pos].equals(Integer.toString(leastCommon)))
                    .collect(Collectors.toList());
            }
        }

        if (possibleOxygenRatings.size() > 1 || possibleCo2ScrubberRatings.size() > 1) {
            throw new NoSolutionException();
        }

        return Integer.parseInt(possibleOxygenRatings.get(0), 2) *
            Integer.parseInt(possibleCo2ScrubberRatings.get(0), 2);
    }

    public Integer checkPowerConsumption(List<String> input) {
        int gammaRate = 0;
        int epsilonRate = 0;
        for (int i = 0; i < input.get(0).length(); i++) {
            int mostCommon = findMostCommonBitAtPosition(input, i);
            int leastCommon = flip(mostCommon);

            gammaRate = gammaRate << 1 | mostCommon;
            epsilonRate = epsilonRate << 1 | leastCommon;
        }

        return gammaRate * epsilonRate;
    }

    private Integer findMostCommonBitAtPosition(List<String> report, int position) {
        long ones = report.stream().filter(n -> n.split("")[position].equals("1")).count();
        long zeros = report.size() - ones;

        return ones >= zeros ? 1 : 0;
    }

    private Integer flip(int bit) {
        return bit == 0 ? 1 : 0;
    }
}