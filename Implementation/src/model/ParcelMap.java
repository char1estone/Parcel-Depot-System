package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParcelMap {
	
	private Map<String, Parcel> parcelMap;
	
	public ParcelMap() {
        parcelMap = new HashMap<>();
    }
	
	public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelID(), parcel);
    }
	
	public void readParcelsFromCSV(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                //Ensures the line in the csv file contains all required data fields
                if (values.length >= 6) {
                    String parcelID = values[0].replaceAll("[^\\x20-\\x7E]", "").trim();
                    String dimensions = values[1].trim() + "x" + values[2].trim() + "x" + values[3].trim();     // New version
                    float weight = Float.parseFloat(values[4].trim());
                    int daysInDepot = Integer.parseInt(values[5].trim());
                    String status = values[6].trim();

                    //Creates a Parcel object and add it to the map
                    Parcel parcel = new Parcel(parcelID, dimensions, weight, daysInDepot, status);
                    addParcel(parcel);
                }
            }
            System.out.println("Parcels successfully loaded from CSV file.");
        } catch (IOException e) {
            System.err.println("Error reading parcels CSV file: " + e.getMessage());
        }
    }
	
	public Parcel findParcelById(String parcelID) {
        return parcelMap.get(parcelID);
    }
	
	public Collection<Parcel> getAllParcels() {
        return parcelMap.values();
    }
	
	public void displayAllParcels() {
        System.out.println("All Parcels in the Depot:");
        for (Parcel parcel : parcelMap.values()) {
            parcel.displayParcelInfo();
        }
    }

}
