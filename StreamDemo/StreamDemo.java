import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String gender;
    private double salary;
    private String designation;
    private String department;

    public Employee(String name, int age, String gender, double salary, String designation, String department) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.designation = designation;
        this.department = department;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getSalary() { return salary; }
    public String getDesignation() { return designation; }
    public String getDepartment() { return department; }
    public void setSalary(double salary) {this.salary=salary;}

    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, gender='%s', salary=%.2f, desig='%s', dept='%s'}",
                name, age, gender, salary, designation, department);
    }
}


public class StreamDemo
{
    public static void main(String args[])
    {
        List<Employee> employees=new ArrayList<>();
        employees.add(new Employee("Arjun", 28, "Male", 75000, "Developer", "IT"));
        employees.add(new Employee("Sriya", 24, "Female", 60000, "Developer", "IT"));
        employees.add(new Employee("Rahul", 35, "Male", 95000, "Manager", "IT"));
        employees.add(new Employee("Priya", 30, "Female", 80000, "Analyst", "Finance"));
        employees.add(new Employee("Amit", 40, "Male", 120000, "Manager", "Finance"));
        employees.add(new Employee("Anjali", 26, "Female", 55000, "HR Executive", "HR"));
        employees.add(new Employee("Varun", 32, "Male", 70000, "Sales Lead", "Sales"));
        employees.add(new Employee("Neha", 29, "Female", 65000, "Developer", "IT"));
        employees.add(new Employee("Vikram", 45, "Male", 150000, "Director", "IT"));
        employees.add(new Employee("Kavita", 31, "Female", 72000, "Analyst", "Finance"));
        employees.add(new Employee("Rohan", 27, "Male", 58000, "Developer", "IT"));
        employees.add(new Employee("Sneha", 23, "Female", 50000, "Intern", "HR"));
        employees.add(new Employee("Manish", 38, "Male", 88000, "Manager", "Sales"));
        employees.add(new Employee("Pooja", 34, "Female", 82000, "Manager", "HR"));
        employees.add(new Employee("Aakash", 25, "Male", 62000, "Developer", "IT"));
        employees.add(new Employee("Ishita", 29, "Female", 68000, "Sales Associate", "Sales"));
        employees.add(new Employee("Suresh", 50, "Male", 110000, "Senior Manager", "Finance"));
        employees.add(new Employee("Meera", 27, "Female", 64000, "Analyst", "IT"));
        employees.add(new Employee("Kabir", 33, "Male", 77000, "Developer", "IT"));
        employees.add(new Employee("Ritu", 36, "Female", 90000, "Manager", "Finance"));
        employees.add(new Employee("Deepak", 42, "Male", 130000, "Director", "Sales"));
        employees.add(new Employee("Tara", 22, "Female", 45000, "Intern", "IT"));
        employees.add(new Employee("Karan", 30, "Male", 67000, "HR Executive", "HR"));
        employees.add(new Employee("Zoya", 31, "Female", 74000, "Developer", "IT"));
        employees.add(new Employee("Abhi", 29, "Male", 61000, "Sales Associate", "Sales"));


        Optional<Employee> highest_salary=employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        highest_salary.ifPresent(emp->System.out.println("Highest Paid Employee:"+emp.getName()));

        Predicate<Employee> p1=emp->"Male".equals(emp.getGender());
        long male_employees_count=employees.stream().filter(p1).count();
        long female_employees_count=employees.stream().filter(p1.negate()).count();

        System.out.println("Male employees:"+male_employees_count);
        System.out.println("Female employees:"+female_employees_count);



        Map<String, Double> totalExpenseByDept = employees.stream().collect(Collectors.groupingBy(
        Employee::getDepartment, 
        Collectors.summingDouble(Employee::getSalary)
    ));

    totalExpenseByDept.forEach((dept, total) -> 
        System.out.println("Department: " + dept + " | Total Expense: " + total));
    
    List<Employee> senior=employees.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).limit(5).collect(Collectors.toList());
    System.out.println("Senior");
    senior.forEach((emp->System.out.println(emp.getName())));

    Predicate<Employee> p2=emp->"Manager".equals(emp.getDesignation());

    List<Employee> only_manager=employees.stream().filter(p1).collect(Collectors.toList());
    only_manager.forEach((emp->System.out.println(emp.getName())));
    employees.stream().filter(p2.negate()).forEach(emp->{
        double old_salary=emp.getSalary();
        emp.setSalary(old_salary*1.2);
    });
    
    employees.forEach(System.out::println);
    long total_count=employees.stream().count();






    }
}