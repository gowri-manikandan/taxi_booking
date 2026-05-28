package service;

import repository.DB;
import model.Booking;
import model.Taxi;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BookingService
{
    int bookingID = 1;
    DB DB;
    View view;
    public BookingService(View view)
    {
        DB = repository.DB.getInstance();
        this.view = view;
    }

    // get the basic data name of the company and number of taxis.


    // Initialize the taxis.
    public void initializeTaxi(int numberOfTaxi)
    {
        for(int i=1;i<=numberOfTaxi;i++)
        {
            DB.addNewTaxi(new Taxi(i));
        }
    }

    // the booking information.
    public void getBookingInfo()
    {
        int customerID = view.getPositiveNUmber("Customer ID:");
        char pickupPoint = view.getPickupORDropPoint("Pickup Point:");
        char dropPoint = view.getPickupORDropPoint("Drop Point:");
        if (pickupPoint == dropPoint)
        {
            view.displayError("The pickup Point and Drop point must not be same.");
            return;
        }
        int pickupTime = view.getTime("Pickup Time:");
        checkAvailabilities(customerID,pickupPoint,dropPoint,pickupTime);
    }

    // Check for the available taxis.
    private void checkAvailabilities(int customerID,char pickupPoint,char dropPoint,int pickupTime)
    {
        List<Taxi> taxiList = DB.getTAXI_LIST();
        List<Taxi> availableTaxi = new ArrayList<>();

        // Check the taxis in pickup point
        for(Taxi taxi: taxiList)
        {
            int canArriveAt = taxi.getAvailableAt();
            if(taxi.getCurrentPoint()==pickupPoint && canArriveAt-pickupTime<=0)
            {
                availableTaxi.add(taxi);
            }
        }
        if(!availableTaxi.isEmpty())
        {
            selectTaxi(customerID,pickupPoint,dropPoint,pickupTime,availableTaxi);
        }
        else
        {
            //  Check for the nearest Taxis
            int minDistance = Integer.MAX_VALUE;
            for(Taxi taxi: taxiList)
            {
                int distance = Math.abs(taxi.getCurrentPoint()-pickupPoint);
                int canArriveAt = distance+taxi.getAvailableAt();
                if(minDistance > distance && canArriveAt-pickupTime<=0 )
                {
                    minDistance = distance;
                    availableTaxi.clear();
                    availableTaxi.add(taxi);
                }
                else if(minDistance == distance && canArriveAt-pickupTime<=0)
                {
                    availableTaxi.add(taxi);
                }
            }
            if(!availableTaxi.isEmpty())
            {
                selectTaxi(customerID,pickupPoint,dropPoint,pickupTime,availableTaxi);
            }
            else
            {
                view.displayStatement("No taxi is available at this time.booking is rejected");
            }

        }
    }

    public void selectTaxi(int customerID,char pickupPoint,char dropPoint,int pickupTime, List<Taxi> availableTaxi)
    {
        //if one taxi is available
        if(availableTaxi.size()==1)
        {
            allocateTaxi(customerID,pickupPoint,dropPoint,pickupTime,availableTaxi.getFirst());
        }
        // if more taxis are available
        else
        {
            // find taxi with min earnings.
            Taxi selectedTaxi = availableTaxi.getFirst();
            double minEarning = selectedTaxi.getTotalEarning();
            for(Taxi taxi : availableTaxi)
            {
                if(taxi.getTotalEarning()<minEarning)
                {
                    minEarning = taxi.getTotalEarning();
                    selectedTaxi = taxi;
                }
            }
            allocateTaxi(customerID,pickupPoint,dropPoint,pickupTime,selectedTaxi);
        }
    }
    // Allocating the taxi.
    public void allocateTaxi(int customerID,char pickupPoint,char dropPoint,int pickupTime,Taxi taxi)
    {
        view.displayStatement("Taxi can be allocate.");
        int duration = Math.abs(dropPoint-pickupPoint);
        int dropTime = pickupTime+duration;
        double cost = (duration-1)*150 + 200;
        DB.addNewBooking(taxi.getTAXI_ID(),new Booking(customerID,bookingID++,pickupPoint,dropPoint,pickupTime,dropTime,cost));
        taxi.setCurrentPoint(dropPoint);
        taxi.setAvailableAt(dropTime);
        taxi.setTotalEarning(taxi.getTotalEarning()+cost);
        view.displayStatement("Taxi-"+taxi.getTAXI_ID()+" is allocated");
    }

    //Sent the data to view to print.
    public void displayTaxiDetails()
    {
        List<Taxi> taxiList = DB.getTAXI_LIST();
        TreeMap<Integer,List<Booking>> bookingList = DB.getBookingList();
        view.displayReport(taxiList,bookingList);
    }

}
