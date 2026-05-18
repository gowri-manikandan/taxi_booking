package model;

public class Booking
{
    private final int bookingID;
    private final int customerID;
    private final char pickupPoint;
    private final char dropPoint;
    private final int pickupTime;
    private final int dropTime;
    private final double cost;

    public Booking(int customerID, int bookingID, char pickupPoint, char dropPoint, int pickupTime,int dropTime,double cost) {
        this.customerID = customerID;
        this.bookingID = bookingID;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.cost = cost;
    }

    public int getCustomerID() {
        return customerID;
    }

    public char getPickupPoint() {
        return pickupPoint;
    }

    public char getDropPoint() {
        return dropPoint;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public int getBookingID() {
        return bookingID;
    }

    public double getCost() {
        return cost;
    }

    public int getDropTime() {
        return dropTime;
    }
}
