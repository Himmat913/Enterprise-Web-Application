import java.util.Scanner;

public class Ex3_Calculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            String input1 = sc.nextLine();
            System.out.print("Enter second number: ");
            String input2 = sc.nextLine();
            System.out.print("Enter operator (+, -, *, /): ");
            String operator = sc.nextLine();

            try {
                double num1 = Double.parseDouble(input1);
                double num2 = Double.parseDouble(input2);
                double result;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Cannot divide by zero");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + operator);
                }
                System.out.println("Result: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric input");
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}