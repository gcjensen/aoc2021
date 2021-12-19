package sh.gcj.aoc.day18;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day18Test {
    Day<Day18.SnailNumber> day18 = new Day18();

    private final List<String> testInput = List.of(
        "[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
        "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
        "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
        "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
        "[7,[5,[[3,8],[1,4]]]]",
        "[[2,[2,2]],[8,[8,1]]]",
        "[2,9]",
        "[1,[[[9,3],9],[[9,0],[0,7]]]]",
        "[[[5,[7,4]],7],1]",
        "[[[[4,2],2],6],[8,7]]"
    );
    private final List<String> testInput2 = List.of(
        "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
        "[[[5,[2,8]],4],[5,[[9,9],0]]]",
        "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
        "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
        "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
        "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
        "[[[[5,4],[7,7]],8],[[8,3],8]]",
        "[[9,3],[[9,9],[6,[4,9]]]]",
        "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
        "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
    );
    private final List<Day18.SnailNumber> actualInput = day18.parseInput();

    private String testExplode(String number) throws IOException {
        var num = Day18.SnailNumber.parse(new StringReader(number));
        num.explode(0);
        return num.toString();
    }

    @Test
    public void testPart1() throws NoSolutionException, IOException {
        assertEquals("[[[[0,9],2],3],4]", testExplode("[[[[[9,8],1],2],3],4]"));
        assertEquals("[[6,[5,[7,0]]],3]", testExplode("[[6,[5,[4,[3,2]]]],1]"));

        assertEquals(143, Day18.SnailNumber.parse(new StringReader("[[1,2],[[3,4],5]]")).magnitude());
        assertEquals(1384, Day18.SnailNumber.parse(new StringReader("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]")).magnitude());
        assertEquals(3488, Day18.SnailNumber.parse(new StringReader("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]")).magnitude());

        assertEquals(4140, day18.solvePart1(day18.parseInput(testInput2.stream())));
        assertEquals(4289, day18.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
         assertEquals(3993, day18.solvePart2(day18.parseInput(testInput2.stream())));
         assertEquals(4807, day18.solvePart2(actualInput));
    }
}