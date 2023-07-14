import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class expense_tracker {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, List<Expense>> expenseMap = new HashMap<>();
    private static final Map<String, String> categoryMap = new HashMap<>();

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        int choice;
        do {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Add Category");
            System.out.println("4. View Categories");
            System.out.println("5. Generate Reports");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    addCategory();
                    break;
                case 4:
                    viewCategories();
                    break;
                case 5:
                    generateReports();
                    break;
                case 0:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void addExpense() {
        System.out.println("Add Expense:");
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(date, amount, category, description);
        List<Expense> expenses = expenseMap.getOrDefault(category, new ArrayList<>());
        expenses.add(expense);
        expenseMap.put(category, expenses);

        System.out.println("Expense added successfully.");
    }

    private static void viewExpenses() {
        System.out.println("View Expenses:");
        System.out.println("1. View All Expenses");
        System.out.println("2. Filter Expenses");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            System.out.println("All Expenses:");
            for (List<Expense> expenses : expenseMap.values()) {
                for (Expense expense : expenses) {
                    System.out.println(expense);
                }
            }
        } else if (choice == 2) {
            System.out.println("Filter Expenses:");
            System.out.println("1. Filter by Date Range");
            System.out.println("2. Filter by Category");
            System.out.print("Enter your choice: ");
            int filterChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (filterChoice == 1) {
                System.out.print("Enter start date (YYYY-MM-DD): ");
                String startDateStr = scanner.nextLine();
                LocalDate startDate = LocalDate.parse(startDateStr, DATE_FORMATTER);

                System.out.print("Enter end date (YYYY-MM-DD): ");
                String endDateStr = scanner.nextLine();
                LocalDate endDate = LocalDate.parse(endDateStr, DATE_FORMATTER);

                System.out.println("Filtered Expenses (Date Range):");
                for (List<Expense> expenses : expenseMap.values()) {
                    for (Expense expense : expenses) {
                        if (expense.getDate().isAfter(startDate) && expense.getDate().isBefore(endDate)) {
                            System.out.println(expense);
                        }
                    }
                }
            } else if (filterChoice == 2) {
                System.out.print("Enter category: ");
                String category = scanner.nextLine();

                List<Expense> expenses = expenseMap.getOrDefault(category, new ArrayList<>());
                System.out.println("Filtered Expenses (Category: " + category + "):");
                for (Expense expense : expenses) {
                    System.out.println(expense);
                }
            } else {
                System.out.println("Invalid choice. Returning to the main menu.");
            }
        } else {
            System.out.println("Invalid choice. Returning to the main menu.");
        }
    }

    private static void addCategory() {
        System.out.println("Add Category:");
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        categoryMap.put(categoryName, categoryName);
        expenseMap.put(categoryName, new ArrayList<>());

        System.out.println("Category added successfully.");
    }

    private static void viewCategories() {
        System.out.println("View Categories:");
        for (String category : categoryMap.keySet()) {
            System.out.println(category);
        }
    }

    private static void generateReports() {
        System.out.println("Generate Reports:");
        System.out.println("1. Monthly Expense Report");
        System.out.println("2. Category-wise Expense Report");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (choice == 1) {
            System.out.print("Enter year (YYYY): ");
            String yearStr = scanner.nextLine();
            int year = Integer.parseInt(yearStr);

            for (int month = 1; month <= 12; month++) {
                double totalExpense = 0;
                for (List<Expense> expenses : expenseMap.values()) {
                    for (Expense expense : expenses) {
                        if (expense.getDate().getYear() == year && expense.getDate().getMonthValue() == month) {
                            totalExpense += expense.getAmount();
                        }
                    }
                }
                System.out.println("Month: " + month + ", Year: " + year + ", Total Expense: $" + totalExpense);
            }
        } else if (choice == 2) {
            System.out.println("Category-wise Expense Report:");
            for (Map.Entry<String, List<Expense>> entry : expenseMap.entrySet()) {
                double totalExpense = 0;
                for (Expense expense : entry.getValue()) {
                    totalExpense += expense.getAmount();
                }
                System.out.println("Category: " + entry.getKey() + ", Total Expense: $" + totalExpense);
            }
        } else {
            System.out.println("Invalid choice. Returning to the main menu.");
        }
    }
}

class Expense {
    private LocalDate date;
    private double amount;
    private String category;
    private String description;

    public Expense(LocalDate date, double amount, String category, String description) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
 

