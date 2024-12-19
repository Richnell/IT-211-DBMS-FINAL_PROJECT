import java.util.Scanner;

public class MentalHealthTracker {
    private final MoodTracker moodTracker;

    public MentalHealthTracker() {
        this.moodTracker = new MoodTracker(); // Initialize MoodTracker
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Advanced Mental Health Tracker App");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option (1-3): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1 -> register(scanner);
                case 2 -> login(scanner);
                case 3 -> {
                    System.out.println("Exiting the Mental Health Tracker. Take care!");
                    exit = true;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = Database.registerUser(username, password);  // Corrected here
        if (success) {
            System.out.println("Registration successful! You can now log in.");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = Database.loginUser(username, password);  // Corrected here
        if (success) {
            System.out.println("Login successful! Welcome back, " + username + "!");
            moodTracking(scanner, username);
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private void moodTracking(Scanner scanner, String username) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Log a mood");
            System.out.println("2. View mood history");
            System.out.println("3. Generate mood report");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> moodTracker.logEntry(username);
                case 2 -> moodTracker.viewHistory(username);
                case 3 -> moodTracker.generateReport(username);
                case 4 -> {
                    System.out.println("Exiting the Mental Health Tracker. Take care!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
