package q1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Course {
    private String code;
    private String name;
    private int capacity;
    private HashSet<String> enrolledStudents;
    private ArrayList<String> prerequisites;
    private LinkedList<String> waitlist;

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = capacity;
        enrolledStudents = new HashSet<>();
        prerequisites = new ArrayList<>();
        waitlist = new LinkedList<>();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashSet<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public LinkedList<String> getWaitlist() {
        return waitlist;
    }

    public void addPrerequisite(String prerequisiteCode) {
        prerequisites.add(prerequisiteCode);
    }

    public boolean isFull() {
        return enrolledStudents.size() >= capacity;
    }
}
