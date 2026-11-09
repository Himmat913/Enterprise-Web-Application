// Name: Himantveer Kaur
// Roll No: 1024170271
// Question 2

import java.io.*;

public class LogAnalyzer {

    public static void main(String[] args) {
        BufferedReader br = null;
        BufferedWriter bw = null;
        int total = 0;
        int errorCount = 0;
        int warningCount = 0;

        try {
            br = new BufferedReader(new FileReader("app.log"));
            bw = new BufferedWriter(new FileWriter("filtered.log"));

            String line;
            while ((line = br.readLine()) != null) {
                total++;
                if (line.contains("ERROR")) {
                    errorCount++;
                    bw.write(line);
                    bw.newLine();
                } else if (line.contains("WARNING")) {
                    warningCount++;
                    bw.write(line);
                    bw.newLine();
                }
            }

            System.out.println("Total log entries: " + total);
            System.out.println("ERROR entries: " + errorCount);
            System.out.println("WARNING entries: " + warningCount);
            System.out.println("Filtered entries saved in filtered.log");
        } catch (FileNotFoundException e) {
            System.out.println("Log file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing files");
            }
        }
    }
}
