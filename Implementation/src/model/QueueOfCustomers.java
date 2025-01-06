package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
	private Queue<Customer> customerQueue;
	
	
	public QueueOfCustomers() {
        customerQueue = new LinkedList<>();
    }
	
	public void enqueue(Customer customer) {
        customerQueue.add(customer);
    }
	
	public void readCustomersFromCSV(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Ensure the line contains the required data fields
                if (values.length >= 3) {
                    String customerID = values[0].trim();
                    String name = values[1].trim();
                    String parcelID = values[2].trim();

                    // Create a Customer object and add it to the queue
                    Customer customer = new Customer(customerID, name, parcelID);
                    enqueue(customer);
                }
            }
            System.out.println("Customers successfully loaded from CSV file.");
        } catch (IOException e) {
            System.err.println("Error reading customers CSV file: " + e.getMessage());
        }
    }
	
	public Customer dequeue() {
        Customer customer = customerQueue.poll(); // Removes and returns the head of the queue
        if (customer != null) {
            System.out.println("Customer " + customer.getName() + " removed from the queue.");
        } else {
            System.out.println("Queue is empty.");
        }
        return customer;
    }
	
	/**
     * Retrieves the current queue of customers.
     *
     * @return The queue of Customer objects as a Queue.
     */
    public Queue<Customer> getQueue() {
        return customerQueue;
    }
    
    /**
     * Displays the current customer queue.
     * Each customer's name and associated parcel ID are printed to the console.
     * This method is primarily for testing and debugging purposes.
     */
    public void displayQueue() {
        System.out.println("Current Customer Queue:");
        for (Customer customer : customerQueue) {
            System.out.println(" - " + customer.getName() + " (Parcel ID: " + customer.getParcelID() + ")");
        }
    }
}
	
	
