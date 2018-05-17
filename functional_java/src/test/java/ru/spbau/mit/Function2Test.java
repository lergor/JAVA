package ru.spbau.mit;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class Function2Test {

    private Function2<String, Integer, Character> getSymbol = String::charAt;
    private Function2<Integer, Integer, Integer> mult = (i1, i2) -> i1 * i2;

    @Test
    public void testApply() {
        assertThat(getSymbol.apply("Kek", 1)).isEqualTo('e');
    }

    @Test
    public void testBind() {
        Function1<Integer, Character> getSymbolFromKek = getSymbol.bind1("Kek");
        Function1<String, Character> getSecondSymbol = getSymbol.bind2(1);
        assertThat(getSymbolFromKek.apply(2)).isEqualTo('k');
        assertThat(getSecondSymbol.apply("lol")).isEqualTo('o');
    }

    @Test
    public void testCurry() {
        Function1<Integer, Function1<Integer, Integer>> curried =
                mult.curry();
        assertThat(curried.apply(2).apply(3)).isEqualTo(6);
        assertThat(curried.apply(-3).apply(2)).isEqualTo(-6);
    }

    @Test
    public void testCompose() {
        Predicate<Character> isE = c -> c.equals('e');
        Function2<String, Integer, Boolean> symbolFromStringIsE = getSymbol.compose(isE);
        assertThat(symbolFromStringIsE.apply("kek", 1)).isTrue();
        assertThat(symbolFromStringIsE.apply("lol", 1)).isFalse();
    }

    @Test
    public void testComposeStringToObject() {
        Function1<Object, String> toString = Object::toString;
        Function2<Integer, Integer, String> stringOfProduct = mult.compose(toString);
        assertThat(stringOfProduct.apply(2, 17)).isEqualTo("34");
    }

    @Test
    public void testComposeObjectToString() {
        Function1<Integer, String> integerToString = Object::toString;
        Function2<Integer, Integer, String> stringOfProduct = mult.compose(integerToString);
        assertThat(stringOfProduct.apply(2, 17)).isEqualTo("34");
    }

}
