package model;

import java.util.ArrayList;
import java.util.List;

public class Taxi
{
    private final int taxiID;
    private double totalEarning;
    private char currentPoint;
    private int availableAt;
    private List<Booking> bookingList;

    public Taxi(int taxiID, double totalEarning) {
        this.taxiID = taxiID;
        this.totalEarning = totalEarning;
        currentPoint = 'A';
        availableAt = 0;
        this.bookingList = new ArrayList<>();
    }

    public int getTaxiID() {
        return taxiID;
    }
    public double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(Booking booking) {
        bookingList.add(booking);
    }

    public char getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(char currentPoint) {
        this.currentPoint = currentPoint;
    }

    public int getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(int availableAt) {
        this.availableAt = availableAt;
    }
}
