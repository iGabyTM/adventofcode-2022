package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.util.Pair;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day4 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day4());
    }

    final Pattern pattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

    private Day4() {
        super(4);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private Stream<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getInput() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                .map(pattern::matcher)
                .map(it -> {
                    it.find();
                    return Pair.of(
                            Pair.of(
                                    Integer.parseInt(it.group(1)),
                                    Integer.parseInt(it.group(2))
                            ),
                            Pair.of(
                                    Integer.parseInt(it.group(3)),
                                    Integer.parseInt(it.group(4))
                            )
                    );
                });
    }

    /**
     * Checks if {@code a} is inside {@code group}
     */
    private boolean isInside(final int a, final Pair<Integer, Integer> group) {
        return group.first() <= a && group.second() >= a;
    }

    @Override
    public Integer solveFirstPart() {
        return (int) getInput()
                .filter(it -> {
                    final var a = it.first();
                    final var b = it.second();
                    // First group contains second || second group contains first
                    return (a.first() <= b.first() && a.second() >= b.second()) || (b.first() <= a.first() && b.second() >= a.second());
                })
                .count();
    }

    @Override
    public Integer solveSecondPart() {
        return (int) getInput()
                .filter(it -> {
                    final var a = it.first();
                    final var b = it.second();

                    // First group contains second
                    if (isInside(b.first(), a) || isInside(b.second(), a)) {
                        return true;
                    }

                    // Second group contains first
                    return isInside(a.first(), b) || isInside(a.second(), b);
                })
                .count();
    }

}
