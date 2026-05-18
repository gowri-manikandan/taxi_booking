package service;

import model.Booking;
import model.Taxi;
import util.InputUtil;
import util.OutputUtil;
import view.BookingView;

import java.util.ArrayList;
import java.util.List;

public class BookingService
{
    private final List<Taxi> taxiList ;
    int bookingID = 1;
    BookingView view ;
    public BookingService(BookingView view)
    {
        taxiList = new ArrayList<>();
        this.view = view;
    }
    public void taxiInit(int count)
    {
        for(int i=1;i<=count;i++)
        {
            taxiList.add(new Taxi(i,0.0d));
        }
        callMainMenu();
    }
    public void callMainMenu()
    {
        while (true)
            view.printMainMenu();
    }
    public void menuOption(int choice)
    {
        switch (choice)
        {
            case 1:
            {
                getBookingData();
                break;
            }
            case 2:
            {
                displayTaxiDetails();
                break;
            }
            case 0:
            {
                OutputUtil.printOutput("Thank you for using our services.");
                OutputUtil.printOutput("Come back soon..");
                OutputUtil.printOutput("Bye.");
                System.exit(10);
            }
            default:
            {
                OutputUtil.printError("Invalid input.");
            }
        }
    }

    private void getBookingData()
    {
        int customerID = InputUtil.getPositiveInt("Customer ID:");
        char pickupPoint = InputUtil.getPoint("Pickup Point:");
        char dropPoint = InputUtil.getPoint("Drop Point:");
        int pickupTime = InputUtil.getTime("Pickup Time:");
        if (pickupPoint == dropPoint)
        {
            OutputUtil.printError("The pickup Point and Drop point must not be same.");
            return;
        }
        checkAvailabilities(customerID,pickupPoint,dropPoint,pickupTime);
    }

    private void checkAvailabilities(int customerID,char pickupPoint,char dropPoint,int pickupTime)
    {
        List<Taxi> availableTaxi = new ArrayList<>();
        // check the taxis in pickup point
        for(Taxi taxi: taxiList)
        {
//            int distance = Math.abs(taxi.getCurrentPoint()-pickupPoint);
//            int canArriveAt = distance+taxi.getAvailableAt();
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
                OutputUtil.printOutput("No taxi is available at this time.booking is rejected");
            }

        }
        // check nearest taxis
    }

    public void selectTaxi(int customerID,char pickupPoint,char dropPoint,int pickupTime, List<Taxi> availableTaxi)
    {
        //if one ia available at pickup point
        if(availableTaxi.size()==1)
        {
            allocateTaxi(customerID,pickupPoint,dropPoint,pickupTime,availableTaxi.getFirst());
        }
        // if more taxi are available at pickup point
        else
        {
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

    public void allocateTaxi(int customerID,char pickupPoint,char dropPoint,int pickupTime,Taxi taxi)
    {
        OutputUtil.printOutput("Taxi can be allotted.");
        int duration = Math.abs(dropPoint-pickupPoint);
        int dropTime = pickupTime+duration;
        double cost = (duration-1)*150 + 200;

        taxi.setBookingList(new Booking(customerID,bookingID++,pickupPoint,dropPoint,pickupTime,dropTime,cost));
        taxi.setCurrentPoint(dropPoint);
        taxi.setAvailableAt(dropTime);
        taxi.setTotalEarning(taxi.getTotalEarning()+cost);
        OutputUtil.printOutput("Taxi-"+taxi.getTaxiID()+" is allotted");
    }
    private void displayTaxiDetails()
    {
        OutputUtil.printOutput("Taxi No: Total Earnings:\n");
        OutputUtil.printOutput("BookingID CustomerID From To PickupTime DropTime Amount");
        for(Taxi taxi : taxiList)
        {
            OutputUtil.printOutput("Taxi : "+taxi.getTaxiID()+"                Total Earnings : "+taxi.getTotalEarning());
            for(Booking booking : taxi.getBookingList())
            {
                OutputUtil.printOutput(" "+booking.getBookingID()+" "+booking.getCustomerID()+" "+booking.getPickupPoint()+" "+booking.getDropPoint()+" "+booking.getPickupTime()+" "+booking.getDropTime()+" "+booking.getCost());
            }
        }
    }

}
