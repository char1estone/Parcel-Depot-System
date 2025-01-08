package model;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static volatile Log instance;
    private StringBuffer logEntries; 

    private Log() {
        logEntries = new StringBuffer();
    }

    public static Log getInstance() {
        if (instance == null) {
            synchronized (Log.class) {
                if (instance == null) {
                    instance = new Log();
                }
            }
        }
        return instance;
    }

    public synchronized void logEvent(String event) {
        String timestampedEvent = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " - " + event;

        logEntries.append(timestampedEvent).append("\n");
        System.out.println(timestampedEvent);
        writeToLogFile("DepotLog.txt", timestampedEvent);
    }

    //adds single log entry to specific log file
    private synchronized void writeToLogFile(String filename, String entry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(entry);
            writer.newLine();
        } catch (IOException e) {
            String errorLog = "Error writing to log file: " + e.getMessage();
            System.err.println(errorLog);
            logEntries.append(errorLog).append("\n");
        }
    }

    //writes entire content of in-memory log to file
    public synchronized void writeLogToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(logEntries.toString());
        } catch (IOException e) {
            String errorLog = "Error writing full log to file: " + e.getMessage();
            System.err.println(errorLog);
            logEntries.append(errorLog).append("\n");
        }
    }
}
 
