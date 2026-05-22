package service;

import repository.DB;
import model.Booking;
import model.Taxi;
import util.Util;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BookingService
{
    int bookingID = 1;
    DB DB;
    View view;
    public BookingService(DB repo, View view)
    {
        DB = repo;
        this.view = view;
    }
    // get the basic data name of the company and number of taxis.
    public void init()
    {
        String name = Util.getString("Enter the Taxi Company Name:",view);
        int numberOfTaxi = Util.getNumberOfTaxis("Enter the Number of Taxis in your Company:",view);
        initializeTaxi(numberOfTaxi);
        view.printMassage("---------------------------------------------------------------------");
        view.printMassage("                Welcome To "+name+" Call Taxis.                      ");
        view.printMassage("---------------------------------------------------------------------");
        view.printMassage("Taxis Are read for your Service. the taxi are in the Point A at 1 O'clock");
        view.printMassage("Our Service are From A To F.\n");
        while (true)
        {
            view.printMainMenu();
            int choice = Util.getChoice("Enter Your choice :",view);
            switch (choice)
            {
                case 1:
                {
                    getBookingInfo();
                    break;
                }
                case 2:
                {
                    displayTaxiDetails();
                    break;
                }
                case 0:
                {
                    view.printMassage("Thank you for Using Our service.");
                    view.printMassage("Come Back");
                    view.printMassage("bye....");
                    System.exit(1);
                }
                default:
                {
                    view.printError("Invalid Number.");
                }
            }
        }
    }
    // Initialize the taxis.
    private void initializeTaxi(int numberOfTaxi)
    {
        for(int i=1;i<=numberOfTaxi;i++)
        {
            DB.addNewTaxi(new Taxi(i));
        }
    }

    // the booking information.
    private void getBookingInfo()
    {
        int customerID = Util.getCustomerID("Customer ID:",view);
        char pickupPoint = Util.getPickupORDropPoint("Pickup Point:",view);
        char dropPoint = Util.getPickupORDropPoint("Drop Point:",view);
        if (pickupPoint == dropPoint)
        {
            view.printError("The pickup Point and Drop point must not be same.");
            return;
        }
        int pickupTime = Util.getTime("Pickup Time:",view);
        checkAvailabilities(customerID,pickupPoint,dropPoint,pickupTime);
    }

    // Check for the available taxis.
    private void checkAvailabilities(int customerID,char pickupPoint,char dropPoint,int pickupTime)
    {
        List<Taxi> taxiList = DB.getTaxiList();
        List<Taxi> availableTaxi = new ArrayList<>();

        // Check the taxis in pickup point
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
                view.printMassage("No taxi is available at this time.booking is rejected");
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
        view.printMassage("Taxi can be allocate.");
        int duration = Math.abs(dropPoint-pickupPoint);
        int dropTime = pickupTime+duration;
        double cost = (duration-1)*150 + 200;
        DB.addNewBooking(taxi.getTaxiID(),new Booking(customerID,bookingID++,pickupPoint,dropPoint,pickupTime,dropTime,cost));
        taxi.setCurrentPoint(dropPoint);
        taxi.setAvailableAt(dropTime);
        taxi.setTotalEarning(taxi.getTotalEarning()+cost);
        view.printMassage("Taxi-"+taxi.getTaxiID()+" is allocated");
    }

    //Sent the data to view to print.
    private void displayTaxiDetails()
    {
        List<Taxi> taxiList = DB.getTaxiList();
        TreeMap<Integer,List<Booking>> bookingList = DB.getBookingList();
        view.printReport(taxiList,bookingList);
    }

}
