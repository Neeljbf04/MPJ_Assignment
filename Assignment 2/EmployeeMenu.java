import java.util.Scanner;

class Employee {

    protected double salary;

    public Employee(double salary) {
        this.salary = salary;
    }

    public void displaySalary() {
        System.out.println("Salary before hike: " + salary);
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(double salary) {
        super(salary);
    }

    public void calculateSalary() {
        salary = salary + (salary * 0.50);
        System.out.println("Salary after 50% hike: " + salary);
    }
}

class InternEmployee extends Employee {

    public InternEmployee(double salary) {
        super(salary);
    }

    public void calculateSalary() {
        salary = salary + (salary * 0.25);
        System.out.println("Salary after 25% hike: " + salary);
    }
}

public class EmployeeMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Salary Menu ---");
            System.out.println("1. Full Time Employee");
            System.out.println("2. Intern Employee");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Full Time Employee Salary: ");
                    double fullSalary = sc.nextDouble();

                    FullTimeEmployee fte = new FullTimeEmployee(fullSalary);
                    fte.displaySalary();
                    fte.calculateSalary();
                    break;

                case 2:
                    System.out.print("Enter Intern Employee Salary: ");
                    double internSalary = sc.nextDouble();

                    InternEmployee intern = new InternEmployee(internSalary);
                    intern.displaySalary();
                    intern.calculateSalary();
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}