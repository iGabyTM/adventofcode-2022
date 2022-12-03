package me.gabytm.adventofcode.days;

import java.util.ArrayList;
import java.util.Collections;

public class Day1 extends Day<Integer> {

    protected Day1() {
        super(1);
    }

    public static void main(String[] args) {
        Day.showAnswer(new Day1());
    }

    @Override
    public Integer solveFirstPart() {
        var max = 0;
        var current = 0;

        for (var line : getInput(1, "\n")) {
            if (line.trim().isEmpty()) {
                max = Math.max(max, current);
                current = 0;
                continue;
            }

            current += Integer.parseInt(line.trim());
        }

        return max;
    }

    @Override
    public Integer solveSecondPart() {
        var list = new ArrayList<Integer>();
        var current = 0;

        for (var line : getInput(1, "\n")) {
            if (line.trim().isEmpty()) {
                list.add(current);
                current = 0;
                continue;
            }

            current += Integer.parseInt(line.trim());
        }

        list.sort(Collections.reverseOrder(Integer::compareTo));
        return list.get(0) + list.get(1) + list.get(2);
    }

}
