package me.gabytm.adventofcode.days;

public class Day10 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day10());
    }

    private Day10() {
        super(10);
    }

    private int increaseStrength(int cycle, int x, int strength) {
        if (cycle == 20 || (cycle + 20) % 20 == 0) {
            return x * cycle;
        }

        return strength;
    }

    @Override
    public Integer solveFirstPart() {
        var x = 1;
        var cycle = 0;
        var total = 0;

        for (var command : getInput(1, "\n")) {
            if (cycle % 40 == 20) {
                total += cycle * x;
            }

            cycle++;

            if (command.startsWith("addx")) {
                if (cycle % 40 == 20) {
                    total += cycle * x;
                }

                x += Integer.parseInt(command.trim().split(" ")[1]);
                cycle++;
            }
        }

        return total;
    }

    @Override
    public Integer solveSecondPart() {
        return null;
    }

}
