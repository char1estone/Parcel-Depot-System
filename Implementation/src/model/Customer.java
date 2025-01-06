package model;

public class Customer {
    // Attributes
	private final String customerID;
    private String name; // Customer name
    private String parcelID; // Parcel ID associated with the customer

    // Constructor
    public Customer(String customerID, String name, String parcelID) {
        if (customerID == null || name == null || parcelID == null) {
            throw new IllegalArgumentException("Customer attributes cannot be null.");
        }
        this.customerID = customerID.trim();
        this.name = name.trim();
        this.parcelID = parcelID.trim();
    }

    //Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getParcelID() {
        return parcelID;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + ", Name: " + name + ", Parcel ID: " + parcelID;
    }
}
