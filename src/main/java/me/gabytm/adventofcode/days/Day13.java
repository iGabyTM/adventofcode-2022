package me.gabytm.adventofcode.days;

import java.util.Arrays;

public class Day13 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day13());
    }

    private Day13() {
        super(13);
    }

    @Override
    public Integer solveFirstPart() {
        var input = getInput(1, "\n");

        for (var i = 0; i < input.length - 1; i++) {
            var first = input[i];
            var second = input[++i];
            i++;
        }

        return null;
    }

    @Override
    public Integer solveSecondPart() {
        return null;
    }
}
