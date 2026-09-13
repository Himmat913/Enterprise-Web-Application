public class Ex2_NestedTryCatch {
    public static void main(String[] args) {
        try {
            int[] arr = {1, 2, 3};

            try {
                int result = 10 / 0;
                System.out.println(result);
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: " + e);
            }

            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Outer catch: " + e);
        }
    }
}
