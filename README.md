# Employee Data Sorting and Searching Program

## Requirements
- Java 17+ (or Java 11+)
- Windows PowerShell or terminal

## Project Structure
- `src/main/EmployeeApp.java`: program entry point
- `src/model/Employee.java`: employee model and salary calculation
- `src/sort/SelectionSort.java`: selection sort by salary
- `src/sort/QuickSort.java`: quick sort by employee name
- `src/search/BinarySearch.java`: recursive binary search by name
- `src/util/FileHandler.java`: file read/write helpers

## How to Compile
From the project root:

```powershell
javac -d bin src/main/*.java src/model/*.java src/sort/*.java src/search/*.java src/util/*.java
```

## How to Run
From the project root:

```powershell
java -cp bin main.EmployeeApp
```

When prompted, enter the full path to one of the input files, for example:
- `C:\Users\yisongwang\Desktop\test\employeesWithoutRepeat.txt`
- `C:\Users\yisongwang\Desktop\test\employeesWithRepeat.txt`

Then enter a name to search, for example: `Liz`.

## Output Files
After sorting, the program writes:
- `sortedemployeeBySalary.csv`
- `sortedemployeeByName.csv`
