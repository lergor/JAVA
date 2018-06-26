package ru.spbau.mit;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class Function1Test {

    @Test
    public void testApply() {
        Function1<Integer, Integer> succ = i -> i + 1;
        Function1<Double, Double> succ_d = i -> i + 1;
        assertThat(succ.apply(17)).isEqualTo(18);
        assertThat(succ_d.apply(17.01)).isEqualTo(18.01);
    }

    @Test
    public void testCompose() {
        Function1<Integer, Double> half = i -> i * 0.5;
        assertThat(half.apply(5)).isEqualTo(2.5);
        Function1<Double, String> toString = Object::toString;
        assertThat(toString.apply(2.5)).isEqualTo("2.5");
        assertThat(half.compose(toString).apply(5)).isEqualTo("2.5");
    }

    @Test
    public void testComposeStringToObject() {
        Function1<Object, Integer> objectHash = Object::hashCode;
        Function1<Integer, String> toString = Object::toString;
        assertThat(toString.compose(objectHash).apply(17)).isEqualTo(1574);
    }

    @Test
    public void testComposeObjectToString() {
        Function1<Object, Integer> objectHash = Object::hashCode;
        Function1<Integer, String> toString = Object::toString;
        assertThat(objectHash.compose(toString).apply("17")).isEqualTo("1574");
    }

}
