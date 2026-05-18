package view;

import model.Booking;
import model.Taxi;
import service.BookingService;

import java.util.List;
import java.util.TreeMap;

public class View
{
    public void printMainMenu()
    {
        System.out.println("======================= Main Menu =======================");
        System.out.println("1. Call Taxi Booking");
        System.out.println("2. Display the Taxi details");
        System.out.println("0. Exit");
    }
    public void printError(String error)
    {
        System.err.println(error);
    }

    public void printMassage(String message)
    {
        System.out.println(message);
    }
    public void printReport(List<Taxi>list, TreeMap<Integer,List<Booking>> map)
    {
        printMassage("Taxi No: Total Earnings:\n");
        printMassage("BookingID CustomerID From To PickupTime DropTime Amount");
        for (Taxi taxi : list)
        {
            System.out.printf(
                    "\nTaxi : %-5d Total Earnings : %-8f\n",
                    taxi.getTaxiID(),
                    taxi.getTotalEarning()
            );

            List<Booking> bookings =
                    map.getOrDefault(taxi.getTaxiID(), List.of());

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
                        booking.getCustomerID(),
                        booking.getPickupPoint(),
                        booking.getDropPoint(),
                        booking.getPickupTime(),
                        booking.getDropTime(),
                        booking.getCost()
                );
            }
        }
    }
}
