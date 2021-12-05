package sh.gcj.aoc.day4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    Number[][] numbers;
    int totalValue = 0;

    public Board(int width, int height, List<String> numbersList) {
        numbers = new Number[width][height];

        for (int y = 0; y < height; y++) {
            // Split each row and parse the digits (single digit numbers will have extra whitespace as well)
            List<Integer> row = Arrays.stream(numbersList.get(y).split(" "))
                .filter(str -> !str.isEmpty())
                .map(str -> Integer.parseInt(str.trim()))
                .collect(Collectors.toList());

            for (int x = 0; x < width; x++) {
                totalValue += row.get(x);
                numbers[x][y] = new Number(row.get(x));
            }
        }
    }

    public void check(int value) {
        for (int y = 0; y < numbers.length; y++) {
            for (int x = 0; x < numbers[y].length; x++) {
                Number num = numbers[x][y];
                if (num.getValue() == value) {
                    num.mark();
                    totalValue -= num.getValue();
                }
            }
        }
    }

    public boolean hasWon() {
        if (hasLineWin(Type.ROW)) {
            return true;
        }
        return hasLineWin(Type.COLUMN);
    }

    public Integer getRemainingValue() {
        return totalValue;
    }

    private boolean hasLineWin(Type type) {
        for (int y = 0; y < numbers.length; y++) {
            int count = 0;
            for (int x = 0; x < numbers[y].length; x++) {
                Number num = type == Type.ROW ? numbers[x][y] : numbers[y][x];
                if (num.isMarked()) {
                    count++;
                }
            }
            if (count == numbers[y].length) {
                return true;
            }
        }

        return false;
    }

    private enum Type {
        ROW(),
        COLUMN();
    }

    static class Number {
        private final int value;
        private boolean marked = false;

        public Number(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public boolean isMarked() {
            return marked;
        }

        public void mark() {
            marked = true;
        }
    }
}
