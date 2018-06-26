package ru.spbau.mit;

public interface Function1<P, R> {

    R apply(P argument);

    default <T> Function1<P, T> compose(final Function1<? super R, T> other) {
        return argument -> other.apply(apply(argument));
    }
}
