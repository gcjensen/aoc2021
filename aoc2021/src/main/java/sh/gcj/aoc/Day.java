package sh.gcj.aoc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Stream;

public abstract class Day<T> {
    protected int day;

    public Day(int day) {
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }

    public List<T> parseInput() {
        String filename = String.format("day%d.txt", this.day);
        InputStream stream = Day.class.getResourceAsStream(filename);
        return this.parseInput(new BufferedReader(new InputStreamReader(stream)).lines());
    }

    abstract public List<T> parseInput(Stream<String> input);

    abstract public Object solvePart1(List<T> input);

    abstract public Object solvePart2(List<T> input);
}