package util;

import view.View;

import java.util.Scanner;

public class Util
{
    public static final Scanner sc = new Scanner(System.in);
    public static String getString(String message, View view)
    {
        while (true)
        {
            System.out.println(message);
            String name = sc.nextLine();
            if(name==null || name.length()<=3)
            {
                view.printError("Input cannot be empty and should be more than 3 characters.");
            }
            else
            {
                return name;
            }
        }
    }
    public static int getNumberOfTaxis(String message,View view)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();

                if (value > 0) {
                    return value;
                } else {
                    view.printError("Number of Taxis must be > 0. Please enter a positive number.");
                }
            } else {
                view.printError("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }

    public static int getCustomerID(String message,View view)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();

                if (value > 0) {
                    return value;
                } else {
                    view.printError("Please enter a positive number.");
                }
            } else {
                view.printError("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }

    public static int getChoice(String message, View view)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                view.printError("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }
    public static char getPickupORDropPoint(String message,View view)
    {
        while(true)
        {
            System.out.println(message);

            String input = sc.next().toUpperCase();

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

            view.printError("Invalid input. Enter a character between A and F.");
        }
    }
    public static int getTime(String message,View view)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();

                if (value >= 0 && value < 24) {
                    return value;
                } else {
                    view.printError("Please enter a number between 0 and 24.");
                }
            } else {
                view.printError("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }
}
