package me.gabytm.adventofcode.days;

public class Day6 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day6());
    }

    private Day6() {
        super(6);
    }

    private int solve(final int bufferLength) {
        final var input = getInput(1).toCharArray();

        for (var i = 0; i < input.length - (bufferLength - 1); i++) {
            if (areDifferent(input, i, bufferLength)) {
                return i + bufferLength;
            }
        }

        return 0;
    }

    private boolean areDifferent(final char[] input, final int startIndex, final int prefixLength) {
        // Loop until lastIndex - 1
        for (var i = startIndex; i < startIndex + (prefixLength - 1); i++) {
            // Loop until lastIndex
            for (var j = i + 1; j < startIndex + prefixLength; j++) {
                if (input[i] == input[j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Integer solveFirstPart() {
        return solve(4);
    }

    @Override
    public Integer solveSecondPart() {
        return solve(14);
    }

}
