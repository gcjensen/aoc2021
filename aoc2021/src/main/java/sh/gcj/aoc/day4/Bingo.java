package sh.gcj.aoc.day4;

import sh.gcj.aoc.NoSolutionException;

import java.util.*;
import java.util.stream.Collectors;

public class Bingo {
    private final List<Integer> numberDraw;
    private final List<Board> boards;

    public Bingo(List<Integer> numberDraw, List<Board> boards) {
        this.numberDraw = numberDraw;
        this.boards = boards;
    }

    public static Bingo parseGame(List<String> input) {
        List<Integer> numberDraw = Arrays.stream(input.get(0).split(","))
           .map(Integer::parseInt)
           .collect(Collectors.toList());

        List<Board> boards = new ArrayList<>();
        List<String> boardNumbers = new ArrayList<>(input.subList(2, input.size()));

        // Add blank line at the end so we can subList the full List below, without
        // getting an index out of bounds
        boardNumbers.add("");

        for (int i = 0; i < boardNumbers.size(); i += 5) {
            boards.add(new Board(5, 5, boardNumbers.subList(i, i + 5)));
            i++;
        }

        return new Bingo(numberDraw, boards);
    }

    public Integer play() throws NoSolutionException {
        for (int num : numberDraw) {
            for (Board board : boards) {
                board.check(num);
                if (board.hasWon()) {
                    return num * board.getRemainingValue();
                }
            }
        }

        throw new NoSolutionException();
    }

    public Integer findLoser() throws NoSolutionException {
        for (int num : numberDraw) {
            Iterator<Board> boardIter = boards.iterator();
            while (boardIter.hasNext()) {
                Board board = boardIter.next();
                board.check(num);
                if (board.hasWon()) {
                    if (boards.size() > 1) {
                        boardIter.remove();
                    } else {
                        return num * board.getRemainingValue();
                    }
                }
            }
        }

        throw new NoSolutionException();
    }
}
