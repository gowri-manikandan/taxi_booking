package view;

import service.BookingService;
import util.InputUtil;

public class BookingView
{
    BookingService service;
    public BookingView()
    {
        service = new BookingService(this);
        System.out.println("Welcome to MGM Taxis");
        System.out.println("The locations available in our servers");
        System.out.println("[A,B,C,D,E,F]");
        init();
    }
    public void init()
    {
        int count = InputUtil.getPositiveInt("enter the Number of Taxis :");
        service.taxiInit(count);
    }
    public void printMainMenu()
    {
        System.out.println("1.Call taxi booking");
        System.out.println("2.Display the Taxi details");
        System.out.println("0.Exit");
        int choice = InputUtil.getInt("Enter the choice");
        service.menuOption(choice);
    }
}
