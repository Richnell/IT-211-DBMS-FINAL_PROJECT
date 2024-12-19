import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/MHDB";
    private static final String USER = "root";
    private static final String PASSWORD = "april182005";
    public static Connection connection;
    public static Connection getConnection() {
        return connection;
    }

    static {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            initializeDatabase();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }

    private static void initializeDatabase() {
        createUsersTable();
        createMoodLogsTable();
        createTipsTable();
    }

    private static void createUsersTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS users (
                    username VARCHAR(50) PRIMARY KEY,
                    password VARCHAR(100) NOT NULL
                );
                """;
        executeTableCreation(query, "users");
    }

    private static void createMoodLogsTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS MoodLogs (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL,
                    mood VARCHAR(50) NOT NULL,
                    log_date DATE NOT NULL,
                    FOREIGN KEY (username) REFERENCES users(username)
                );
                """;
        executeTableCreation(query, "MoodLogs");
    }

    private static void createTipsTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS Tips (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    mood VARCHAR(50) NOT NULL,
                    tip TEXT NOT NULL
                );
                """;
        executeTableCreation(query, "Tips");
    }

    private static void executeTableCreation(String query, String tableName) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            System.out.println("Table '" + tableName + "' is ready.");
        } catch (SQLException e) {
            System.out.println("Failed to create table '" + tableName + "': " + e.getMessage());
        }
    }

    public static boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password").equals(password);
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
            return false;
        }
    }

    public static boolean logMood(String username, String mood, java.sql.Date logDate) {
        String query = "INSERT INTO MoodLogs (username, mood, log_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, mood);
            stmt.setDate(3, logDate);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Failed to log mood: " + e.getMessage());
            return false;
        }
    }

    public static void generateMoodReport(String username) {
        String query = "SELECT mood, COUNT(mood) AS mood_count FROM MoodLogs WHERE username = ? GROUP BY mood";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            int totalMoods = 0;
            Map<String, Integer> moodCounts = new HashMap<>();

            while (rs.next()) {
                String mood = rs.getString("mood");
                int count = rs.getInt("mood_count");
                moodCounts.put(mood, count);
                totalMoods += count;
            }

            if (totalMoods > 0) {
                System.out.println("\nMood Report for " + username + ":");
                for (Map.Entry<String, Integer> entry : moodCounts.entrySet()) {
                    String mood = entry.getKey();
                    int count = entry.getValue();
                    double percentage = (count * 100.0) / totalMoods;
                    System.out.printf("%s: %.2f%%\n", mood, percentage);
                }
            } else {
                System.out.println("No data available to generate a report.");
            }

        } catch (SQLException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}
