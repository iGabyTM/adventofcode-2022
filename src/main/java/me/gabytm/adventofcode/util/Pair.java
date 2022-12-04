package me.gabytm.adventofcode.util;

public class Pair<A, B> {

    private final A first;
    private final B second;

    public static <A, B> Pair<A, B> of(final A first, final B second) {
        return new Pair<>(first, second);
    }

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A first() {
        return first;
    }

    public B second() {
        return second;
    }

}
