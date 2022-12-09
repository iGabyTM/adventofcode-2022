package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Math.sqrt;

public class Day9 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day9());
    }

    private Day9() {
        super(9);
    }

    private int distance(final Pair<Integer, Integer> tail, final Pair<Integer, Integer> head) {
        return (int) sqrt(Math.pow(head.first() - tail.first(), 2) + Math.pow(head.second() - tail.second(), 2));
    }

    @Override
    public Integer solveFirstPart() {
        var visited = new HashSet<String>();
        var headPos = Pair.of(0, 0);
        var tailPos = Pair.of(0, 0);

        visited.add(tailPos.toString());

        for (var line : getInput(1, "\n")) {
            var parts = line.trim().split(" ");
            var amount = Integer.parseInt(parts[1]);

            for (var i = 0; i < amount; i++) {
                var currentHeadPos = headPos.copy();

                headPos = switch (parts[0]) {
                    case "U" -> Pair.of(headPos.first(), headPos.second() + 1);
                    case "D" -> Pair.of(headPos.first(), headPos.second() - 1);
                    case "L" -> Pair.of(headPos.first() - 1, headPos.second());
                    default -> Pair.of(headPos.first() + 1, headPos.second());
                };

                if (distance(tailPos, headPos) <= 1) {
                    continue;
                }

                tailPos = currentHeadPos;
                visited.add(tailPos.toString());
            }
        }

        return visited.size();
    }

    @Override
    public Integer solveSecondPart() {
        return null;
    }

}
