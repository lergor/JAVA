package ru.spbau.mit;

import org.junit.Test;
import java.util.Objects;
import static org.assertj.core.api.Assertions.*;

public class PredicateTest {

    private Predicate<String> isKek = "Kek"::equals;;
    private Predicate<Integer> isEven = i -> i % 2 == 0;
    private Predicate<Integer> moreThanTen = i -> i > 10;

    @Test
    public void testApply() {
        assertThat(isKek.apply("Kek")).isTrue();
        assertThat(isKek.apply("notKek")).isFalse();
    }

    @Test
    public void testNot() {
        Predicate<Integer> isOdd = isEven.not();
        assertThat(isEven.apply(2)).isTrue();
        assertThat(isOdd.apply(2)).isFalse();
    }

    @Test
    public void testAnd() {
        Predicate<Integer> moreThanTen = i -> i > 10;
        Predicate<Integer> isEvenAndMoreThanTen = isEven.and(moreThanTen);
        assertThat(isEvenAndMoreThanTen.apply(12)).isTrue();
        assertThat(isEvenAndMoreThanTen.apply(13)).isFalse();
        assertThat(isEvenAndMoreThanTen.apply(8)).isFalse();
        assertThat(isEvenAndMoreThanTen.apply(9)).isFalse();
    }

    @Test
    public void testOr() {
        Predicate<Integer> isEvenOrMoreThanTen = isEven.or(moreThanTen);
        assertThat(isEvenOrMoreThanTen.apply(12)).isTrue();
        assertThat(isEvenOrMoreThanTen.apply(13)).isTrue();
        assertThat(isEvenOrMoreThanTen.apply(8)).isTrue();
        assertThat(isEvenOrMoreThanTen.apply(9)).isFalse();
    }

    @Test
    public void testConstants() {
        assertThat(Predicate.ALWAYS_TRUE.apply(12)).isTrue();
        assertThat(Predicate.ALWAYS_FALSE.apply("Kek!")).isFalse();
        assertThat(Predicate.ALWAYS_TRUE.apply(12)).isEqualTo
                (Predicate.ALWAYS_FALSE.not().apply("Kek!"));
    }

    @Test
    public void testLaziness() {
        Predicate<Object> isNull = Objects::isNull;
        Predicate<Object> lazyTrue = Predicate.ALWAYS_TRUE.or(isNull);
        assertThat(lazyTrue.apply(null)).isTrue();
        Predicate<Object> lazyFalse = Predicate.ALWAYS_FALSE.and(isNull);
        assertThat(lazyFalse.apply(17)).isFalse();
    }

}
