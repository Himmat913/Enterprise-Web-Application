package q1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RegistrationPortal {
    private HashMap<String, Course> courses = new HashMap<>();
    private HashMap<String, HashSet<String>> completedCourses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public void markCompleted(String studentId, String courseCode) {
        if (!completedCourses.containsKey(studentId)) {
            completedCourses.put(studentId, new HashSet<>());
        }
        completedCourses.get(studentId).add(courseCode);
        System.out.println(courseCode + " marked as completed for student " + studentId);
    }

    private ArrayList<String> getMissingPrerequisites(String studentId, Course course) {
        ArrayList<String> missing = new ArrayList<>();
        HashSet<String> done = completedCourses.get(studentId);
        for (String pre : course.getPrerequisites()) {
            if (done == null || !done.contains(pre)) {
                missing.add(pre);
            }
        }
        return missing;
    }

    public void registerStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course " + courseCode + " does not exist.");
            return;
        }
        if (course.getEnrolledStudents().contains(studentId)) {
            System.out.println("Student " + studentId + " is already enrolled in " + courseCode);
            return;
        }
        if (course.getWaitlist().contains(studentId)) {
            System.out.println("Student " + studentId + " is already on the waitlist of " + courseCode);
            return;
        }
        ArrayList<String> missing = getMissingPrerequisites(studentId, course);
        if (!missing.isEmpty()) {
            System.out.println("Cannot register. Missing prerequisites: " + missing);
            return;
        }
        if (!course.isFull()) {
            course.getEnrolledStudents().add(studentId);
            System.out.println("Student " + studentId + " registered in " + courseCode);
        } else {
            course.getWaitlist().add(studentId);
            System.out.println(courseCode + " is full. Student " + studentId
                    + " added to the waitlist at position " + course.getWaitlist().size());
        }
    }

    public void dropStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course " + courseCode + " does not exist.");
            return;
        }
        if (course.getEnrolledStudents().remove(studentId)) {
            System.out.println("Student " + studentId + " dropped " + courseCode);
            if (!course.getWaitlist().isEmpty()) {
                String next = course.getWaitlist().removeFirst();
                course.getEnrolledStudents().add(next);
                System.out.println("Student " + next + " moved from the waitlist to enrolled in " + courseCode);
            }
        } else if (course.getWaitlist().remove(studentId)) {
            System.out.println("Student " + studentId + " removed from the waitlist of " + courseCode);
        } else {
            System.out.println("Student " + studentId + " is not registered in " + courseCode);
        }
    }

    public void showCourse(String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course " + courseCode + " does not exist.");
            return;
        }
        System.out.println("Course: " + course.getCode() + " - " + course.getName());
        System.out.println("Capacity: " + course.getCapacity());
        System.out.println("Prerequisites: " + course.getPrerequisites());
        System.out.println("Enrolled (" + course.getEnrolledStudents().size() + "): " + course.getEnrolledStudents());
        System.out.println("Waitlist: " + course.getWaitlist());
    }

    public void showAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course c : courses.values()) {
            System.out.println(c.getCode() + " | " + c.getName() + " | Seats: "
                    + c.getEnrolledStudents().size() + "/" + c.getCapacity()
                    + " | Waitlist: " + c.getWaitlist().size()
                    + " | Prerequisites: " + c.getPrerequisites());
        }
    }
}
