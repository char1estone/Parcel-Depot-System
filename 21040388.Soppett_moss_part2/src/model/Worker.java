package model;

public class Worker {
	
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;
    private Log log;
    private Parcel currentParcel;
    
    public Worker(QueueOfCustomers customerQueue, ParcelMap parcelMap, Log log) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
        this.log = log;
    }
    
    public void processNextCustomer() {
        //Retrieve the next customer from the queue
        Customer customer = customerQueue.dequeue();

        //No customers in queue
        if (customer == null) {
            log.logEvent("No customers in queue to process.");
            currentParcel = null;
            return;
        }

        //Retrieve the parcel with a certain ID
        currentParcel = parcelMap.findParcelById(customer.getParcelID());

        if (currentParcel == null) {
            String notFoundMessage = "Parcel not found for customer " + customer.getName();
            log.logEvent(notFoundMessage);
            return;
        }
        
        double fee = calculateFee(currentParcel);

        currentParcel.updateStatus("Collected");

        //Log the details of the processed transaction
        String message = "Processed customer " + customer.getName() +
                " with Parcel ID: " + currentParcel.getParcelID() +
                ". Collection fee: £" + fee;
        log.logEvent(message);
    }
    
    public String getCurrentParcelInfo() {
        if (currentParcel == null) {
            return "No parcel being processed";
        }
        return currentParcel.toString(); //Uses the Parcel class's overridden toString method
    }
    
    public Log getLog() {
        return log;
    }
    
    public double calculateFee(Parcel parcel) {
        double fee = parcel.getWeight() * 0.5 + parcel.getDaysInDepot() * 0.2;
        return Math.round(fee * 100.0) / 100.0;
    }
}