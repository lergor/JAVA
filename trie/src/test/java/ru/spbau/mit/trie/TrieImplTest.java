package ru.spbau.mit.trie;

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
    public void AddSizeContainsAddTest() {
        assertFalse(testTrie.add(""));
        assertEquals(0, testTrie.size());
        assertFalse(testTrie.contains("address"));

        assertTrue(testTrie.add("address"));
        assertTrue(testTrie.contains("address"));
        assertEquals(1, testTrie.size());

        assertFalse(testTrie.add("address"));

        for (int i = 1; i < stringsForTest.length; i++) {
            assertTrue(testTrie.add(stringsForTest[i]));
            assertEquals(i + 1, testTrie.size());
        }

        for (String aStringsForTest : stringsForTest) {
            assertTrue(testTrie.contains(aStringsForTest));
            assertFalse(testTrie.add(aStringsForTest));
        }

        assertEquals(stringsForTest.length, testTrie.size());
    }

    @Test
    public void ContainsRemoveContainsSizeTest() {

        assertEquals(stringsForTest.length, testTrie.size());

        for (int i = 0; i < stringsForTest.length; i++) {
            assertTrue(testTrie.contains(stringsForTest[i]));
            assertTrue(testTrie.remove(stringsForTest[i]));
            assertFalse(testTrie.contains(stringsForTest[i]));
            assertEquals(10 - i, testTrie.size());
        }
        assertEquals(0, testTrie.size());

        assertTrue(testTrie.add("abc"));
        assertTrue(testTrie.add("ab"));
        assertTrue(testTrie.contains("abc"));
        assertTrue(testTrie.contains("ab"));
        assertEquals(2, testTrie.size());
        assertTrue(testTrie.remove("abc"));
        assertTrue(testTrie.contains("ab"));
        assertTrue(testTrie.remove("ab"));
        assertEquals(0, testTrie.size());

    }

    @Test
    public void NumOfPrefixAddPrefixTest() {

        assertEquals(0, testTrie.howManyStartsWithPrefix("a"));
        assertEquals(0, testTrie.howManyStartsWithPrefix("grand"));
        assertEquals(0, testTrie.howManyStartsWithPrefix("to"));
        assertEquals(0, testTrie.howManyStartsWithPrefix("oo"));

        for (String aStringsForPrefix : stringsForPrefix) {
            assertTrue(testTrie.add(aStringsForPrefix));
        }

        assertEquals(3, testTrie.howManyStartsWithPrefix("a"));
        assertEquals(3, testTrie.howManyStartsWithPrefix("grand"));
        assertEquals(3, testTrie.howManyStartsWithPrefix("to"));
        assertEquals(0, testTrie.howManyStartsWithPrefix("oo"));

        for (String aStringsForPrefix : stringsForPrefix) {
            assertFalse(testTrie.add(aStringsForPrefix));
        }

        assertEquals(3, testTrie.howManyStartsWithPrefix("a"));
        assertEquals(3, testTrie.howManyStartsWithPrefix("grand"));
        assertEquals(3, testTrie.howManyStartsWithPrefix("to"));
        assertEquals(0, testTrie.howManyStartsWithPrefix("oo"));

        assertTrue(testTrie.remove("grandparents"));
        assertEquals(2, testTrie.howManyStartsWithPrefix("grand"));
        assertTrue(testTrie.remove("grandmother"));
        assertTrue(testTrie.remove("grandfather"));
        assertEquals(0, testTrie.howManyStartsWithPrefix("grand"));
    }

}
