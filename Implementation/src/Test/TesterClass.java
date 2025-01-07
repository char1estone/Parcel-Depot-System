package Test;

import java.util.Collection;
import java.util.Queue;

import model.Customer;
import model.Log;
import model.Parcel;
import model.ParcelMap;
import model.QueueOfCustomers;
import model.Worker;

public class TesterClass {
    public static void main(String[] args) {
        System.out.println("=== Customer Class Testing ===");

        // Test case 1: Valid Customer Creation
        try {
            Customer customer1 = new Customer("C123", "Alice", "P456");
            System.out.println("Test 1 Passed: " + customer1);
        } catch (Exception e) {
            System.out.println("Test 1 Failed: " + e.getMessage());
        }

        // Test case 2: Null Attributes
        try {
            Customer invalidCustomer = new Customer(null, "Bob", "P789");
            System.out.println("Test 2 Failed: Customer should not allow null attributes.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 2 Passed: " + e.getMessage());
        }

        // Test case 3: Empty Strings
        try {
            Customer emptyCustomer = new Customer(" ", " ", " ");
            System.out.println("Test 3 Passed: " + emptyCustomer);
        } catch (Exception e) {
            System.out.println("Test 3 Failed: " + e.getMessage());
        }

        // Test case 4: Test Getters
        try {
            Customer customer2 = new Customer("C124", "Charlie", "P457");
            if (customer2.getCustomerID().equals("C124") && customer2.getName().equals("Charlie")
                    && customer2.getParcelID().equals("P457")) {
                System.out.println("Test 4 Passed: Getters work correctly.");
            } else {
                System.out.println("Test 4 Failed: Getters return incorrect values.");
            }
        } catch (Exception e) {
            System.out.println("Test 4 Failed: " + e.getMessage());
        }

        // Test case 5: Test Setters
        try {
            Customer customer3 = new Customer("C125", "Diana", "P458");
            customer3.setName("Diane");
            customer3.setParcelID("P459");
            if (customer3.getName().equals("Diane") && customer3.getParcelID().equals("P459")) {
                System.out.println("Test 5 Passed: Setters work correctly.");
            } else {
                System.out.println("Test 5 Failed: Setters did not update values correctly.");
            }
        } catch (Exception e) {
            System.out.println("Test 5 Failed: " + e.getMessage());
        }

        // Test case 6: toString() Output
        try {
            Customer customer4 = new Customer("C126", "Eve", "P460");
            String expectedOutput = "Customer ID: C126, Name: Eve, Parcel ID: P460";
            if (customer4.toString().equals(expectedOutput)) {
                System.out.println("Test 6 Passed: toString() produces correct output.");
            } else {
                System.out.println("Test 6 Failed: toString() output is incorrect.");
            }
        } catch (Exception e) {
            System.out.println("Test 6 Failed: " + e.getMessage());
        }

        // Test case 7: Test Trim Functionality
        try {
            Customer customer5 = new Customer("  C127  ", "  Frank  ", "  P461  ");
            if (customer5.getCustomerID().equals("C127") && customer5.getName().equals("Frank")
                    && customer5.getParcelID().equals("P461")) {
                System.out.println("Test 7 Passed: Constructor trims input values correctly.");
            } else {
                System.out.println("Test 7 Failed: Constructor did not trim input values.");
            }
        } catch (Exception e) {
            System.out.println("Test 7 Failed: " + e.getMessage());
        }
        
        Customer customer1 = new Customer("C001", "Thomas Moss", "X123");
        Customer customer2 = new Customer("C002", "Katy Moss", "X456");
        customer1.toString();       // Display customer info
        customer1.setName("Tom Updated"); // Test setName
        customer1.setParcelID("X123");    // Test setParcelID
        System.out.println("Updated Customer: " + customer1.getName() + ", Parcel ID: " + customer1.getParcelID());
        
        
        System.out.println("=== Log Class Testing ===");
        // Test 1: Singleton Instance Creation
        try {
            Log log1 = Log.getInstance();
            Log log2 = Log.getInstance();
            if (log1 == log2) {
                System.out.println("Test 1 Passed: Singleton instance works correctly.");
            } else {
                System.out.println("Test 1 Failed: Singleton instance does not work correctly.");
            }
        } catch (Exception e) {
            System.out.println("Test 1 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 2: Logging an Event
        try {
            Log log = Log.getInstance();
            log.logEvent("Test Event 1");
            System.out.println("Test 2 Passed: Event logged successfully.");
        } catch (Exception e) {
            System.out.println("Test 2 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 3: Writing Log to a File
        try {
            Log log = Log.getInstance();
            log.logEvent("Test Event 2");
            log.writeLogToFile("test_log_output.txt");
            System.out.println("Test 3 Passed: Log written to file successfully.");
        } catch (Exception e) {
            System.out.println("Test 3 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 4: Handling File Write Errors
        try {
            Log log = Log.getInstance();
            // Use an invalid filename to trigger an IOException
            log.writeLogToFile("/invalid_path/test_log_output.txt");
            System.out.println("Test 4 Failed: Expected an error, but none occurred.");
        } catch (Exception e) {
            System.out.println("Test 4 Passed: Exception handled correctly - " + e.getMessage());
        }

        // Test 5: Logging Multiple Events
        try {
            Log log = Log.getInstance();
            log.logEvent("Test Event 3");
            log.logEvent("Test Event 4");
            System.out.println("Test 5 Passed: Multiple events logged successfully.");
        } catch (Exception e) {
            System.out.println("Test 5 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 6: Verify Logs Are Appended to File
        try {
            Log log = Log.getInstance();
            log.logEvent("Test Event 5");
            log.logEvent("Test Event 6");
            log.writeLogToFile("test_log_output.txt");
            System.out.println("Test 6 Passed: Logs appended to file successfully.");
        } catch (Exception e) {
            System.out.println("Test 6 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 7: Verify Console Output for Logged Events
        try {
            Log log = Log.getInstance();
            log.logEvent("This event should appear in the console.");
            System.out.println("Test 7 Passed: Console output works as expected.");
        } catch (Exception e) {
            System.out.println("Test 7 Failed: Exception occurred - " + e.getMessage());
        }
        
        
        System.out.println("=== Parcel Class Testing ===");

        // Test 1: Create a Parcel and check attributes
        try {
            Parcel parcel = new Parcel("P123", "30x20x15", 5.5f, 10, "In Transit");
            if (parcel.getParcelID().equals("P123") &&
                parcel.getDimensions().equals("30x20x15") &&
                parcel.getWeight() == 5.5 &&
                parcel.getDaysInDepot() == 10 &&
                parcel.getStatus().equals("In Transit")) {
                System.out.println("Test 1 Passed: Parcel attributes correctly initialized.");
            } else {
                System.out.println("Test 1 Failed: Parcel attributes not correctly initialized.");
            }
        } catch (Exception e) {
            System.out.println("Test 1 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 2: Update Parcel Status
        try {
            Parcel parcel = new Parcel("P124", "40x25x20", 7.0f, 5, "Pending");
            parcel.updateStatus("Delivered");
            if (parcel.getStatus().equals("Delivered")) {
                System.out.println("Test 2 Passed: Parcel status updated successfully.");
            } else {
                System.out.println("Test 2 Failed: Parcel status not updated correctly.");
            }
        } catch (Exception e) {
            System.out.println("Test 2 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 3: Display Parcel Information
        try {
            Parcel parcel = new Parcel("P125", "25x15x10", 2.5f, 3, "Ready for Pickup");
            System.out.println("Test 3: Displaying Parcel Information...");
            parcel.displayParcelInfo(); // Should print parcel details to console
            System.out.println("Test 3 Completed: Verify console output for correctness.");
        } catch (Exception e) {
            System.out.println("Test 3 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 4: Edge Case - Parcel with Zero Weight
        try {
            Parcel parcel = new Parcel("P126", "10x10x10", 0.0f, 1, "New");
            if (parcel.getWeight() == 0.0) {
                System.out.println("Test 4 Passed: Parcel with zero weight handled correctly.");
            } else {
                System.out.println("Test 4 Failed: Parcel with zero weight not handled correctly.");
            }
        } catch (Exception e) {
            System.out.println("Test 4 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 5: Edge Case - Negative Days in Depot
        try {
            Parcel parcel = new Parcel("P127", "20x15x10", 3.0f, -5, "New");
            if (parcel.getDaysInDepot() == -5) {
                System.out.println("Test 5 Passed: Parcel with negative days in depot handled correctly (though this might need validation).");
            } else {
                System.out.println("Test 5 Failed: Parcel with negative days in depot not handled correctly.");
            }
        } catch (Exception e) {
            System.out.println("Test 5 Failed: Exception occurred - " + e.getMessage());
        }
        
        
        System.out.println("=== ParcelMap Class Testing ===");

        // Test 1: Add a Parcel and Verify
        try {
            ParcelMap parcelMap = new ParcelMap();
            Parcel parcel = new Parcel("P001", "30x20x10", 5.0f, 3, "In Transit");
            parcelMap.addParcel(parcel);

            Parcel retrievedParcel = parcelMap.findParcelById("P001");
            if (retrievedParcel != null && retrievedParcel.getParcelID().equals("P001")) {
                System.out.println("Test 1 Passed: Parcel added and retrieved successfully.");
            } else {
                System.out.println("Test 1 Failed: Parcel could not be retrieved.");
            }
        } catch (Exception e) {
            System.out.println("Test 1 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 2: Retrieve Non-Existent Parcel
        try {
            ParcelMap parcelMap = new ParcelMap();
            Parcel retrievedParcel = parcelMap.findParcelById("P999");
            if (retrievedParcel == null) {
                System.out.println("Test 2 Passed: Non-existent parcel retrieval correctly returned null.");
            } else {
                System.out.println("Test 2 Failed: Retrieved a non-existent parcel.");
            }
        } catch (Exception e) {
            System.out.println("Test 2 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 3: Display All Parcels
        try {
            ParcelMap parcelMap = new ParcelMap();
            parcelMap.addParcel(new Parcel("P002", "25x15x10", 3.0f, 2, "Ready for Pickup"));
            parcelMap.addParcel(new Parcel("P003", "40x30x20", 8.0f, 1, "Delivered"));

            System.out.println("Test 3: Displaying all parcels...");
            parcelMap.displayAllParcels(); // Check console output for correctness
            System.out.println("Test 3 Completed: Verify console output.");
        } catch (Exception e) {
            System.out.println("Test 3 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 4: Read Parcels from CSV File
        try {
            ParcelMap parcelMap = new ParcelMap();
            String testCSVFile = "Parcels.csv"; // Make sure to have this file prepared
            parcelMap.readParcelsFromCSV(testCSVFile);

            Collection<Parcel> parcels = parcelMap.getAllParcels();
            if (!parcels.isEmpty()) {
                System.out.println("Test 4 Passed: Parcels successfully loaded from CSV file.");
            } else {
                System.out.println("Test 4 Failed: No parcels were loaded from the CSV file.");
            }
        } catch (Exception e) {
            System.out.println("Test 4 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 5: Edge Case - Empty ParcelMap
        try {
            ParcelMap parcelMap = new ParcelMap();
            Collection<Parcel> parcels = parcelMap.getAllParcels();
            if (parcels.isEmpty()) {
                System.out.println("Test 5 Passed: Empty ParcelMap handled correctly.");
            } else {
                System.out.println("Test 5 Failed: Non-empty parcels in a new ParcelMap.");
            }
        } catch (Exception e) {
            System.out.println("Test 5 Failed: Exception occurred - " + e.getMessage());
        }
        
        
        System.out.println("=== QueueOfCustomers Class Testing ===");

        // Test 1: Enqueue and Display Customers
        try {
            QueueOfCustomers queue = new QueueOfCustomers();
            queue.enqueue(new Customer("C001", "Tom", "X123"));
            queue.enqueue(new Customer("C002", "Katy", "X456"));

            System.out.println("Test 1: Displaying queue after enqueuing customers...");
            queue.displayQueue(); // Check console output for correctness
            System.out.println("Test 1 Completed: Verify console output.");
        } catch (Exception e) {
            System.out.println("Test 1 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 2: Dequeue Customer
        try {
            QueueOfCustomers queue = new QueueOfCustomers();
            queue.enqueue(new Customer("C003", "Charlie", "P003"));

            System.out.println("Test 2: Dequeuing customer...");
            Customer dequeuedCustomer = queue.dequeue();
            if (dequeuedCustomer != null && dequeuedCustomer.getCustomerID().equals("C003")) {
                System.out.println("Test 2 Passed: Correct customer dequeued.");
            } else {
                System.out.println("Test 2 Failed: Incorrect customer dequeued or queue is empty.");
            }
        } catch (Exception e) {
            System.out.println("Test 2 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 3: Dequeue from Empty Queue
        try {
            QueueOfCustomers queue = new QueueOfCustomers();

            System.out.println("Test 3: Attempting to dequeue from an empty queue...");
            Customer dequeuedCustomer = queue.dequeue();
            if (dequeuedCustomer == null) {
                System.out.println("Test 3 Passed: Correctly handled empty queue.");
            } else {
                System.out.println("Test 3 Failed: Dequeue returned a customer from an empty queue.");
            }
        } catch (Exception e) {
            System.out.println("Test 3 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 4: Read Customers from CSV
        try {
            QueueOfCustomers queue = new QueueOfCustomers();
            String testCSVFile = "Custs.csv"; // Ensure this file is available with valid data
            queue.readCustomersFromCSV(testCSVFile);

            Queue<Customer> customers = queue.getQueue();
            if (!customers.isEmpty()) {
                System.out.println("Test 4 Passed: Customers successfully loaded from CSV.");
            } else {
                System.out.println("Test 4 Failed: No customers were loaded from CSV.");
            }
        } catch (Exception e) {
            System.out.println("Test 4 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 5: Display Empty Queue
        try {
            QueueOfCustomers queue = new QueueOfCustomers();

            System.out.println("Test 5: Displaying an empty queue...");
            queue.displayQueue(); // Check console output
            System.out.println("Test 5 Completed: Verify console output.");
        } catch (Exception e) {
            System.out.println("Test 5 Failed: Exception occurred - " + e.getMessage());
        }
        
        System.out.println("=== Worker Class Testing ===");

        // Setup: Create instances of required classes
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();
        Log log = Log.getInstance(); // Singleton log instance
        Worker worker = new Worker(queueOfCustomers, parcelMap, log);

        // Test 1: Process Next Customer when Queue is Empty
        try {
            System.out.println("Test 1: Process next customer with an empty queue...");
            worker.processNextCustomer();
            String logMessage = "Check the log for: 'No customers in queue to process.'";
            System.out.println("Test 1 Completed: " + logMessage);
        } catch (Exception e) {
            System.out.println("Test 1 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 2: Process Customer with Valid Parcel
        try {
            System.out.println("Test 2: Process customer with a valid parcel...");
            Customer customer3 = new Customer("C001", "Alice", "P001");
            queueOfCustomers.enqueue(customer3);

            Parcel parcel1 = new Parcel("P001", "10x20x30", 5.5f, 3, "In Depot");
            parcelMap.addParcel(parcel1);

            worker.processNextCustomer();

            String currentParcelInfo = worker.getCurrentParcelInfo();
            System.out.println("Current Parcel Info: " + currentParcelInfo);
            System.out.println("Test 2 Completed: Check log for successful processing.");
        } catch (Exception e) {
            System.out.println("Test 2 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 3: Process Customer with Missing Parcel
        try {
            System.out.println("Test 3: Process customer with a missing parcel...");
            Customer customer4 = new Customer("C002", "Bob", "P002");
            queueOfCustomers.enqueue(customer4);

            worker.processNextCustomer();

            String currentParcelInfo = worker.getCurrentParcelInfo();
            System.out.println("Current Parcel Info: " + currentParcelInfo);
            System.out.println("Test 3 Completed: Check log for missing parcel handling.");
        } catch (Exception e) {
            System.out.println("Test 3 Failed: Exception occurred - " + e.getMessage());
        }

        // Test 4: Verify Fee Calculation and Parcel Status Update
        try {
            System.out.println("Test 4: Verify fee calculation and status update...");
            Customer customer3 = new Customer("C003", "Charlie", "P003");
            queueOfCustomers.enqueue(customer3);

            Parcel parcel3 = new Parcel("P003", "15x25x35", 7.0f, 5, "In Depot");
            parcelMap.addParcel(parcel3);

            worker.processNextCustomer();

            Parcel updatedParcel = parcelMap.findParcelById("P003");
            if ("Collected".equals(updatedParcel.getStatus())) {
                System.out.println("Test 4 Passed: Parcel status updated to 'Collected'.");
            } else {
                System.out.println("Test 4 Failed: Parcel status not updated correctly.");
            }
        } catch (Exception e) {
            System.out.println("Test 4 Failed: Exception occurred - " + e.getMessage());
        }
    }
}
    
    

