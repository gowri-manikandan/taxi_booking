package repository;

import model.Booking;
import model.Taxi;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DB
{
    private final List<Taxi> taxiList ;
    TreeMap<Integer,List<Booking>> bookingList;
    public DB(List<Taxi> taxiList, TreeMap<Integer, List<Booking>> bookingList)
    {
        this.taxiList = taxiList;
        this.bookingList = bookingList;
    }

    public void addNewTaxi(Taxi newTaxi)
    {
        taxiList.add(newTaxi);
    }

    public List<Taxi> getTaxiList()
    {
        return taxiList;
    }

    public void addNewBooking(int taxiID,Booking booking)
    {
        bookingList.computeIfAbsent(taxiID, k -> new ArrayList<>()).add(booking);
    }

    public TreeMap<Integer, List<Booking>> getBookingList() {
        return bookingList;
    }
}
