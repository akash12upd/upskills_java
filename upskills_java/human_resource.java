import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private String name;
    private int id;
    private int totalLeaves;
    private int leavesTaken;

    public Employee(String name, int id, int totalLeaves) {
        this.name = name;
        this.id = id;
        this.totalLeaves = totalLeaves;
        this.leavesTaken = 0;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public int getLeavesTaken() {
        return leavesTaken;
    }

    public void takeLeave(int days) {
        if (days > 0 && days <= (totalLeaves - leavesTaken)) {
            leavesTaken += days;
            System.out.println("Leave granted for " + days + " days.");
        } else {
            System.out.println("Leave request cannot be fulfilled.");
        }
    }
}

class HRMS {
    private List<Employee> employees;

    public HRMS() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee.getName());
    }

    public void removeEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                System.out.println("Employee removed: " + employee.getName());
                return;
            }
        }
        System.out.println("Employee not found with ID: " + id);
    }

    public void displayEmployees() {
        System.out.println("Employee List:");
        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName());
        }
    }

    public void takeLeave(int id, int days) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.takeLeave(days);
                return;
            }
        }
        System.out.println("Employee not found with ID: " + id);
    }
}

public class human_resource {
    public static void main(String[] args) {
        HRMS hrms = new HRMS();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("HRMS - Human Resource Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Display Employees");
            System.out.println("4. Take Leave");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter employee name: ");
                    String name = scanner.next();
                    System.out.print("Enter employee ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter total leaves: ");
                    int totalLeaves = scanner.nextInt();
                    Employee employee = new Employee(name, id, totalLeaves);
                    hrms.addEmployee(employee);
                    break;

                case 2:
                    System.out.print("Enter employee ID: ");
                    int removeId = scanner.nextInt();
                    hrms.removeEmployee(removeId);
                    break;

                case 3:
                    hrms.displayEmployees();
                    break;

                case 4:
                    System.out.print("Enter employee ID: ");
                    int empId = scanner.nextInt();
                    System.out.print("Enter number of days for leave: ");
                    int leaveDays = scanner.nextInt();
                    hrms.takeLeave(empId, leaveDays);
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }

        scanner.close();
        System.out.println("HRMS - Exiting");
    }
}

