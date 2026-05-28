package view;

import model.Booking;
import model.Taxi;
import service.BookingService;

import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class View
{
    //variables
    BookingService bookingService;
    public final Scanner SCANNER;
    //constructor
    public View()
    {
        bookingService = new BookingService(this);
        SCANNER = new Scanner(System.in);
    }

    //init
    public void init()
    {
        String name = getString("Enter the Taxi Company Name:");
        int numberOfTaxi = getPositiveNUmber("Enter the Number of Taxis in your Company:");
        bookingService.initializeTaxi(numberOfTaxi);
        displayLabel();
        displayStatement("                Welcome To "+name+" Call Taxis.                      ");
        displayLabel();
        displayStatement("Taxis Are read for your Service. the taxi are in the Point A at 1 O'clock");
        displayStatement("Our Service are From A To F.\n");
        showMainMenu();
    }

    //print
    public void displayMainMenu()
    {
        System.out.println("======================= Main Menu =======================");
        System.out.println("1. Call Taxi Booking");
        System.out.println("2. Display the Taxi details");
        System.out.println("0. Exit");
    }
    public void displayError(String error)
    {
        System.err.println(error);
    }
    public void displayReport(List<Taxi>list, TreeMap<Integer,List<Booking>> map)
    {
        displayStatement("Taxi No: Total Earnings:\n");
        displayStatement("BookingID CustomerID From To PickupTime DropTime Amount");
        for (Taxi taxi : list)
        {
            System.out.printf(
                    "\nTaxi : %-5d Total Earnings : %-8f\n",
                    taxi.getTAXI_ID(),
                    taxi.getTotalEarning()
            );

            List<Booking> bookings =
                    map.getOrDefault(taxi.getTAXI_ID(), List.of());

            System.out.printf(
                    "%-10s %-12s %-8s %-8s %-12s %-12s %-8s\n",
                    "BookingID",
                    "CustomerID",
                    "From",
                    "To",
                    "Pickup",
                    "Drop",
                    "Cost"
            );

            for (Booking booking : bookings)
            {
                System.out.printf(
                        "%-10d %-12d %-8c %-8c %-12d %-12d %-8f\n",
                        booking.getBookingID(),
                        booking.getCUSTOMER_ID(),
                        booking.getPICKUP_POINT(),
                        booking.getDROP_POINT(),
                        booking.getPICKUP_TIME(),
                        booking.getDROP_TIME(),
                        booking.getCOST()
                );
            }
        }
    }
    public void displayLabel()
    {
        displayStatement("---------------------------------------------------------------------");
    }
    public void displayStatement(String message)
    {
        System.out.println(message);
    }


    //get


    public void showMainMenu()
    {
        while (true)
        {
            displayMainMenu();
            int choice = getPositiveNUmber("Enter Your choice :");
            switch (choice)
            {
                case 1:
                {
                    bookingService.getBookingInfo();
                    break;
                }
                case 2:
                {
                    bookingService.displayTaxiDetails();
                    break;
                }
                case 0:
                {
                    displayStatement("Thank you for Using Our service.");
                    displayStatement("Come Back");
                    displayStatement("bye....");
                    System.exit(1);
                }
                default:
                {
                    displayStatement("Invalid Number.");
                }
            }
        }
    }

    public String getString(String prompt)
    {
        while (true)
        {
            displayStatement(prompt);
            String name = SCANNER.nextLine();
            if(name==null || name.length()<=3)
            {
                displayError("Input cannot be empty and should be more than 3 characters.");
            }
            else
            {
                return name;
            }
        }
    }
    public int getPositiveNUmber(String prompt)
    {
        while (true)
        {
            displayStatement(prompt);
            try
            {
                int value = Integer.parseInt(SCANNER.nextLine().trim());

                if (value > 0)
                {
                    return value;
                }

                displayStatement("Invalid input. Please enter a positive number.");
            }
            catch (NumberFormatException e)
            {
                displayStatement("Invalid input. Please enter a valid number.");
            }
        }
    }

    public char getPickupORDropPoint(String prompt)
    {
        while(true)
        {
            displayStatement(prompt);

            String input = SCANNER.next().toUpperCase();

            // check single character
            if(input.length() == 1)
            {
                char ch = input.charAt(0);

                // check range A-F
                if(ch >= 'A' && ch <= 'F')
                {
                    return ch;
                }
            }

            displayError("Invalid input. Enter a character between A and F.");
        }
    }
    public int getTime(String message)
    {
        while (true)
        {
            System.out.print(message + " : ");

            try
            {
                int value = Integer.parseInt(SCANNER.nextLine().trim());

                if (value >= 0 && value < 24)
                {
                    return value;
                }

                displayError("Please enter a number between 0 and 23.");
            }
            catch (NumberFormatException e)
            {
                displayError("Invalid input. Please enter numbers only.");
            }
        }
    }

}
