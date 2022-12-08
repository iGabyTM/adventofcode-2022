package me.gabytm.adventofcode.days;

import java.util.Arrays;

public class Day8 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day8());
    }

    private Day8() {
        super(8);
    }

    private Integer[][] getInput() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                .map(it -> it.split(""))
                .map(it -> Arrays.stream(it).map(Integer::parseInt))
                .map(it -> it.toArray(Integer[]::new))
                .toArray(Integer[][]::new);
    }

    @SuppressWarnings("DuplicatedCode")
    private boolean isVisible(final Integer[][] map, final int x, final int y) {
        final var height = map[x][y];
        var visibleUp = true;
        var visibleDown = true;
        var visibleLeft = true;
        var visibleRight = true;

        // Up
        for (var x2 = 0; x2 < x; x2++) {
            if (map[x2][y] >= height) {
                visibleUp = false;
                break;
            }
        }

        // Down
        for (var x2 = x + 1; x2 < map.length; x2++) {
            if (map[x2][y] >= height) {
                visibleDown = false;
                break;
            }
        }

        // Left
        for (var y2 = 0; y2 < y; y2++) {
            if (map[x][y2] >= height) {
                visibleLeft = false;
                break;
            }
        }

        // Right
        for (var y2 = y + 1; y2 < map.length; y2++) {
            if (map[x][y2] >= height) {
                visibleRight = false;
                break;
            }
        }

        return visibleUp || visibleDown || visibleLeft || visibleRight;
    }

    @SuppressWarnings("DuplicatedCode")
    private int countVisible(final Integer[][] map, final int x, final int y) {
        var visibleUp = 0;
        var visibleDown = 0;
        var visibleLeft = 0;
        var visibleRight = 0;

        // Check up only if the tree is not on the edge
        if (x > 0) {
            for (var x2 = x - 1; x2 >= 0; x2--) {
                visibleUp++;

                if (map[x2][y] >= map[x][y]) {
                    break;
                }
            }
        }

        // Check down only of the tree is not on the edge
        if (x < map.length) {
            for (var x2 = x + 1; x2 < map.length; x2++) {
                visibleDown++;

                if (map[x2][y] >= map[x][y]) {
                    break;
                }
            }
        }

        // Check left only if the tree is not on the edge
        if (y > 0) {
            for (var y2 = y - 1; y2 >= 0; y2--) {
                visibleLeft++;

                if (map[x][y2] >= map[x][y]) {
                    break;
                }
            }
        }

        // Check right only if the tree is not on the edge
        if (y < map.length) {
            for (var y2 = y + 1; y2 < map.length; y2++) {
                visibleRight++;

                if (map[x][y2] >= map[x][y]) {
                    break;
                }
            }
        }

        return visibleUp * visibleDown * visibleLeft * visibleRight;
    }

    @Override
    public Integer solveFirstPart() {
        var map = getInput();
        var count = 0;

        for (var x = 1; x < map.length - 1; x++) {
            for (var y = 1; y < map.length - 1; y++) {
                if (isVisible(map, x, y)) {
                    count++;
                }
            }
        }

        // Count + the perimeter of the area (because all trees from edges are visible)
        return count + ((map.length - 1) * 4);
    }

    @Override
    public Integer solveSecondPart() {
        var map = getInput();
        var max = 0;

        for (var x = 1; x < map.length - 1; x++) {
            for (var y = 1; y < map.length - 1; y++) {
                max = Math.max(max, countVisible(map, x, y));
            }
        }

        return max;
    }

}
