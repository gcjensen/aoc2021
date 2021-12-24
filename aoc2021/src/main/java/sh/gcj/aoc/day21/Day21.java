package sh.gcj.aoc.day21;

import sh.gcj.aoc.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day21 extends Day<Integer> {

    public Day21() {
        super(21);
    }

    @Override
    public List<Integer> parseInput(Stream<String> input) {
        return input.map(s -> s.substring(s.length() - 1)).map(Integer::parseInt).collect(Collectors.toList());
    }

    @Override
    public Long solvePart1(List<Integer> input) {
        var player1 = new Player(input.get(0), 0);
        var player2 = new Player(input.get(1), 0);
        var die = new Die();

        Player loser = player1;

        while (!hasWon(player2, 1000)) {
            player1 = playTurn(player1, die);
            if (hasWon(player1, 1000)) {
                loser = player2;
                break;
            }

            player2 = playTurn(player2, die);
        }

        return loser.score * die.getRollCount();
    }

    private boolean hasWon(Player player, int targetScore) {
        return player.score >= targetScore;
    }

    private Player playTurn(Player player, Die die) {
        var roll = die.rollThrice();
        var newPosition = (player.position + roll[0] + roll[1] + roll[2] - 1) % 10 + 1;
        var newScore = player.score + newPosition;

        return new Player(newPosition, newScore);
    }

    private static class Die {
        private int lastRoll = 0;
        private int rollCount = 0;

        public int[] rollThrice() {
            rollCount += 3;
            return new int[]{++lastRoll, ++lastRoll, ++lastRoll};
        }

        public int getRollCount() {
            return rollCount;
        }
    }

    @Override
    public Long solvePart2(List<Integer> input) {
        var player1 = new Player(input.get(0), 0);
        var player2 = new Player(input.get(1), 0);

        var wins = playQuantumGame(player1, player2, new HashMap<>());

        return Math.max(wins.player1, wins.player2);
    }

    private record Player(int position, long score) {}
    private record GameState(Player player1, Player player2) {}
    private record Wins(long player1, long player2) {}

    private Wins playQuantumGame(Player player1, Player player2, Map<GameState, Wins> resultsCache) {
        // We're using a cache of game states to wins to memoize the results
        var key = new GameState(player1, player2);
        if (resultsCache.containsKey(key)) {
            return resultsCache.get(key);
        }

        if (hasWon(player2, 21)) {
            return new Wins(0L, 1L);
        } else {
            // The sums of all the possible results of three rolls
            var rolls = new int[]{3, 4, 5, 4, 5, 6, 5, 6, 7, 4, 5, 6, 5, 6, 7, 6, 7, 8, 5, 6, 7, 6, 7, 8, 7, 8, 9};

            var player1Wins = 0L;
            var player2Wins = 0L;

            // For each possible roll we calculate the position and score for a player, and then play a "new" game from
            // that starting state, summing the number of wins for each player as we go along.
            for (var roll : rolls) {
                var nextPos = (player1.position + roll -1 ) % 10 + 1;
                var nextScore = player1.score + nextPos;

                // Swap the players and play from this point
                var wins = playQuantumGame(player2, new Player(nextPos, nextScore), resultsCache);

                // Note these are intentionally swapped, as we swapped the players above
                player2Wins += wins.player1;
                player1Wins += wins.player2;
            }

            var wins = new Wins(player1Wins, player2Wins);
            resultsCache.put(key, wins);

            return wins;
        }
    }
}