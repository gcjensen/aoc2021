package sh.gcj.aoc.day18;

import com.google.common.collect.Sets;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day18 extends Day<Day18.SnailNumber> {
    public Day18() {
        super(18);
    }

    @Override
    public List<SnailNumber> parseInput(Stream<String> input) {
        return input.map(s -> {
            try {
                return SnailNumber.parse(new StringReader(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<SnailNumber> snailNumbers) throws NoSolutionException {
        var runningTotal = snailNumbers.get(0);
        var numbers = snailNumbers.subList(1, snailNumbers.size());

        return numbers.stream().reduce(runningTotal, SnailNumber::add).magnitude();
    }

    @Override
    public Integer solvePart2(List<SnailNumber> snailNumbers) throws NoSolutionException {
        var combinations = Sets.combinations(Sets.newHashSet(snailNumbers), 2);

        var maxMagnitude = 0;

        // Loop over all the combinations of pairs, sum them, and then take the max magnitude
        for (Set<SnailNumber> combination : combinations) {
            var iter = combination.iterator();
            var first = iter.next();
            var second = iter.next();

            maxMagnitude = Math.max(maxMagnitude, SnailNumber.add(first, second).magnitude());
            maxMagnitude = Math.max(maxMagnitude, SnailNumber.add(second, first).magnitude());
        }

        return maxMagnitude;
    }

    public abstract static class SnailNumber {
        protected SnailNumber parent;
        protected SnailNumber left;
        protected SnailNumber right;
        protected int value;

        public static SnailNumber add(SnailNumber a, SnailNumber b) {
            var sum = new Pair(a.copy(), b.copy());
            sum.reduce();
            return sum;
        }

        public static SnailNumber parse(StringReader reader) throws IOException {
            char ch = (char) reader.read();
            if (ch == '[') {
                var left = parse(reader);
                reader.read();
                var right = parse(reader);
                reader.read();

                return new Pair(left, right);
            }

            return new Regular(Character.getNumericValue(ch));
        }

        public abstract List<SnailNumber> flatten();
        public abstract boolean explode(int depth);
        public abstract boolean split();
        public abstract int magnitude();

        public void setParent(SnailNumber parent) {
            this.parent = parent;
        }

        public SnailNumber copy() {
            // If we once successfully parsed it, we'll be able to do it again (so we can ignore the exception)
            try {
                return parse(new StringReader(this.toString()));
            } catch (Exception ignored) {};
            return null;
        }

        public SnailNumber origin() {
            SnailNumber current = this;
            while (current.parent != null) {
                current = current.parent;
            }
            return current;
        }

        public SnailNumber nearestRegular(SnailNumber number, boolean left) {
            List<SnailNumber> nums = origin().flatten();
            if (!left) Collections.reverse(nums);

            SnailNumber nearest = null;
            for (SnailNumber current : nums) {
                if (current.equals(number.left) || current.equals(number.right)) {
                    break;
                }
                nearest = current;
            }
            return nearest;
        }

        void reduce() {
            while (reduceOnce()) {}
        }

        private boolean reduceOnce() {
            var exploded = explode(0);
            if (exploded) {
                return true;
            }

            return split();
        }
    }

    private static class Pair extends SnailNumber {
        public Pair(SnailNumber left, SnailNumber right) {
            this.left = left;
            this.right = right;

            this.left.parent = this;
            this.right.parent = this;
        }

        @Override
        public List<SnailNumber> flatten() {
            List<SnailNumber> all = new ArrayList<>();
            all.addAll(left.flatten());
            all.addAll(right.flatten());
            return all;
        }

        @Override
        public boolean explode(int depth) {
            if (depth >= 4) {
                var nearestLeft = nearestRegular(this, true);
                if (nearestLeft != null) {
                    nearestLeft.value += left.value;
                }

                var nearestRight = nearestRegular(this, false);
                if (nearestRight != null) {
                    nearestRight.value += right.value;
                }

                // Check which side of the parents pair we need to update, and then set it to a regular
                if (parent.left == this) {
                    parent.left = new Regular(0);
                    parent.left.setParent(parent);
                } else if (parent.right == this) {
                    parent.right = new Regular(0);
                    parent.right.setParent(parent);
                }

                return true;
            }

            depth++;
            if (left.explode(depth)) {
                return true;
            }

            return right.explode(depth);
        }

        @Override
        public boolean split() {
            if (left.split()) {
                return true;
            }

            return right.split();
        }

        @Override
        public int magnitude() {
            return 3 * left.magnitude() + 2 * right.magnitude();
        }

        @Override
        public String toString() {
            return "[" + left + "," + right + "]";
        }
    }

    private static class Regular extends SnailNumber {
        public Regular(int value) {
            this.value = value;
        }

        @Override
        public List<SnailNumber> flatten() {
            return List.of(this);
        }

        @Override
        public boolean explode(int depth) {
            return false;
        }

        @Override
        public boolean split() {
            if (value >= 10) {
                var l = value / 2;
                var r = (int) Math.ceil((double) value / 2);

                var newPair = new Pair(new Regular(l), new Regular(r));
                newPair.setParent(parent);

                // Check we're side of the parent pair we need to update
                if (parent.left == this) {
                    parent.left = newPair;
                } else if (parent.right == this) {
                    parent.right = newPair;
                }

                return true;
            }

            return false;
        }

        @Override
        public int magnitude() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}

