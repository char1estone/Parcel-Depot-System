package controller;

import model.ParcelMap;
import model.QueueOfCustomers;
import model.Worker;
import view.DepotSystemViewer;
import model.Customer;
import model.Parcel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepotWorker {
    private final DepotSystemViewer view; // GUI component
    private final QueueOfCustomers queue; // Manages the customer queue
    private final ParcelMap parcelMap; // Manages parcels in the depot
    private final Worker worker; // Handles processing logic
    private final String customerCSVPath = "Custs.csv"; // Holds the customer csv file
    private final String parcelCSVPath = "Parcels.csv"; // Holds the parcels csv file

    // Constructor for DepotWorker
    public DepotWorker(DepotSystemViewer view, QueueOfCustomers queue, ParcelMap parcelMap, Worker worker) {
        this.view = view;
        this.queue = queue;
        this.parcelMap = parcelMap;
        this.worker = worker;

        // handle button actions in the GUI
        view.getProcessNextButton().addActionListener(new ProcessNextListener());
        view.getReloadDataButton().addActionListener(new ReloadDataListener()); 

        // ActionListeners for the new buttons
        view.getAddParcelButton().addActionListener(new AddParcelListener());
        view.getEditParcelButton().addActionListener(new EditParcelListener());
        view.getAddCustomerButton().addActionListener(new AddCustomerListener());
        view.getEditCustomerButton().addActionListener(new EditCustomerListener());

        // show the current state
        refreshDisplay();
    }
    
    private class ProcessNextListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            worker.processNextCustomer(); 
            refreshDisplay();            
        }
    }
    
    // Reloads data from CSV files
    private class ReloadDataListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Clears existing data
            queue.getQueue().clear();
            parcelMap.getAllParcels().clear();

            // Reload data from CSV files
            parcelMap.readParcelsFromCSV(parcelCSVPath);
            queue.readCustomersFromCSV(customerCSVPath);
            worker.getLog().logEvent("Data reloaded from Customer and Parcel CSV files.");

            // Refresh the display
            refreshDisplay();
        }
    }
    
    private void refreshDisplay() {
        view.updateParcelList(getParcelList()); // Updates parcel list in the GUI
        view.updateCustomerQueue(getCustomerQueue()); // Updates customer queue in the GUI
        view.updateCurrentParcel(getCurrentParcelInfo()); // Updates current parcel info in the GUI
    }
    
    //Returns a string containing parcel IDs and their statuses.
    private String getParcelList() {
        StringBuilder sb = new StringBuilder();
        for (Parcel parcel : parcelMap.getAllParcels()) {
            sb.append("ID: ").append(parcel.getParcelID())
                    .append(", Status: ").append(parcel.getStatus()).append("\n");
        }
        return sb.toString();
    }
    
    private String getCustomerQueue() {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : queue.getQueue()) {
            sb.append("Customer: ").append(customer.getName());
                    sb.append(", Customer ID: ").append(customer.getCustomerID()); 
                    sb.append(", Parcel ID: ").append(customer.getParcelID()).append("\n");
        }
        return sb.toString();
    }
    
    private String getCurrentParcelInfo() {
        return "Currently Processing: " + worker.getCurrentParcelInfo();
    }

    
    // AddCustomerListener to handle adding a new customer
    private class AddCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String customerID = JOptionPane.showInputDialog("Enter Customer ID:");
            String name = JOptionPane.showInputDialog("Enter Customer Name:");
            String parcelID = JOptionPane.showInputDialog("Enter Parcel ID:");

            Customer newCustomer = new Customer(customerID, name, parcelID);
            queue.enqueue(newCustomer);
            refreshDisplay();
            worker.getLog().logEvent("Added new customer: " + customerID);
        }
    }

    // EditCustomerListener to handle editing an existing customer
    private class EditCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String customerID = JOptionPane.showInputDialog("Enter Customer ID to edit:");
            for (Customer customer : queue.getQueue()) {
                if (customer.getCustomerID().equals(customerID)) {
                    String newName = JOptionPane.showInputDialog("Enter new Name:", customer.getName());
                    String newParcelID = JOptionPane.showInputDialog("Enter new Parcel ID:", customer.getParcelID());

                    customer.setName(newName);
                    customer.setParcelID(newParcelID);
                    refreshDisplay();
                    worker.getLog().logEvent("Edited customer: " + customerID);
                    return;
                }
            }
            JOptionPane.showMessageDialog(view, "Customer not found.");
        }
    }
    
    // AddParcelListener to handle adding a new parcel
    private class AddParcelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String parcelID = JOptionPane.showInputDialog("Enter Parcel ID:");
            String dimensions = JOptionPane.showInputDialog("Enter Dimensions (e.g., 10x10x10):");
            float weight = Float.parseFloat(JOptionPane.showInputDialog("Enter Weight (kg):"));
            int daysInDepot = Integer.parseInt(JOptionPane.showInputDialog("Enter Days in Depot:"));
            String status = JOptionPane.showInputDialog("Enter Status (Waiting/Collected):");

            Parcel newParcel = new Parcel(parcelID, dimensions, weight, daysInDepot, status);
            parcelMap.addParcel(newParcel);
            refreshDisplay();
            worker.getLog().logEvent("Added new parcel: " + parcelID);
        }
    }
    
    // EditParcelListener to handle editing an existing parcel
    private class EditParcelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String parcelID = JOptionPane.showInputDialog("Enter Parcel ID to change:");
            Parcel parcel = parcelMap.findParcelById(parcelID);

            if (parcel != null) {
                String newDimensions = JOptionPane.showInputDialog("Enter new Dimensions:", parcel.getDimensions());
                float newWeight = Float.parseFloat(JOptionPane.showInputDialog("Enter new Weight (kg):", parcel.getWeight()));
                int newDaysInDepot = Integer.parseInt(JOptionPane.showInputDialog("Enter new Days in Depot:", parcel.getDaysInDepot()));
                String newStatus = JOptionPane.showInputDialog("Enter new Status (Waiting/Collected):", parcel.getStatus());

                parcel.updateStatus(newStatus);
                refreshDisplay();
                worker.getLog().logEvent("Edited parcel: " + parcelID);
            } else {
                JOptionPane.showMessageDialog(view, "Parcel not found.");
            }
        }
    }
}