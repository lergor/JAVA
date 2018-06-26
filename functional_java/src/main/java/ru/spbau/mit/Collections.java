package ru.spbau.mit;

import java.util.Collection;
import java.util.ArrayList;

public final class Collections {

    private Collections(){}

    public static <P, R> Collection<R> map(Function1<? super P, ? extends R> func,
                                           Collection<? extends P> collection) {
        Collection<R> result = new ArrayList<>();
        for (P elem : collection) {
            result.add(func.apply(elem));
        }
        return result;
    }

    public static <T> Collection<T> filter(Predicate<? super T> predicate,
                                          Collection<? extends T> collection) {
        Collection<T> result = new ArrayList<>();
        for (T elem : collection) {
            if (predicate.apply(elem)) {
                result.add(elem);
            }
        }
        return result;
    }

    public static <T> Collection<T> takeWhile(Predicate<? super T> predicate,
                                          Collection<? extends T> collection) {
        Collection<T> result = new ArrayList<>();
        for (T elem : collection) {
            if (!predicate.apply(elem)) {
                break;
            }
            result.add(elem);
        }
        return result;
    }

    public static <T> Collection<T> takeUnless(Predicate<? super T> predicate,
                                             Collection<? extends T> collection) {
        return takeWhile(predicate.not(), collection);
    }

    public static <T, R> R foldl(Function2<? super R, ? super T, ? extends R> function,
                                 R init, Collection<? extends T> collection) {
        for (T obj : collection) {
            init = function.apply(init, obj);
        }
        return init;
    }

    public static <T, R> R foldr(Function2<? super T, ? super R, ? extends R> func,
                                 R init, Collection<? extends T> collection) {
        ArrayList<T> values = new ArrayList<>(collection);
        java.util.Collections.reverse(values);
        for (T obj : values) {
            init = func.apply(obj, init);
        }
        return init;
    }
}
