package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance; // Singleton instance of the Log class
    private StringBuilder logEntries; // Stores log entries in memory
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initialises a StringBuilder to store log entries.
     */
    private Log() {
        logEntries = new StringBuilder();
    }
    
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }
    
    public void logEvent(String event) {
        // Create a time stamped entry
        String timestampedEvent = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + event;

        // Append to in-memory log
        logEntries.append(timestampedEvent).append("\n");

        // Print the event to the terminal
        System.out.println(timestampedEvent);

        // Write the event to the log file
        writeToLogFile("depot_log7.txt", timestampedEvent);
    }
    
    private void writeToLogFile(String filename, String entry) {
        // Attempt to open the file and write the entry
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(entry); // Write the entry
            writer.newLine();    // Add a newline for separation
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    
    public void writeLogToFile(String filename) {
        // Attempt to open the file and write all log entries
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(logEntries.toString());
        } catch (IOException e) {
            System.err.println("Error writing log to file: " + e.getMessage());
        }
    }
}    
