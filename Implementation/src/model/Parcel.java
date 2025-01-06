package model;

public class Parcel {
    private String parcelID;   // Unique ID for the parcel
    private String dimensions;    // Dimensions of the parcel
    private int daysInDepot;   // Number of days the parcel has been in the depot
    private double weight;     // Weight of the parcel
    private String status;

    // Constructor
    public Parcel(String parcelID, String dimensions, float weight, int daysInDepot, String status) {
        this.parcelID = parcelID;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    // Getters
    public String getParcelID() {
        return parcelID;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }
    
    public void updateStatus(String status) {
        this.status = status;
    }
    
    public void displayParcelInfo() {
        System.out.println(this); // Calls the overridden toString method for easy display
    }
    
    // Override toString for easier display
    @Override
    public String toString() {
        return "Parcel ID: " + parcelID +
                ",\n Dimensions: " + dimensions +
                ",\n Weight: " + weight + "kg" +
                ",\n Days in Depot: " + daysInDepot +
                ",\n Status: " + status;
    }
}
