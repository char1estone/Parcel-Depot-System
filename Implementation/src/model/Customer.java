package model;

public class Customer {
    // Attributes
    private String name; // Customer name
    private String parcelId; // Parcel ID associated with the customer

    // Constructor
    public Customer(String name, String parcelId) {
        this.name = name;
        this.parcelId = parcelId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getParcelId() {
        return parcelId;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    // Override toString
    @Override
    public String toString() {
        return "Customer [Name: " + name + ", Parcel ID: " + parcelId + "]";
    }

    // Test main method
    public static void main(String[] args) {
        // Create a test customer
        Customer customer = new Customer("John Doe", "X123");

        // Test getters
        System.out.println("Name: " + customer.getName());
        System.out.println("Parcel ID: " + customer.getParcelId());

        // Test toString
        System.out.println(customer);

        // Test setters
        customer.setName("Jane Doe");
        customer.setParcelId("X456");
        System.out.println("Updated Customer: " + customer);
    }
}
