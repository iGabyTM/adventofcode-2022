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

    public Pair<A, B> copy() {
        return Pair.of(first(), second());
    }

    @Override
    public String toString() {
        return first() + ";" + second;
    }

    @Override
    public boolean equals(Object obj) {
        return first.equals(((Pair<A, B>) obj).first) && second.equals(((Pair<A, B>) obj).second);
    }

}
