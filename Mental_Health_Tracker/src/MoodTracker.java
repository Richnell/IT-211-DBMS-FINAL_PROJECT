import java.util.*;  // For HashMap, Scanner, Random
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class MoodTracker extends Tracker {
    private static final Map<String, String[]> moodTips = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    static {
        // Initialize mood tips
        moodTips.put("Happy", new String[]{
            "Keep doing what makes you happy!",
            "Share your happiness with someone else.",
            "Laugh, it’s contagious!",
            "Celebrate the little victories in life."
        });

        moodTips.put("Sad", new String[]{
            "Talk to a friend.",
            "Write down your feelings.",
            "Remember that it's okay to feel down sometimes.",
            "Try to focus on something that makes you feel better."
        });

        moodTips.put("Stressed", new String[]{
            "Take deep breaths.",
            "Organize your tasks.",
            "Break your tasks into smaller steps.",
            "Give yourself a break and come back to it later."
        });

        moodTips.put("Anxious", new String[]{
            "Focus on your breathing.",
            "Write down your worries.",
            "Try grounding exercises (5 things you can see, 4 things you can touch, etc.).",
            "Remember that you are not alone in this."
        });

        moodTips.put("Angry", new String[]{
            "Calm yourself with a walk.",
            "Talk to someone you trust.",
            "Take a deep breath and count to 10.",
            "Channel your energy into something productive."
        });

        moodTips.put("Grateful", new String[]{
            "Write down three things you're grateful for today.",
            "Share your gratitude with someone else.",
            "Take a moment to reflect on your blessings.",
            "Carry this feeling with you throughout the day."
        });

        moodTips.put("Excited", new String[]{
            "Share your excitement with someone you trust.",
            "Take action towards what excites you.",
            "Use your energy to work on something creative.",
            "Celebrate your excitement in a positive way."
        });

        moodTips.put("Lonely", new String[]{
            "Reach out to someone you care about.",
            "Engage in a hobby you love.",
            "Remember that loneliness is a temporary feeling.",
            "Consider joining a community or group with similar interests."
        });

        moodTips.put("Confident", new String[]{
            "Use your confidence to help others around you.",
            "Take on a challenge that excites you.",
            "Celebrate your strengths and achievements.",
            "Keep pushing forward with your positive mindset."
        });

        moodTips.put("Hopeful", new String[]{
            "Visualize your goals and keep moving forward.",
            "Share your hope with someone else.",
            "Remind yourself that good things are coming.",
            "Stay patient and keep a positive mindset."
        });
    }

    public MoodTracker() {
        super("moods.txt");  // Initialize file for logging mood history
    }

    @Override
    public void logEntry(String username) {
        System.out.print("How are you feeling today? (Happy, Sad, Stressed, Anxious, Angry, Grateful, Excited, Lonely, Confident, Hopeful): ");
        String mood = capitalizeMood(scanner.nextLine().trim());

        if (!moodTips.containsKey(mood)) {
            System.out.println("Mood not recognized. Please try again.");
            return;
        }

        // Use java.util.Date to get the current date
        java.util.Date utilDate = new java.util.Date();  
        // Convert java.util.Date to java.sql.Date for MySQL
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        // Use the logMood method to insert mood log into the database
        boolean logged = Database.logMood(username, mood, sqlDate);  // Log mood to MySQL
        if (logged) {
            System.out.println("Mood logged successfully.");
        } else {
            System.out.println("Failed to log mood.");
        }

        // Provide a random tip for the logged mood
        String[] tips = moodTips.get(mood);
        String randomTip = tips[new Random().nextInt(tips.length)];
        System.out.println("Here's a tip for when you're feeling " + mood + ": " + randomTip);
    }

    @Override
    public void viewHistory(String username) {
        String query = "SELECT log_date, mood FROM MoodLogs WHERE username = ? ORDER BY log_date DESC";
    
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
    
            boolean found = false;
            while (rs.next()) {
                java.sql.Date logDate = rs.getDate("log_date");
                String mood = rs.getString("mood");
                System.out.println(logDate.toString() + " - " + username + ": " + mood);
                found = true;
            }
    
            if (!found) {
                System.out.println("No mood history found for user.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching mood history: " + e.getMessage());
        }
    }
    

    @Override
    public void generateReport(String username) {
        Map<String, Integer> moodCounts = new HashMap<>();
        AtomicInteger total = new AtomicInteger(0);  // Using AtomicInteger for total
        
        // Query to fetch mood counts for the user from the database
        String query = "SELECT mood, COUNT(*) AS count FROM MoodLogs WHERE username = ? GROUP BY mood";
    
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
    
            // Populate the moodCounts map and calculate the total
            while (rs.next()) {
                String mood = rs.getString("mood");
                int count = rs.getInt("count");
                moodCounts.put(mood, count);
                total.addAndGet(count);  // Update total using AtomicInteger
            }
    
            // Generate the report
            if (total.get() > 0) {
                System.out.println("\nMood Report for " + username + ":");
                moodCounts.forEach((mood, count) -> {
                    System.out.printf("%s: %.2f%%\n", mood, (count * 100.0) / total.get());  // Access total using get()
                });
            } else {
                System.out.println("No data available to generate a report.");
            }
        } catch (SQLException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
    
    

    // Helper method to capitalize the first letter of the mood
    private String capitalizeMood(String mood) {
        if (mood == null || mood.isEmpty()) return mood;
        return mood.substring(0, 1).toUpperCase() + mood.substring(1).toLowerCase();
    }
}
