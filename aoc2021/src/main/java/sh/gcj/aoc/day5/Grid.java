package sh.gcj.aoc.day5;

public class Grid {
    int[][] points;

    public Grid(int width, int height) {
        points = new int[width][height];
    }

    public void draw(Line line) {
        if (line.isHorizontal()) {
            plotHorizontal(line);
        } else if (line.isVertical()) {
            plotVertical(line);
        } else if (line.start().y < line.end().y) {
            plotDiagonalDown(line);
        } else {
            plotDiagonalUp(line);
        }
    }

    public Integer getNumberOfOverlaps() {
        int overlaps = 0;
        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points[x].length; y++) {
               if (points[x][y] > 1) {
                   overlaps++;
               }
            }
        }

        return overlaps;
    }

    private void plotHorizontal(Line line) {
        for (int x = line.start().x; x <= line.end().x; x++) {
          points[x][line.start().y]++;
       }
    }

    private void plotVertical(Line line) {
        for (int y = line.start().y; y <= line.end().y; y++) {
            points[line.start().x][y]++;
        }
    }

    private void plotDiagonalUp(Line line) {
        int y = line.start().y;
        for (int x = line.start().x; x <= line.end().x; x++) {
            points[x][y--]++;
        }
    }

    private void plotDiagonalDown(Line line) {
        int y = line.start().y;
        for (int x = line.start().x; x <= line.end().x; x++) {
            points[x][y++]++;
        }
    }
}
