import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {
    private String studentName;
    private List<T> marks;
    private double maxMarks;

    public Result(String studentName, List<T> marks, double maxMarks) {
        this.studentName = Objects.requireNonNull(studentName, "Student name is required");
        this.marks = Objects.requireNonNull(marks, "Marks are required");
        this.maxMarks = maxMarks;
    }

    public double getTotal() {
        double total = 0;
        for (T m : marks) {
            total += m.doubleValue();
        }
        return total;
    }

    public double getAverage() {
        if (marks.isEmpty()) {
            return 0;
        }
        return getTotal() / marks.size();
    }

    public double getPercentage() {
        if (marks.isEmpty()) {
            return 0;
        }
        return getTotal() / (marks.size() * maxMarks) * 100;
    }

    public boolean hasPassed(double threshold) {
        return getPercentage() >= threshold;
    }

    public Optional<String> getResult(double threshold) {
        if (marks.isEmpty()) {
            return Optional.empty();
        }
        String status = hasPassed(threshold) ? "Pass" : "Fail";
        String text = studentName + " | Total: " + getTotal()
                + " | Average: " + String.format("%.2f", getAverage())
                + " | Percentage: " + String.format("%.2f", getPercentage()) + "%"
                + " | " + status;
        return Optional.of(text);
    }

    public String getStudentName() {
        return studentName;
    }
}

public class Q3_StudentResult {
    public static void main(String[] args) {
        double threshold = 40;

        List<Integer> intMarks = new ArrayList<>();
        intMarks.add(78);
        intMarks.add(85);
        intMarks.add(69);
        intMarks.add(90);
        intMarks.add(72);
        Result<Integer> r1 = new Result<>("Himantveer Kaur", intMarks, 100);

        List<Double> doubleMarks = new ArrayList<>();
        doubleMarks.add(32.5);
        doubleMarks.add(41.0);
        doubleMarks.add(28.75);
        Result<Double> r2 = new Result<>("Student Two", doubleMarks, 100);

        List<Integer> noMarks = new ArrayList<>();
        Result<Integer> r3 = new Result<>("Student Three", noMarks, 100);

        System.out.println(r1.getResult(threshold).orElse("Marks not available for " + r1.getStudentName()));
        System.out.println(r2.getResult(threshold).orElse("Marks not available for " + r2.getStudentName()));
        System.out.println(r3.getResult(threshold).orElse("Marks not available for " + r3.getStudentName()));

        try {
            Result<Integer> r4 = new Result<>(null, intMarks, 100);
            System.out.println(r4.getStudentName());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            Result<Integer> r5 = new Result<>("Student Five", null, 100);
            System.out.println(r5.getStudentName());
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
