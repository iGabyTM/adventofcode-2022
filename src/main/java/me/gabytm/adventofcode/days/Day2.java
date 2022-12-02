package me.gabytm.adventofcode.days;

import java.util.Arrays;

public class Day2 extends Day<Integer> {

    public static void main(String[] args) {
        Day.showAnswer(new Day2());
    }

    private Day2() {
        super(2);
    }

    private int getPoints(final char option) {
        return switch (option) {
            case 'A', 'X' -> 1; // Rock
            case 'B', 'Y' -> 2; // Paper
            default -> 3; // Scissors
        };
    }

    private char getWinningOption(final char opponent) {
        return switch (opponent) {
            case 'A' -> 'Y'; // Papers > Rock
            case 'B' -> 'Z'; // Scissors > Paper
            default -> 'X'; // Rock > Scissors
        };
    }

    private char getLosingOption(final char opponent) {
        return switch (opponent) {
            case 'A' -> 'Z'; // Scissors < Rock
            case 'B' -> 'X'; // Rock < Paper
            default -> 'Y'; // Paper < Scissors
        };
    }

    @Override
    public Integer solveFirstPart() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                // Format: "Opponent You"
                .mapToInt(it -> {
                    final char opponent = it.charAt(0);
                    final char you = it.charAt(2);

                    // Draw
                    if (opponent == you) {
                        return getPoints(you) + 3;
                    }

                    // Win
                    if (getWinningOption(opponent) == you) {
                        return getPoints(you) + 6;
                    }

                    // Lose
                    return getPoints(you);
                })
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                // Format: "Opponent Result"
                .mapToInt(it -> {
                    final char opponent = it.charAt(0);

                    return switch (it.charAt(2)) {
                        // Win
                        case 'Z' -> getPoints(getWinningOption(opponent)) + 6;

                        // Draw
                        case 'Y' -> getPoints(opponent) + 3;

                        // Lose (Z)
                        default -> getPoints(getLosingOption(opponent));
                    };
                }).sum();
    }

}
