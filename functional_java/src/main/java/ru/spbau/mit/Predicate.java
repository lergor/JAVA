package ru.spbau.mit;

public interface Predicate<T> extends Function1<T, Boolean> {

    Boolean apply(T argument);

    default Predicate<T> and(final Predicate<? super T> other) {
        return argument -> apply(argument) && other.apply(argument);
    }

    default Predicate<T> or(final Predicate<? super T> other) {
        return argument -> apply(argument) || other.apply(argument);
    }


    default Predicate<T> not() {
        return argument -> ! apply(argument);
    }

    Predicate<Object> ALWAYS_TRUE = argument -> true;

    Predicate<Object> ALWAYS_FALSE =  argument -> false;
}
