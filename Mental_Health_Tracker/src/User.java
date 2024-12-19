import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class User {
    private static final String USER_FILE = "users.txt";
    private static final Map<String, String> users = new HashMap<>();

    // Method to register a new user
    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, password);
        saveUsersToFile();
        return true;
    }

    // Method to log in an existing user
    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Method to load users from a file
    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]); // Load username and password into the map
                }
            }
            System.out.println("User data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing user data found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading user data.");
            e.printStackTrace();
        }
    }

    // Private helper method to save users to a file
    private static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user data.");
            e.printStackTrace();
        }
    }
}
