package sh.gcj.aoc.day15;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day15 extends Day<int[]> {
    Pattern COST_PATTERN = Pattern.compile("cost=(\\d+)");

    public Day15() {
        super(15);
    }

    @Override
    public List<int[]> parseInput(Stream<String> input) {
        return input.map(s -> Arrays.stream(s.split(""))
            .mapToInt(Integer::parseInt).toArray())
            .collect(Collectors.toList());
    }

    @Override
    public Integer solvePart1(List<int[]> input) throws NoSolutionException {
        var riskLevelMap = input.toArray(new int[0][]);
        return findLowestRiskPath(riskLevelMap);
    }

    @Override
    public Integer solvePart2(List<int[]> input) throws NoSolutionException {
        var riskLevelMap = input.toArray(new int[0][]);
        var expandedRiskLevelMap = expandRiskLevelMap(riskLevelMap, 5);
        return findLowestRiskPath(expandedRiskLevelMap);
    }

    private Integer findLowestRiskPath(int[][] riskLevelMap) throws NoSolutionException {
        // Build the graph of risk levels, connecting each point to all adjacent points
        GraphBuilder<Point, Integer> builder = GraphBuilder.create();
        for (var y = 0; y < riskLevelMap.length; y++) {
            for (var x = 0; x < riskLevelMap[y].length; x++) {
                Point pos = new Point(x, y);
                getAdjacentPositions(riskLevelMap.length, x, y)
                    .forEach(p -> builder.connect(pos).to(p).withEdge(riskLevelMap[p.y][p.x]));
            }
        }

        HipsterDirectedGraph<Point, Integer> cave = builder.createDirectedGraph();
        var searchProblem = GraphSearchProblem
            .startingFrom(new Point(0, 0))
            .in(cave)
            .takeCostsFromEdges()
            .build();

        // Use Dijkstra's to find the shortest path
        var end = new Point(riskLevelMap.length - 1, riskLevelMap.length - 1);
        var result = Hipster.createDijkstra(searchProblem).search(end).getGoalNode().toString();

        // Apparently this library doesn't actually expose the cost of the shortest path, so we have to parse it out of
        // the stringified result... good banter
        var matcher = COST_PATTERN.matcher(result);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new NoSolutionException();
    }

    private List<Point> getAdjacentPositions(int max, int x, int y) {
        var adjacents = List.of(
            new Point(x, y - 1),
            new Point(x + 1, y),
            new Point(x, y + 1),
            new Point(x - 1, y)
        );

        return adjacents.stream()
            .filter(p -> p.x >= 0 && p.x < max && p.y >= 0 && p.y < max)
            .collect(Collectors.toList());
    }

    private int[][] expandRiskLevelMap(int[][] riskLevelMap, int expandBy) {
        var currentSize = riskLevelMap.length;
        var expandedRiskLevelMap = new int[currentSize * expandBy][currentSize * expandBy];
        for (var i = 0; i < expandBy; i++) {
            for (var j = 0; j < expandBy; j++) {
                for (var y = 0; y < riskLevelMap.length; y++) {
                    for (var x = 0; x < riskLevelMap[y].length; x++) {
                        var riskLevel = (riskLevelMap[y][x] + i + j -1) % 9 + 1;
                        expandedRiskLevelMap[i * currentSize + y][j * currentSize + x] = riskLevel;
                    }
                }
            }
        }

        return expandedRiskLevelMap;
    }
}