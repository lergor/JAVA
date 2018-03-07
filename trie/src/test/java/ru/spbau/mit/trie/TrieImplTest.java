package ru.spbau.mit.trie;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TrieImplTest {

    static private TrieImpl testTrie = new TrieImpl();
    static private String[] stringsForTest = {"address", "angry",
            "people", "implementation", "engineer", "grandmother",
            "grandfather", "additional", "today", "tomorrow", "town"};

    static private String[] stringsForPrefix = {"today", "tomorrow", "town",
            "additional", "address", "angry",
            "grandfather", "grandmother", "grandparents"};

    @Test
    //add - size - contains - add
    public void test1() {
        assertFalse(testTrie.add(""));
        assertEquals(testTrie.size(), 0);
        assertFalse(testTrie.contains("address"));

        assertTrue(testTrie.add("address"));
        assertTrue(testTrie.contains("address"));
        assertEquals(testTrie.size(), 1);

        assertFalse(testTrie.add("address"));

        for (int i = 1; i < stringsForTest.length; i++) {
            assertTrue(testTrie.add(stringsForTest[i]));
            assertEquals(testTrie.size(), i + 1);
        }

        for (int i = 0; i < stringsForTest.length; i++) {
            assertTrue(testTrie.contains(stringsForTest[i]));
            assertFalse(testTrie.add(stringsForTest[i]));
        }

        assertEquals(testTrie.size(), stringsForTest.length);
    }

    @Test
    // contains - remove - contains - size
    public void test2() {
        assertEquals(testTrie.size(), stringsForTest.length);

        for (int i = 0; i < stringsForTest.length; i++) {
            assertTrue(testTrie.contains(stringsForTest[i]));
            assertTrue(testTrie.remove(stringsForTest[i]));
            assertFalse(testTrie.contains(stringsForTest[i]));
            assertEquals(testTrie.size(), 10 - i);
        }

        assertEquals(testTrie.size(), 0);
    }

    @Test
    // prefix - add - prefix
    public void test3() {
        assertEquals(testTrie.howManyStartsWithPrefix("a"), 0);
        assertEquals(testTrie.howManyStartsWithPrefix("grand"), 0);
        assertEquals(testTrie.howManyStartsWithPrefix("to"), 0);
        assertEquals(testTrie.howManyStartsWithPrefix("oo"), 0);

        for (int i = 0; i < stringsForPrefix.length; i++) {
            assertTrue(testTrie.add(stringsForPrefix[i]));
        }

        assertEquals(testTrie.howManyStartsWithPrefix("a"), 3);
        assertEquals(testTrie.howManyStartsWithPrefix("grand"), 3);
        assertEquals(testTrie.howManyStartsWithPrefix("to"), 3);
        assertEquals(testTrie.howManyStartsWithPrefix("oo"), 0);

        for (int i = 0; i < stringsForPrefix.length; i++) {
            assertFalse(testTrie.add(stringsForPrefix[i]));
        }

        assertEquals(testTrie.howManyStartsWithPrefix("a"), 3);
        assertEquals(testTrie.howManyStartsWithPrefix("grand"), 3);
        assertEquals(testTrie.howManyStartsWithPrefix("to"), 3);
        assertEquals(testTrie.howManyStartsWithPrefix("oo"), 0);

        assertTrue(testTrie.remove("grandparents"));
        assertEquals(testTrie.howManyStartsWithPrefix("grand"), 2);
        assertTrue(testTrie.remove("grandmother"));
        assertTrue(testTrie.remove("grandfather"));
        assertEquals(testTrie.howManyStartsWithPrefix("grand"), 0);
    }
}
