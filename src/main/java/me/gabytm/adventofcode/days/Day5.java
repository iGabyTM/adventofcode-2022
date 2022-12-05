package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5 extends Day<String> {

    public static void main(String[] args) {
        showAnswer(new Day5());
    }

    final Pattern pattern = Pattern.compile("move (?<amount>\\d+) from (?<source>\\d) to (?<destination>\\d)");

    private Day5() {
        super(5);
    }

    /**
     * @return a pair containing index from where commands start and the list of stacks of crates
     */
    private Pair<Integer, List<List<Character>>> processInput(final String[] input) {
        final var stacks = new ArrayList<List<Character>>(9);

        for (var i = 0; i < 9; i++) {
            stacks.add(new ArrayList<>());
        }

        var index = 0;

        for (; index < input.length; index++) {
            final var line = input[index].trim();

            // Skip to commands
            if (line.startsWith("1")) {
                index += 2;
                break;
            }

            // Replace spaces with a dummy crate
            final var crates = line.replace("    ", " [-]").split(" ");

            for (var j = 0; j < crates.length; j++) {
                final var ch = crates[j].charAt(1);

                // Add the crate to the stack if it is not dummy
                if (ch != '-') {
                    stacks.get(j).add(ch);
                }
            }
        }

        // Reverse the order of each stack
        for (var j = 0; j < stacks.size(); j++) {
            final var temp = stacks.get(j);
            Collections.reverse(temp);
            stacks.set(j, temp);
        }

        return Pair.of(index, stacks);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String solve(BiConsumer<Pair<List<Character>, List<Character>>, Integer> consumer) {
        final var input = getInput(1, "\n");
        final var pair = processInput(input);
        final var stacks = pair.second();
        var index = pair.first();

        var matcher = pattern.matcher("");

        // Move the crates between stacks
        for (; index < input.length; index++) {
            matcher.reset(input[index].trim());
            matcher.find();

            final var amount = Integer.parseInt(matcher.group("amount"));
            final var source = Integer.parseInt(matcher.group("source")) - 1;
            final var destination = Integer.parseInt(matcher.group("destination")) - 1;

            final var sourceList = stacks.get(source);
            final var destionationList = stacks.get(destination);
            final var size = sourceList.size();

            if (size > 0) {
                consumer.accept(Pair.of(sourceList, destionationList), amount);
            }
        }

        return stacks.stream()
                .map(it -> it.isEmpty() ? null : it.get(it.size() - 1))
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @Override
    public String solveFirstPart() {
        return solve((lists, amount) -> {
            final var source = lists.first();
            final var destination = lists.second();
            final var size = source.size();

            // Move each crate one by one
            for (var j = size - 1; j >= Math.max(0, size - amount); j--) {
                destination.add(source.remove(j));
            }
        });
    }

    @Override
    public String solveSecondPart() {
        return solve((lists, amount) -> {
            final var source = lists.first();
            final var destination = lists.second();
            final var size = source.size();

            // Move all crates at once
            destination.addAll(source.subList(Math.max(0, size - amount), size));
            source.subList(Math.max(0, size - amount), size).clear();
        });
    }

}
