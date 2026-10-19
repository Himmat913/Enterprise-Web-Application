import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class GenericUtil {

    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        return value.orElse(defaultValue);
    }

    public static <T extends Comparable<T>> T max(T a, T b) {
        if (a.compareTo(b) >= 0) {
            return a;
        }
        return b;
    }

    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5_GenericUtility {
    public static void main(String[] args) {
        System.out.println("isEqual");
        System.out.println(GenericUtil.isEqual("java", "java"));
        System.out.println(GenericUtil.isEqual("java", "Java"));
        System.out.println(GenericUtil.isEqual(10, 10));
        System.out.println(GenericUtil.isEqual(10, 20));
        System.out.println(GenericUtil.isEqual(null, null));

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        System.out.println(GenericUtil.isEqual(list1, list2));

        System.out.println();
        System.out.println("getDefault");
        Optional<String> name = Optional.of("Himantveer");
        Optional<String> emptyName = Optional.empty();
        System.out.println(GenericUtil.getDefault(name, "Unknown"));
        System.out.println(GenericUtil.getDefault(emptyName, "Unknown"));
        Optional<Integer> emptyNumber = Optional.empty();
        System.out.println(GenericUtil.getDefault(emptyNumber, 0));

        System.out.println();
        System.out.println("max");
        System.out.println(GenericUtil.max("apple", "banana"));
        System.out.println(GenericUtil.max(45, 32));
        System.out.println(GenericUtil.max(3.14, 2.71));
        System.out.println(GenericUtil.max('a', 'z'));

        System.out.println();
        System.out.println("isNull");
        String text = null;
        Integer number = 25;
        System.out.println(GenericUtil.isNull(text));
        System.out.println(GenericUtil.isNull(number));
        System.out.println(GenericUtil.isNull(list1));
    }
}
