package sh.gcj.aoc.day8;

import java.util.List;

public class Entry {
    private List<List<String>> patterns;
    private List<List<String>> output;

    public Entry (List<List<String>> patterns, List<List<String>> output) {
        this.patterns = patterns;
        this.output = output;
    }

    public List<List<String>> getPatterns() {
        return this.patterns;
    }

    public List<List<String>> getOutput() {
        return this.output;
    }
}
