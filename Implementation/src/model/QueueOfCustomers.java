package model;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {

    private Queue<Customer> customerQueue;

    public QueueOfCustomers() {
        customerQueue = new LinkedList<>();
    }

    // Method to load customers from CSV file
    public void loadCustomersFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();  // Trim any extra spaces

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Split each line into parts (CSV format: Name,Parcel ID)
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String parcelId = parts[1].trim();

                    // Create a Customer object and add it to the queue
                    addCustomer(new Customer(name, parcelId));
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    /**
     * Adds a new customer to the queue.
     *
     * @param customer The Customer object to add.
     */
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        System.out.println("Added customer: " + customer);
    }

    /**
     * Removes and returns the next customer in the queue.
     *
     * @return The next Customer, or null if the queue is empty.
     */
    public Customer processNextCustomer() {
        Customer nextCustomer = customerQueue.poll();
        if (nextCustomer != null) {
            System.out.println("Processed customer: " + nextCustomer);
        } else {
            System.out.println("No customers in the queue to process.");
        }
        return nextCustomer;
    }

    /**
     * Peeks at the next customer without removing them.
     *
     * @return The next Customer, or null if the queue is empty.
     */
    public Customer peekNextCustomer() {
        Customer nextCustomer = customerQueue.peek();
        if (nextCustomer != null) {
            System.out.println("Next customer in line: " + nextCustomer);
        } else {
            System.out.println("No customers in the queue.");
        }
        return nextCustomer;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    /**
     * Displays all customers currently in the queue.
     */
    public void displayQueue() {
        if (customerQueue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else {
            System.out.println("Current Queue:");
            for (Customer customer : customerQueue) {
                System.out.println(customer);
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        QueueOfCustomers queue = new QueueOfCustomers();

        // Path to the CSV file in the resources folder or main directory
        String csvFilePath = "Custs.csv";

        // Load customers from CSV
        queue.loadCustomersFromCSV(csvFilePath);

        // Display the queue
        queue.displayQueue();

        // Add a new customer
        queue.addCustomer(new Customer("Alice Wonderland", "PARCEL101"));

        // Peek at the next customer
        queue.peekNextCustomer();

        // Process a customer
        queue.processNextCustomer();

        // Display the updated queue
        queue.displayQueue();

        // Check if the queue is empty
        System.out.println("Is the queue empty? " + queue.isEmpty());
    }
}

	
