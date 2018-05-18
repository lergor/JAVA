package ru.spbau.mit;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.internal.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.assertj.core.api.Assertions.*;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("file.txt"), "utf-8"))) {
            writer.write("string1\n");
            writer.write("string2 with kek and the word\n");
            writer.write("string3 with three spaces\n");
            writer.write("string4 with just kek\n");
        } catch (IOException a) {
            a.printStackTrace();
        }

        List<String> expected = Arrays.asList("string2 with kek and the word",
                                              "string4 with just kek");

        assertThat(SecondPartTasks.findQuotes(
                Arrays.asList("file.txt"), "kek"))
                .isEqualTo(expected);
    }

    @Test
    public void testPiDividedBy4() {
        double expected = Math.PI * Math.pow(0.5, 2);
        assertThat(SecondPartTasks.piDividedBy4()).isCloseTo(expected, within(0.01));
    }

    @Test
    public void testFindPrinter() {
        String author1 = "kek";
        String author2 = "lol";
        String author3 = "lel";
        List<String> books1 =  Arrays.asList("kekkekkek", "kek", "kekkek", "kekkekkek");
        List<String> books2 =  Arrays.asList("lollollol", "lol", "lollol");
        List<String> books3 =  Arrays.asList("lel", "lel", "lellel");
        HashMap<String, List<String>> authorsToBooks = new HashMap<>();
        authorsToBooks.put(author1, books1);
        authorsToBooks.put(author2, books2);
        authorsToBooks.put(author3, books3);
        assertThat(SecondPartTasks.findPrinter(authorsToBooks)).isEqualTo(author1);
    }

    @Test
    public void testCalculateGlobalOrder() {
        HashMap<String, Integer> order1 = new HashMap<>();
        order1.put("potato", 1);
        order1.put("tomato", 2);
        order1.put("water", 3);

        HashMap<String, Integer> order2 = new HashMap<>();
        order2.put("water", 1);
        order2.put("watermelon", 2);
        order2.put("potato", 3);

        HashMap<String, Integer> order3 = new HashMap<>();
        order3.put("potato", 1);
        order3.put("apple", 2);
        order3.put("tomato", 3);

        HashMap<String, Integer> expected = new HashMap<>();
        expected.put("potato", 5);
        expected.put("tomato", 5);
        expected.put("water", 4);
        expected.put("watermelon", 2);
        expected.put("apple", 2);

        assertThat(SecondPartTasks.calculateGlobalOrder(
                Arrays.asList(order1, order2, order3))).isEqualTo(expected);
    }
}