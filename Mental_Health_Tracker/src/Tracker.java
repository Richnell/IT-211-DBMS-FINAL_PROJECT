import java.io.*;

public abstract class Tracker {
    protected final String fileName;

    protected Tracker(String fileName) {
        this.fileName = fileName;
        createFileIfNotExists();
    }

    public abstract void logEntry(String username);

    public abstract void viewHistory(String username);

    public abstract void generateReport(String username);

    protected void saveToFile(String entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving entry to file.");
            e.printStackTrace();
        }
    }

    private void createFileIfNotExists() {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating the file.");
            e.printStackTrace();
        }
    }
}
