package sh.gcj.aoc.day12;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 extends Day<Pair<String, String>> {

    public Day12() {
        super(12);
    }

    @Override
    public List<Pair<String, String>> parseInput(Stream<String> input) {
        return input.map(l -> new Pair<>(l.split("-")[0], l.split("-")[1])).collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<Pair<String, String>> input) throws NoSolutionException {
        var start = buildCaveSystem(input);
        // In part 1 there is no extra visit to a small cave allowed, so disable it by setting used to true immediately
        return dfs(start, new ArrayList<>(), true, new HashMap<>());
    }

    @Override
    public Integer solvePart2(List<Pair<String, String>> input) throws NoSolutionException {
        var start = buildCaveSystem(input);
        return dfs(start, new ArrayList<>(), false, new HashMap<>());
    }

    private Integer dfs(Cave cave, List<Cave> visited, Boolean extraSmallCaveVisited, Map<CacheKey, Integer> cache) {
        // Create a key to memoise the path count for this combination of cave id, connections and extraSmallCaveVisited
        var cacheKey = new CacheKey(cave.id, visited, extraSmallCaveVisited);

        var cachedPathCount = cache.get(cacheKey);
        if (cachedPathCount != null) {
            return cachedPathCount;
        }

        if (cave.isEnd()) {
            return 1;
        }

        var paths = 0;

        visited.add(cave);
        for (var c : cave.connectedCaves) {
            // Big caves can be visited any number of times
            if (c.isBig() || !visited.contains(c)) {
                paths += dfs(c, visited, extraSmallCaveVisited, cache);
            } else if (!c.isStart() && !c.isEnd() && !extraSmallCaveVisited) {
               // Use our extra small cave allowance
               paths += dfs(c, visited, true, cache);
            }
        }

        // We've done our dfs from this cave, so now remove so it can be used in another route
        visited.remove(cave);

        cache.put(cacheKey, paths);

        return paths;
    }

    /*
     * Iterate over cave links, building a map of cave id to Cave object. We can then return the start cave and go
     * from there
     */
    private Cave buildCaveSystem(List<Pair<String, String>> input) {
        var caves = new HashMap<String, Cave>();
        for (Pair<String, String> pair : input) {
            var fstCave = caves.getOrDefault(pair.getKey(), new Cave(pair.getKey()));
            var sndCave = caves.getOrDefault(pair.getValue(), new Cave(pair.getValue()));
            fstCave.connectTo(sndCave);
            sndCave.connectTo(fstCave);

            caves.put(fstCave.id, fstCave);
            caves.put(sndCave.id, sndCave);
        }

        return caves.get(Cave.START);
    }

    private record CacheKey (String cave, List<Cave> visited, Boolean extraSmallCaveVisited) {}

    private static class Cave {
        final static String START = "start";
        final static String END = "end";

        final String id;
        final Set<Cave> connectedCaves;

        public Cave(String id) {
            this.id = id;
            this.connectedCaves = new HashSet<>();
        }

        public void connectTo(Cave c) {
            connectedCaves.add(c);
        }

        public boolean isStart() {
            return id.equals(START);
        }

        public boolean isEnd() {
            return id.equals(END);
        }

        public boolean isBig() {
            return id.toUpperCase().equals(id);
        }
    }
}