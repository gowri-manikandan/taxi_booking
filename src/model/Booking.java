package model;

public class Booking
{
    private final int BOOKING_ID;
    private final int CUSTOMER_ID;
    private final char PICKUP_POINT;
    private final char DROP_POINT;
    private final int PICKUP_TIME;
    private final int DROP_TIME;
    private final double COST;

    public Booking(int customerID, int bookingID, char pickupPoint, char dropPoint, int pickupTime,int dropTime,double cost) {
        CUSTOMER_ID = customerID;
        BOOKING_ID = bookingID;
        PICKUP_POINT = pickupPoint;
        DROP_POINT = dropPoint;
        PICKUP_TIME = pickupTime;
        DROP_TIME = dropTime;
        COST = cost;
    }

    public int getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public char getPICKUP_POINT() {
        return PICKUP_POINT;
    }

    public char getDROP_POINT() {
        return DROP_POINT;
    }

    public int getPICKUP_TIME() {
        return PICKUP_TIME;
    }

    public int getBookingID() {
        return BOOKING_ID;
    }

    public double getCOST() {
        return COST;
    }

    public int getDROP_TIME() {
        return DROP_TIME;
    }
}
