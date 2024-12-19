import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class User {
    private static final String USER_FILE = "users.txt";
    private static final Map<String, String> users = new HashMap<>();

    public static boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, password);
        saveUsersToFile();
        return true;
    }

    public static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public static void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
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
