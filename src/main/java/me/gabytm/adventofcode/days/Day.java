package me.gabytm.adventofcode.days;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public abstract class Day<T> {

    protected final int number;

    protected Day(int number) {
        this.number = number;
    }

    protected static void showAnswer(final Day<?> day) {
        System.out.println("Day #" + day.number);
        System.out.println("\tFirst part: " + day.solveFirstPart());
        System.out.println("\tSecond part: " + day.solveSecondPart());
    }

    protected String getInput(final int part) {
        try {
            return new String(this.getClass().getResourceAsStream("/%d.%d.txt".formatted(number, part)).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String[] getInput(final int part, final String regex) {
        final var input = getInput(part);
        return input == null ? null : input.split(regex);
    }

    public abstract T solveFirstPart();

    public abstract T solveSecondPart();

}
