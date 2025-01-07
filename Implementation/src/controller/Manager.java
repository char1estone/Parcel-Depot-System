package controller;

import model.Log;
import model.ParcelMap;
import model.QueueOfCustomers;
import model.Worker;
import view.DepotSystemViewer;

public class Manager {
	public static void main(String[] args) {
        // Step 1: Initialize Core Components
        // - QueueOfCustomers: Manages the customer queue.
        // - ParcelMap: Manages parcels stored in the depot.
        // - Log: Singleton instance to log system events.
        // - Worker: Responsible for processing customers and parcels.
        QueueOfCustomers queue = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();
        Log log = Log.getInstance(); // Singleton instance for logging
        Worker worker = new Worker(queue, parcelMap, log);

        // Step 2: Load Data from CSV Files
        // Load parcel data into the ParcelMap
        System.out.println("Loading parcel data from 'Parcels.csv'...");
        parcelMap.readParcelsFromCSV("Parcels.csv");

        // Load customer data into the QueueOfCustomers
        System.out.println("Loading customer data from 'Custs.csv'...");
        queue.readCustomersFromCSV("Custs.csv");

        // Step 3: Initialize the GUI (View)
        // - DepotView: Provides the graphical interface for displaying parcel, customer, and processing information.
        System.out.println("Initializing Depot GUI...");
        DepotSystemViewer view = new DepotSystemViewer();

        // Step 4: Set Up the Controller
        // - DepotController: Connects the View, Model (QueueOfCustomers and ParcelMap), and Worker.
        System.out.println("Connecting Controller to the View and Model...");
        DepotWorker controller = new DepotWorker(view, queue, parcelMap, worker);

        // Step 5: Application is Running
        // - At this point, the GUI is displayed, and the user can interact with the system.
        System.out.println("Depot Parcel Processing System is now running.");
    }
}

