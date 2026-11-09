// Name: Himantveer Kaur
// Roll No: 1024170271
// Question 3

import java.io.Serializable;

public class Employee implements Serializable {

    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return id + " | " + name + " | " + department + " | " + salary;
    }
}
