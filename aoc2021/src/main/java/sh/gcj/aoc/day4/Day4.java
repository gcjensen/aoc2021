package sh.gcj.aoc.day4;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 extends Day<String> {

    public Day4() {
        super(4);
    }

    @Override
    public List<String> parseInput(Stream<String> input) {
        return input.collect(Collectors.toList());
    }

    public Integer solvePart1(List<String> input) throws NoSolutionException {
        Bingo game = Bingo.parseGame(input);
        return game.play();
    }

    @Override
    public Integer solvePart2(List<String> input) throws NoSolutionException {
        Bingo game = Bingo.parseGame(input);
        return game.findLoser();
    }
}