package main;

import model.Employee;
import model.EmployeeByName;
import sort.SelectionSort;
import sort.QuickSort;
import search.BinarySearch;
import util.FileHandler;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class EmployeeApp {

    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null,
                "Employee Data sorting and Searching Program! \n Press OK to start program!");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the full path of employee data file <--> ");
        String path = scanner.nextLine().trim().replace("\"", "");

        System.out.println("Read employee data from file " + path);

        Employee[] employees = new Employee[1000];
        int size;

        try {
            size = FileHandler.readEmployees(path, employees);
        } catch (IOException e) {
            System.out.println("File reading error: " + e.getMessage());
            return;
        }

        Employee[] bySalary = FileHandler.copy(employees, size);
        EmployeeByName[] byName = new EmployeeByName[size];
        for (int i = 0; i < size; i++) {
            byName[i] = new EmployeeByName(employees[i]);
        }

        System.out.println();
        System.out.println("The performance of our sorting algorithms");
        System.out.println("###########################################");

        long startSelection = System.currentTimeMillis();
        SelectionSort.sort(bySalary, size);
        long endSelection = System.currentTimeMillis();
        System.out.println("Selection Sort Time -> "
                + (endSelection - startSelection) + " ms");

        long startQuick = System.currentTimeMillis();
        QuickSort.sort(byName, 0, size - 1);
        long endQuick = System.currentTimeMillis();
        System.out.println("Quick Sort Time -> "
                + (endQuick - startQuick) + " ms");

        System.out.println("###########################################");
        System.out.println();

        try {
            FileHandler.writeEmployees(
                    "sortedemployeeBySalary.csv",
                    bySalary, size);
            System.out.println("Write employee data sorted by their hourly salaries into file <--> sortedemployeeBySalary.csv");

            Employee[] byNameEmployees = new Employee[size];
            for (int i = 0; i < size; i++) {
                byNameEmployees[i] = byName[i].getEmployee();
            }

            FileHandler.writeEmployees(
                    "sortedemployeeByName.csv",
                    byNameEmployees, size);
            System.out.println("Write employee data sorted by their names into file <--> sortedemployeeByName.csv");
        } catch (IOException e) {
            System.out.println("File writing error: " + e.getMessage());
        }

        System.out.print("Enter the name of the employee to search <--> ");
        String target = scanner.nextLine();

        int index = BinarySearch.search(byName, size, new EmployeeByName(target));

        if (index == -1)
            System.out.println("Employee not found.");
        else
            System.out.println("Employee found at index <--> " + index);
    }
}
