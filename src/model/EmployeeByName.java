package model;

public class EmployeeByName implements Comparable<EmployeeByName> {

    private final Employee employee;
    private final String name;

    public EmployeeByName(Employee employee) {
        this.employee = employee;
        this.name = employee.getName();
    }

    public EmployeeByName(String name) {
        this.employee = null;
        this.name = name;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public int compareTo(EmployeeByName other) {
        return this.name.compareToIgnoreCase(other.name);
    }
}
