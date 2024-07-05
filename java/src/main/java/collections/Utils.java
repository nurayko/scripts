package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
public class Utils {
    public static <T> T[] appendToArray(T[] source, T element) {
        T[] tmp = Arrays.copyOf(source, source.length + 1);
        tmp[source.length] = element;
        return tmp;
    }

    public static <T> List<T> arrayToList(T[] source) {
        return new ArrayList<>(List.of(source));
    }

    public static <T> List<T> removeDuplicates(List<T> source) {
        return new ArrayList<T>(
                new HashSet<T>(source)
        );
    }
}
