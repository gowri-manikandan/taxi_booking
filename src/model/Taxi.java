package model;


public class Taxi
{
    private final int TAXI_ID;
    private double totalEarning;
    private char currentPoint;
    private int availableAt;

    public Taxi(int taxiID) {
        this.TAXI_ID = taxiID;
        this.totalEarning = 0.0d;
        currentPoint = 'A';
        availableAt = 0;
    }

    public int getTAXI_ID() {
        return TAXI_ID;
    }
    public double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
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
