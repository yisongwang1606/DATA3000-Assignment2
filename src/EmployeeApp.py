from dataclasses import dataclass
import time

@dataclass
class Employee:# Initial the employee class with the given attributes
    id: int
    name: str
    hours_worked: float
    hourly_rate: float
    deduction_province: float
    deduction_federal: float
    education_allowance: float

    def calc_hourly_salary(self) -> float:# Calculate the hourly salary after deductions and allowances
        gross = self.hours_worked * self.hourly_rate
        prov = self.deduction_province / 100.0 if self.deduction_province > 1 else self.deduction_province
        fed = self.deduction_federal / 100.0 if self.deduction_federal > 1 else self.deduction_federal
        deductions = gross * prov + gross * fed
        return gross - deductions + self.education_allowance

    def to_csv(self) -> str: # Convert the employee data to a CSV string format for file writing
        return (
            f"{self.id},{self.name},{self.hours_worked:.2f},{self.hourly_rate:.2f},{self.deduction_province:.2f},{self.deduction_federal:.2f},{self.education_allowance:.2f}"
        )

def selection_sort(data, key): #Sort a list in place using selection sort with a key function.
    n = len(data)
    for i in range(n - 1):
        min_index = i
        for j in range(i + 1, n):
            if key(data[j]) < key(data[min_index]):
                min_index = j
        if min_index != i:
            data[i], data[min_index] = data[min_index], data[i]

def quick_sort(data, key): # Return a new list sorted using quick sort with a key function.
    if len(data) <= 1:
        return data
    pivot = key(data[len(data) // 2])
    left = [x for x in data if key(x) < pivot]
    middle = [x for x in data if key(x) == pivot]
    right = [x for x in data if key(x) > pivot]
    return quick_sort(left, key) + middle + quick_sort(right, key)

def binary_search(data, target, key, left, right): #Recursively search for target in sorted data, return index
    if left > right:
        return -1
    mid = (left + right) // 2
    mid_val = key(data[mid]).lower()
    target_val = target.lower()
    if mid_val == target_val:
        if mid == 0 or key(data[mid - 1]).lower() != target_val:
            return mid
        return binary_search(data, target, key, left, mid - 1)
    if target_val < mid_val:
        return binary_search(data, target, key, left, mid - 1)
    return binary_search(data, target, key, mid + 1, right)

def read_employees(file_path):#Read and return Employee objects from the given file path.
    employees = []
    try:
        with open(file_path, "r", encoding="utf-8") as file:
            for line in file:
                line = line.strip()
                if not line:
                    continue
                delimiter = "◄►" if "◄►" in line else ","
                parts = line.split(delimiter)
                if len(parts) < 7:
                    continue
                try:
                    emp = Employee(
                        int(parts[0].strip()),
                        parts[1].strip(),
                        float(parts[2].strip()),
                        float(parts[3].strip()),
                        float(parts[4].strip()),
                        float(parts[5].strip()),
                        float(parts[6].strip()),
                    )
                except ValueError:
                    continue
                employees.append(emp)
    except FileNotFoundError:
        print(f"File not found: {file_path}")
    except OSError as e:
        print("File reading error:", e)
    return employees

def write_to_file(file_path, employees):#Write the list of Employee objects to a file in CSV format.
    try:
        with open(file_path, "w", encoding="utf-8") as file:
            for emp in employees:
                file.write(emp.to_csv() + "\n")
    except FileNotFoundError:
        print(f"Cannot open file for writing: {file_path}")
    except OSError as e:
        print("File writing error:", e)

def main():# Main function to execute the employee data processing, sorting, searching, execution time compare.
    file_path = input("Enter full path of employee data file: ").strip().replace('"', "")
    if not file_path:
        file_path = "employeesWithoutRepeat.txt"
        print(f"No path entered. Using default file: {file_path}")

    employees = read_employees(file_path)
    if not employees:
        print("No employees loaded. Check input file format/path.")
        return

    employees_salary = employees.copy()
    employees_name = employees.copy()

    start = time.time()
    selection_sort(employees_salary, key=lambda emp: emp.calc_hourly_salary())
    end = time.time()
    selection_time_ms = round((end - start) * 1000)
    print("1. =========Selection Sort (by salary) Results:=========")
    for emp in employees_salary:#employees_salary[:30]
        print(f"{emp.id}◄►{emp.name}◄►{emp.hours_worked:.2f}◄►{emp.hourly_rate:.2f}◄►{emp.deduction_province:.2f}◄►{emp.deduction_federal:.2f}◄►{emp.education_allowance:.2f}") #◄►{emp.calc_hourly_salary():.2f}
    print("Selection Sort Time ->", selection_time_ms, "ms")

    start = time.time()
    employees_name = quick_sort(employees_name, key=lambda emp: emp.name.lower())
    end = time.time()
    quick_time_ms = round((end - start) * 1000)
    print("2. =========Quick Sort (by name) Results:=========")
    for emp in employees_name:#employees_name[:300]
        print(f"{emp.id}◄►{emp.name}◄►{emp.hours_worked:.2f}◄►{emp.hourly_rate:.2f}◄►{emp.deduction_province:.2f}◄►{emp.deduction_federal:.2f}◄►{emp.education_allowance:.2f}") 
    print("Quick Sort Time ->", quick_time_ms, "ms")

    print(f"3 . =========Time Comparison (ms)=========\n: Selection -> {selection_time_ms} ms, Quick -> {quick_time_ms} ms")

    write_to_file("sortedemployeeBySalary.csv", employees_salary)
    write_to_file("sortedemployeeByName.csv", employees_name)

    name = input("\n Enter name to search: ").strip()
    if name:
        index = binary_search(employees_name, name, key=lambda emp: emp.name, left=0, right=len(employees_name) - 1)
        if index != -1:
            print("4. ========= Search Result:=========\n Employee found at index ->", index)
            print("Employee info ->", employees_name[index].to_csv())
        else:
            print("4. Search Result: Employee not found")
    else:
        print("4. Search Result: No name entered. Search skipped.")

if __name__ == "__main__":
    main()