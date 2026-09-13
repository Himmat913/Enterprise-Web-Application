import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

public class Ex4_StudentResult {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numSubjects = 5;
        int[] marks = new int[numSubjects];
        boolean allValid = true;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            int m = sc.nextInt();
            try {
                if (m < 0 || m > 100) {
                    throw new InvalidMarksException("Marks must be between 0 and 100");
                }
                marks[i] = m;
            } catch (InvalidMarksException e) {
                System.out.println(e.getMessage());
                allValid = false;
            }
        }

        if (allValid) {
            int total = 0;
            for (int m : marks) {
                total += m;
            }
            double percentage = (double) total / numSubjects;
            char grade;
            if (percentage >= 90) {
                grade = 'A';
            } else if (percentage >= 75) {
                grade = 'B';
            } else if (percentage >= 60) {
                grade = 'C';
            } else if (percentage >= 40) {
                grade = 'D';
            } else {
                grade = 'F';
            }

            System.out.println("Total: " + total);
            System.out.println("Percentage: " + percentage);
            System.out.println("Grade: " + grade);
        } else {
            System.out.println("Result cannot be calculated due to invalid marks");
        }

        sc.close();
    }
}
