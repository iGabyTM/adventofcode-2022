package me.gabytm.adventofcode.days;

import java.util.Arrays;

public class Day3 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day3());
    }

    private final String priorities = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private Day3() {
        super(3);
    }

    private int find(final String first, final String second) {
        for (var x : first.toCharArray()) {
            for (var y : second.toCharArray()) {
                if (x == y) {
                    return priorities.indexOf(x);
                }
            }
        }

        return 0;
    }

    private int find(final String first, final String second, final String third) {
        for (var x : first.toCharArray()) {
            for (var y : second.toCharArray()) {
                for (var z : third.toCharArray()) {
                    if (x == y && y == z) {
                        return priorities.indexOf(x);
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public Integer solveFirstPart() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                .mapToInt(it -> {
                    final var length = it.length();
                    return find(it.substring(0, length / 2), it.substring(length / 2));
                })
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        final var input = getInput(1, "\n");
        var total = 0;

        for (var i = 0; i < input.length - 2; i++) {
            total += find(input[i].trim(), input[++i].trim(), input[++i].trim());
        }

        return total;
    }

}
