package sh.gcj.aoc.day20;

import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20 extends Day<String[]> {

    public Day20() {
        super(20);
    }

    @Override
    public List<String[]> parseInput(Stream<String> input) {
        return input.map(s -> s.split("")).collect(Collectors.toList());
    }

    @Override
    public Long solvePart1(List<String[]> input) throws NoSolutionException {
        var algorithm = input.get(0);

        String[][] pixels = input.subList(2, input.size()).toArray(new String[0][]);

        var enhanced = enhance(algorithm, enhance(algorithm, new Image(pixels, ".")));
        return countLit(enhanced.pixels);
    }

    @Override
    public Long solvePart2(List<String[]> input) throws NoSolutionException {
        var algorithm = input.get(0);

        String[][] pixels = input.subList(2, input.size()).toArray(new String[0][]);

        Image image = new Image(pixels, ".");
        for (var i = 0; i < 50; i++) {
            image = enhance(algorithm, image);
        }

        return countLit(image.pixels);
    }

    private record Image (String[][] pixels, String infinitePixel) {}

    private long countLit(String[][] image) {
        return Arrays.stream(image)
            .map(row -> Arrays.stream(row)
            .filter(p -> p.equals("#")).count())
            .reduce(0L, Long::sum);
    }

    private Image enhance(String[] algorithm, Image image) {
        image = addBorder(image);
        var output = new String[image.pixels.length][image.pixels.length];
        for (var y = 0; y < image.pixels.length; y++) {
            for (var x = 0; x < image.pixels[y].length; x++) {
                int index = convertPixel(new Point(x, y), image);
                output[y][x] = algorithm[index];
            }
        }

        var infinitePixel = algorithm[convertPixel(new Point(-1, -1), image)];
        return new Image(output, infinitePixel);
    }

    private int convertPixel(Point pixel, Image image) {
        List<Point> surroundingPixels = List.of(
            new Point(pixel.x - 1, pixel.y - 1),
            new Point(pixel.x, pixel.y - 1),
            new Point(pixel.x + 1, pixel.y - 1),
            new Point(pixel.x - 1, pixel.y),
            new Point(pixel.x, pixel.y),
            new Point(pixel.x + 1, pixel.y),
            new Point(pixel.x - 1, pixel.y + 1),
            new Point(pixel.x, pixel.y + 1),
            new Point(pixel.x + 1, pixel.y + 1)
        );

        var bitString = new StringBuilder();
        for (var p : surroundingPixels) {
            if (p.x >= 0 && p.x < image.pixels.length && p.y >= 0 && p.y < image.pixels.length)  {
                bitString.append(image.pixels[p.y][p.x].equals(".") ? "0" : "1");
                continue;
            }
            bitString.append(image.infinitePixel.equals(".") ? "0" : "1");
        }

        return Integer.parseInt(bitString.toString(), 2);
    }

    // Adds a single pixel border to the image to represent the infinite pixels going in all directions
    private Image addBorder(Image image) {
        var size = image.pixels.length;
        var imageList = Arrays.stream(image.pixels)
            .map(b -> new ArrayList<>(Arrays.asList(b)))
            .collect(Collectors.toList());

        // Add a border at the top and bottom of the image
        imageList.add(0, new ArrayList<>(Collections.nCopies(size, image.infinitePixel)));
        imageList.add(imageList.size(), new ArrayList<>(Collections.nCopies(size, image.infinitePixel)));

        // Add a border on the left and right
        imageList.forEach(row -> {
            row.add(0, image.infinitePixel);
            row.add(row.size(), image.infinitePixel);
        });

        var withBorder = imageList.stream().map(l -> l.toArray(String[]::new)).toArray(String[][]::new);
        return new Image(withBorder, image.infinitePixel);
    }
}