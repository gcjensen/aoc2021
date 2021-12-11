package sh.gcj.aoc.day8;

import org.junit.Test;
import sh.gcj.aoc.Day;
import sh.gcj.aoc.NoSolutionException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day8Test {
    Day<Entry> day8 = new Day8();

    private final List<String> testInput = Arrays.asList(
        "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
        "edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
        "fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
        "fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
        "aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
        "fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
        "dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
        "bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
        "egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
        "gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"
    );
    private final List<Entry> actualInput = day8.parseInput();

    @Test
    public void testPart1() throws NoSolutionException {
        List<Entry> input = day8.parseInput(testInput.stream());
        assertEquals(26L, day8.solvePart1(input));
        assertEquals(272L, day8.solvePart1(actualInput));
    }

    @Test
    public void testPart2() throws NoSolutionException {
        List<Entry> input = day8.parseInput(testInput.stream());
        assertEquals(61229, day8.solvePart2(input));
        assertEquals(1007675, day8.solvePart2(actualInput));
    }
}