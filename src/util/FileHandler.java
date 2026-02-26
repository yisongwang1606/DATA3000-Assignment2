package util;

import model.Employee;
import java.io.*;

public class FileHandler {

    public static int readEmployees(String path, Employee[] employees) throws IOException {

        String line;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null && count < employees.length) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double hoursWorked = Double.parseDouble(parts[2].trim());
                double hourlyRate = Double.parseDouble(parts[3].trim());
                double deductionProvince = Double.parseDouble(parts[4].trim());
                double deductionFederal = Double.parseDouble(parts[5].trim());
                double educationAllowance = Double.parseDouble(parts[6].trim());

                employees[count++] = new Employee(
                        id, name, hoursWorked, hourlyRate,
                        deductionProvince, deductionFederal,
                        educationAllowance
                );
            }
        }

        return count;
    }

    public static void writeEmployees(String outputFile,
                                      Employee[] employees,
                                      int size) throws IOException {

        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for (int i = 0; i < size; i++) {
                writer.println(employees[i].toString());
            }
        }
    }

    public static Employee[] copy(Employee[] arr, int size) {
        Employee[] copy = new Employee[arr.length];

        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }

        return copy;
    }
}
