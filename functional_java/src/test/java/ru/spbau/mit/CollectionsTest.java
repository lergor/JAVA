package ru.spbau.mit;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsTest {

    private ArrayList<Integer> numbers = new ArrayList<>();
    private Predicate<Integer> moreThanTen = i -> i > 10;
    private Function2<Integer, Integer, Integer> sum = (i, j) -> i + j;
    private Function2<Integer, Integer, Integer> diff = (i, j) -> i - j;

    @Before
    public void addStrings() {
        numbers.add(1);
        numbers.add(2);
        numbers.add(4);
        numbers.add(17);
        numbers.add(25);
        numbers.add(50);
        numbers.add(-17);
    }

    @Test
    public void testMap() {
        List<Integer> expected = numbers.stream()
                .map(i -> i * i)
                .collect(Collectors.toList());
        Function1<Integer, Integer> square = i -> i * i;
        ArrayList<Integer> squares = new ArrayList<>(Collections.map(square, numbers));
        assertThat(squares.size()).isEqualTo(expected.size());
        assertThat(squares).isEqualTo(expected);
    }

    @Test
    public void testFilter() {
        List<Integer> expected = numbers.stream()
                .filter(i -> i > 10)
                .collect(Collectors.toList());
        ArrayList<Integer> filtered = new ArrayList<>(Collections.filter(moreThanTen, numbers));
        assertThat(filtered.size()).isEqualTo(expected.size());
        assertThat(filtered).isEqualTo(expected);
    }

    @Test
    public void testTakeWhile() {
        ArrayList<Integer> prefixLessThanTen = new ArrayList<>(
                Collections.takeWhile(moreThanTen.not(), numbers));
        assertThat(prefixLessThanTen).containsExactly(1, 2, 4);
    }

    @Test
    public void testTakeUnless() {
        ArrayList<Integer> prefixLessThanTen = new ArrayList<>(
                Collections.takeUnless(moreThanTen, numbers));
        assertThat(prefixLessThanTen).containsExactly(1, 2, 4);
    }

    @Test
    public void testFoldr() {
        Integer value = Collections.foldr(sum, 3, numbers);
        assertThat(value).isEqualTo(85);
        value = Collections.foldr(diff, 0, numbers);
        assertThat(value).isEqualTo(-56);
    }

    @Test
    public void testFoldl() {
        Integer value = Collections.foldl(sum, -2, numbers);
        assertThat(value).isEqualTo(80);
        value = Collections.foldl(diff, 0, numbers);
        assertThat(value).isEqualTo(-82);
    }
}
