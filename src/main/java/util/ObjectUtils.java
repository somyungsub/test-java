package util;

import java.util.ArrayList;
import java.util.List;

public class ObjectUtils {

    public static <T> List<T> deepCopy(List<T> list) {
        List<T> copy = new ArrayList<>();
        for (T t : list) {
            if (t instanceof List) {
                List list1 = deepCopy(((List) t));
                copy.add((T) list1);
                continue;
            }
            copy.add(t);
        }
        return copy;
    }
}
