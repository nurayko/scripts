import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import collections.Utils;
import io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilsTest {
    private final String path = "src/main/resources/test.txt";

    @Test
    void testAppend() {
        String[] strings = {"one", "two", "three"};
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

    @Test
    void testFileExists() {
        Assertions.assertTrue(FileUtils.fileExists(path));
        Assertions.assertFalse(FileUtils.fileExists(path + ".txt"));
    }

    @Test
    void testReadFile() throws IOException {
        String content = FileUtils.readFile(path);
        Assertions.assertEquals("One", content.substring(0, 3));
    }

    @Test
    void testReadFileBuffered() {
        String newLine = System.lineSeparator();
        Assertions.assertEquals(
                "One".concat(newLine).concat("Two").concat(newLine).concat("Three").concat(newLine),
                FileUtils.readFileBuffered(path)
        );
    }

    @Test
    void testReadUnderDir() throws IOException {
        FileUtils.printFilesUnderDirectory("src/main/resources");
    }
}
