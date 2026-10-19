public class Q1_GenericSum {

    public static <T extends Number> double sum(T[] arr) {
        double total = 0;
        for (T x : arr) {
            total += x.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        Integer[] ints = {10, 20, 30, 40, 50};
        Float[] floats = {1.5f, 2.5f, 3.25f};
        Double[] doubles = {10.5, 20.25, 30.75};

        System.out.println("Sum of integers: " + sum(ints));
        System.out.println("Sum of floats: " + sum(floats));
        System.out.println("Sum of doubles: " + sum(doubles));
    }
}
