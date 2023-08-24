package me.gabytm.adventofcode.days;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Day7 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day7());
    }

    private final Pattern commandPattern = Pattern.compile("\\$ (?<command>ls|cd)(?<argument> \\.\\.|\\w+)?");
    private final Pattern filePattern = Pattern.compile("(dir|\\d+) ([\\w.]+)");

    private Day7() {
        super(7);
    }

    @Override
    public Integer solveFirstPart() {
        final var input = getInput(1, "\n");
        final var map = new HashMap<String, Directory>();
        map.put("~", new Directory("~"));

        var readingOutermostDirectory = true;
        final var path = new StringBuilder("~");

        // Skip first two lines, '$ cd /' and '$ ls'
        var i = 2;

        for (; i < input.length; i++) {
            final var line = input[i].trim();

            if (line.startsWith("$")) {
                break;
            }

            if (line.startsWith("dir")) {
                map.put("~/" + line.split(" ")[1], new Directory(line.split(" ")[1]));
            } else {
                map.get("~").size += Integer.parseInt(line.split(" ")[0]);
            }
        }

        for (; i < input.length; i++) {
            final var line = input[i].trim();

            if (line.equals("$ ls")) {
                continue;
            }

            if (line.startsWith("$ cd")) {
                final var cdTo = line.split(" ")[1];

                if (cdTo.equals("..")) {
                    path.delete(path.lastIndexOf("/"), path.length());
                } else {
                    path.append('/').append(cdTo);
                }

                continue;
            }

            if (line.startsWith("dir")) {

            }
        }


        return null;
    }

    @Override
    public Integer solveSecondPart() {
        return null;
    }

    private class Directory {

        private final String path;
        private int size;

        private Directory(String path) {
            this.path = path;
        }

    }

}
