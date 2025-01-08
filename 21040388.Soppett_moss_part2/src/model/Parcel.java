package model;

public class Parcel {
    private String parcelID;  
    private String dimensions;    
    private int daysInDepot;   
    private double weight;     
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
    
    public String getDimensions() {
        return dimensions;
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
    
 // Method to calculate the collection fee
    private double calculateFee() {
        double fee = weight * 0.5 + daysInDepot * 0.2;
        return Math.round(fee * 100.0) / 100.0;
    }
    
    // Override toString for easier display
    @Override
    public String toString() {
        return "Parcel ID: " + parcelID +
                ",\n Dimensions: " + dimensions +
                ",\n Weight: " + weight + "kg" +
                ",\n Days in Depot: " + daysInDepot +
                ",\n Status: " + status +
                ",\n Collection Fee: £" + calculateFee();
    }
}
