package util;

import java.util.Scanner;

public class InputUtil
{
    public static final Scanner sc = new Scanner(System.in);

    public static int getPositiveInt(String message)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();

                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }

    public static int getInt(String message)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                System.out.println("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }
    public static char getPoint(String message)
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

            System.out.println("Invalid input. Enter a character between A and F.");
        }
    }
    public static int getTime(String message)
    {
        while (true) {
            System.out.println(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();

                if (value >= 0 && value < 24) {
                    return value;
                } else {
                    System.out.println("Please enter a number between 0 and 24.");
                }
            } else {
                System.out.println("Invalid input. Numbers only.");
                sc.nextLine();
            }
        }
    }
}
