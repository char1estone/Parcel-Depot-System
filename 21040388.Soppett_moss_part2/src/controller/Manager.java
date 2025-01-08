package controller;

import model.Log;
import model.ParcelMap;
import model.QueueOfCustomers;
import model.Worker;
import view.DepotSystemViewer;

public class Manager {
	public static void main(String[] args) {
        //Initialise core classes
        QueueOfCustomers queue = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();
        Log log = Log.getInstance(); //Singleton instance for logging
        Worker worker = new Worker(queue, parcelMap, log);

        //Loads data from CSV files
        System.out.println("Loading parcels from 'Parcels.csv'.");
        parcelMap.readParcelsFromCSV("Parcels.csv");

        //Loads customer data into the QueueOfCustomers class
        System.out.println("Loading customers from 'Custs.csv'.");
        queue.readCustomersFromCSV("Custs.csv");

        //Start the GUI load up view from class DepotSystemViewer
        DepotSystemViewer view = new DepotSystemViewer();

        //Connects the classes in the model package to the DepotWorker class in the controller package
        DepotWorker controller = new DepotWorker(view, queue, parcelMap, worker);
        System.out.println("Parcel Depot System has started running.");
    }
}

