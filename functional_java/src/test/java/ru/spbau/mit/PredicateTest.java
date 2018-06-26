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

    private class MockPredicate<T> implements Predicate<T> {

        private boolean wasUsed = false;
        private Predicate<T> predicate;

        MockPredicate(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @Override
        public Boolean apply(T argument) {
            wasUsed = true;
            return predicate.apply(argument);
        }
    }

    @Test
    public void testLaziness() {
        MockPredicate<Object> isNull = new MockPredicate<>(Objects::isNull);

        MockPredicate<Object> alwaysTrue = new MockPredicate<>(Predicate.ALWAYS_TRUE);
        Predicate<Object> lazyTrue = alwaysTrue.or(isNull);
        assertThat(lazyTrue.apply(null)).isTrue();
        assertThat(alwaysTrue.wasUsed).isTrue();
        assertThat(isNull.wasUsed).isFalse();

        MockPredicate<Object> alwaysFalse = new MockPredicate<>(Predicate.ALWAYS_FALSE);
        Predicate<Object> lazyFalse = alwaysFalse.and(isNull);
        assertThat(lazyFalse.apply(17)).isFalse();
        assertThat(alwaysFalse.wasUsed).isTrue();
        assertThat(isNull.wasUsed).isFalse();
    }

}
