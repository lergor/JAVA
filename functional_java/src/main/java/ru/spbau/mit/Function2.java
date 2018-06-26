package ru.spbau.mit;

public interface Function2<F, S, R> {

    R apply(F argument1, S argument2);

    default <T> Function2<F, S, T> compose(Function1<? super R, ? extends T> other) {
        return (argument1, argument2) -> other.apply(apply(argument1, argument2));
    }

    default Function1<S, R> bind1(final F argument1) {
        return argument2 -> apply(argument1, argument2);
    }

    default Function1<F, R> bind2(final S argument2) {
        return argument1 -> apply(argument1, argument2);
    }

    default Function1<F, Function1<S, R>> curry() {
        return this::bind1;
    }
}
