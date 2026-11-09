// Name: Himantveer Kaur
// Roll No: 1024170271
// Question 1

import java.io.*;

public class StudentFileHandling {

    // copy binary file using byte streams
    static void copyBinaryFile(String source, String dest) {
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fin = new FileInputStream(source);
            fout = new FileOutputStream(dest);
            int b;
            while ((b = fin.read()) != -1) {
                fout.write(b);
            }
            System.out.println("Binary file copied successfully");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while copying: " + e.getMessage());
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing streams");
            }
        }
    }

    // write student details using character streams
    static void writeStudents(String fileName) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            fw.write("101,Aman Sharma,CSE,8.5\n");
            fw.write("102,Simran Kaur,CSE,9.1\n");
            fw.write("103,Rohan Verma,ECE,7.8\n");
            fw.write("104,Priya Singh,IT,8.9\n");
            System.out.println("Student details written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing writer");
            }
        }
    }

    // read student details using character streams
    static void readStudents(String fileName) {
        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
            int ch;
            System.out.println("Student details:");
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while reading: " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing reader");
            }
        }
    }

    public static void main(String[] args) {
        copyBinaryFile("input.bin", "output.bin");
        writeStudents("students.txt");
        readStudents("students.txt");
    }
}
