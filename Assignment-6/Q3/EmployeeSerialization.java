// Name: Himantveer Kaur
// Roll No: 1024170271
// Question 3

import java.io.*;

public class EmployeeSerialization {

    // serialization
    static void writeEmployees(String fileName) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(new Employee(1, "Amit Kumar", "IT", 55000));
            oos.writeObject(new Employee(2, "Neha Gupta", "HR", 48000));
            oos.writeObject(new Employee(3, "Ravi Singh", "Finance", 62000));
            oos.writeObject(new Employee(4, "Kiran Kaur", "Sales", 45000));
            System.out.println("Employees serialized to " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing: " + e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing stream");
            }
        }
    }

    // deserialization
    static void readEmployees(String fileName) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(fileName));
            System.out.println("Employees read from file:");
            while (true) {
                Employee e = (Employee) ois.readObject();
                System.out.println(e);
            }
        } catch (EOFException e) {
            System.out.println("End of file reached");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while reading: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing stream");
            }
        }
    }

    public static void main(String[] args) {
        writeEmployees("employees.ser");
        readEmployees("employees.ser");
    }
}
