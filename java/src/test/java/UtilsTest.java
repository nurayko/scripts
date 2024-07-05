import java.util.ArrayList;
import java.util.List;

import collections.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {
    @Test
    void testAppend() {
        String[] strings = { "one", "two", "three" };
        String[] result = Utils.appendToArray(strings, "four");
        Assertions.assertEquals(4, result.length);
        Assertions.assertEquals("four", result[3]);
    }

    @Test
    void testDuplicates() {
        List<String> strings = new ArrayList<>(List.of("one", "two", "three", "three"));
        List<String> result = Utils.removeDuplicates(strings);
        Assertions.assertEquals(3, result.size());
    }
}
