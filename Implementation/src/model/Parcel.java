package model;

public class Parcel {
    private String parcelId;   // Unique ID for the parcel
    private int daysInDepot;   // Number of days the parcel has been in the depot
    private double weight;     // Weight of the parcel
    private double length;     // Length dimension of the parcel
    private double width;      // Width dimension of the parcel
    private double height;     // Height dimension of the parcel

    // Constructor
    public Parcel(String parcelId, int daysInDepot, double weight, double length, double width, double height) {
        this.parcelId = parcelId;
        this.daysInDepot = daysInDepot;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // Getters and Setters
    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Override toString for easier display
    @Override
    public String toString() {
        return String.format("Parcel ID: %s, Days in Depot: %d, Weight: %.2f, Dimensions: %.2f x %.2f x %.2f", parcelId, daysInDepot, weight, length, width, height);
    }
}
