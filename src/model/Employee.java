package model;

public class Employee implements Comparable<Employee> {

    private int id;
    private String name;
    private double hoursWorked;
    private double hourlyRate;
    private double deductionProvince;
    private double deductionFederal;
    private double educationAllowance;

    public Employee(int id, String name, double hoursWorked,
                    double hourlyRate, double deductionProvince,
                    double deductionFederal, double educationAllowance) {

        this.id = id;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.deductionProvince = deductionProvince;
        this.deductionFederal = deductionFederal;
        this.educationAllowance = educationAllowance;
    }

    public String getName() {
        return name;
    }

    public double calcHourlySalary() {

        double gross = hoursWorked * hourlyRate;

        double provRate = (deductionProvince > 1) ? (deductionProvince / 100.0) : deductionProvince;
        double fedRate  = (deductionFederal  > 1) ? (deductionFederal  / 100.0) : deductionFederal;

        double deductions = gross * provRate + gross * fedRate;

        return gross - deductions + educationAllowance;
    }

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.calcHourlySalary(), other.calcHourlySalary());
    }

    @Override
    public String toString() {
        return id + "," + name + "," + hoursWorked + "," +
                hourlyRate + "," + deductionProvince + "," +
                deductionFederal + "," + educationAllowance;
    }
}