package ru.spbau.mit.dictionary;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class DictImplTest {
    static private DictionaryImpl testDict = new DictionaryImpl();

    static private String[] values = {"absent", "angry",
            "people", "implementation", "engineer", "grandmother",
            "grandfather", "additional", "today", "tomorrow", "town",
            "accord", "knife", "leisure", "locked", "location",
            "strike", "youthful", "grey", "curse"};

    static private String[] keys = {"one", "two", "three",
            "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen", "twenty"};

    @Test
    public void PutSizeContainsTest() {
        assertThat(testDict.size()).isEqualTo(0);
        assertThat(testDict.put("key", "value")).isEqualTo(null);
        assertThat(testDict.contains("key")).isTrue();
        assertThat(testDict.size()).isEqualTo(1);

        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.put(keys[i], values[i])).isEqualTo(null);
            assertThat(testDict.contains(keys[i])).isTrue();
        }
        assertThat(testDict.size()).isEqualTo(1 + keys.length);

        assertThat(testDict.contains("cookies")).isFalse();

        testDict.clear();
        assertThat(testDict.size()).isEqualTo(0);
    }

    @Test
    public void PutGetTest() {
        assertThat(testDict.size()).isEqualTo(0);
        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.put(keys[i], values[i])).isEqualTo(null);
            assertThat(testDict.contains(keys[i])).isTrue();
        }
        assertThat(testDict.size()).isEqualTo(keys.length);

        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.get(keys[i])).isEqualTo(values[i]);
        }
        testDict.clear();
        assertThat(testDict.size()).isEqualTo(0);
    }

    @Test
    public void PutOtherValueTest() {
        assertThat(testDict.size()).isEqualTo(0);
        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.put(keys[i], values[i])).isEqualTo(null);
            assertThat(testDict.contains(keys[i])).isTrue();
        }
        assertThat(testDict.size()).isEqualTo(keys.length);

        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.put(keys[i], keys[i])).isEqualTo(values[i]);
            assertThat(testDict.contains(keys[i])).isTrue();
        }
        assertThat(testDict.size()).isEqualTo(keys.length);
        testDict.clear();
        assertThat(testDict.size()).isEqualTo(0);
    }

    @Test
    public void PutContainsRemoveContainsTest() {
        assertThat(testDict.size()).isEqualTo(0);
        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.put(keys[i], values[i])).isEqualTo(null);
            assertThat(testDict.contains(keys[i])).isTrue();
        }
        assertThat(testDict.size()).isEqualTo(keys.length);

        for (int i = 0; i < keys.length; i++) {
            assertThat(testDict.remove(keys[i])).isEqualTo(values[i]);
            assertThat(testDict.contains(keys[i])).isFalse();
            assertThat(testDict.remove(keys[i])).isEqualTo(null);
        }
        assertThat(testDict.size()).isEqualTo(0);
        testDict.clear();
    }


}
