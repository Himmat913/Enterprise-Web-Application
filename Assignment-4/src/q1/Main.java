// Name: Himantveer Kaur
// Roll No: 1024170271
package q1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationPortal portal = new RegistrationPortal();

        Course c1 = new Course("CS101", "Programming Basics", 2);
        Course c2 = new Course("CS201", "Data Structures", 2);
        c2.addPrerequisite("CS101");
        Course c3 = new Course("CS301", "Database Systems", 1);
        c3.addPrerequisite("CS101");
        c3.addPrerequisite("CS201");
        portal.addCourse(c1);
        portal.addCourse(c2);
        portal.addCourse(c3);

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- Course Registration Portal ---");
            System.out.println("1. View all courses");
            System.out.println("2. View course details");
            System.out.println("3. Add a new course");
            System.out.println("4. Mark a course as completed for a student");
            System.out.println("5. Register for a course");
            System.out.println("6. Drop a course");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                choice = -1;
                continue;
            }

            if (choice == 1) {
                portal.showAllCourses();
            } else if (choice == 2) {
                System.out.print("Course code: ");
                String code = sc.nextLine().trim().toUpperCase();
                portal.showCourse(code);
            } else if (choice == 3) {
                System.out.print("Course code: ");
                String code = sc.nextLine().trim().toUpperCase();
                System.out.print("Course name: ");
                String name = sc.nextLine().trim();
                System.out.print("Capacity: ");
                int capacity;
                try {
                    capacity = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Capacity must be a number.");
                    continue;
                }
                if (capacity <= 0) {
                    System.out.println("Capacity must be greater than 0.");
                    continue;
                }
                if (portal.getCourse(code) != null) {
                    System.out.println("Course already exists.");
                    continue;
                }
                Course course = new Course(code, name, capacity);
                System.out.print("Prerequisite codes separated by commas (or press enter for none): ");
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    for (String p : parts) {
                        course.addPrerequisite(p.trim().toUpperCase());
                    }
                }
                portal.addCourse(course);
                System.out.println("Course " + code + " added.");
            } else if (choice == 4) {
                System.out.print("Student ID: ");
                String id = sc.nextLine().trim();
                System.out.print("Completed course code: ");
                String code = sc.nextLine().trim().toUpperCase();
                portal.markCompleted(id, code);
            } else if (choice == 5) {
                System.out.print("Student ID: ");
                String id = sc.nextLine().trim();
                System.out.print("Course code: ");
                String code = sc.nextLine().trim().toUpperCase();
                portal.registerStudent(id, code);
            } else if (choice == 6) {
                System.out.print("Student ID: ");
                String id = sc.nextLine().trim();
                System.out.print("Course code: ");
                String code = sc.nextLine().trim().toUpperCase();
                portal.dropStudent(id, code);
            } else if (choice == 0) {
                System.out.println("Exiting portal.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
