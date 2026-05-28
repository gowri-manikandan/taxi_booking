package repository;

import model.Booking;
import model.Taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DB
{
    private final List<Taxi> TAXI_LIST;
    TreeMap<Integer,List<Booking>> bookingList;
    static DB db;
    public DB()
    {
        TAXI_LIST = new ArrayList<>();
        bookingList = new TreeMap<>();
    }
    public static DB getInstance()
    {
        if(db==null)
        {
            db = new DB();
        }
        return db;
    }
    public void addNewTaxi(Taxi newTaxi)
    {
        TAXI_LIST.add(newTaxi);
    }

    public List<Taxi> getTAXI_LIST()
    {
        return TAXI_LIST;
    }

    public void addNewBooking(int taxiID,Booking booking)
    {
        bookingList.computeIfAbsent(taxiID, k -> new ArrayList<>()).add(booking);
    }

    public TreeMap<Integer, List<Booking>> getBookingList() {
        return bookingList;
    }
}
