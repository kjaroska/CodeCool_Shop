package com.codecool.shop.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import com.codecool.shop.view.Printer;

public class InputGetter {

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static String getStringInput() {
        Scanner scanner = getScanner();
        return scanner.next();
    }

  public static String getLineInput() {
        Scanner scanner = getScanner();
        return scanner.nextLine();
    }

    public static Integer getIntegerInput() {
        Scanner scanner = getScanner();
        boolean bError = true;
        Integer intInput = 0;
        while (bError) {
            try {
                if (scanner.hasNextInt()) {
                    intInput = scanner.nextInt();
                }
            } catch (Exception e) {
                System.out.print(e.toString());
                scanner.reset();
                scanner.hasNextInt();
                continue;
            }
            bError = false;
        }
        return intInput;
    }

    public static Float getFloatInput() {
        Scanner scanner = getScanner();
        scanner.useLocale(Locale.US);
        Float price = 0.0f;
        Boolean search = true;
        while (search) {
            System.out.print("Enter a float price: ");
            try {
                price = scanner.nextFloat();
                search = false;
            } catch (InputMismatchException ime) {
                System.out.println(ime.toString());
                scanner.reset();
                scanner.next();// Flush the buffer from all data
            }
        }
        return price;
    }

    public static Date getDate() {
      String date = InputGetter.getLineInput();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = null;
        Boolean search = true;
        while (search) {
            try {
                //Parsing the String
                date2 = dateFormat.parse(date);
                search = false;
            } catch (ParseException e) {
                System.out.println("Bad format try (DD-MM-YYYY)");
              date = InputGetter.getLineInput();
            }
        }
        return date2;
    }

    public static void waitForEnter() {
        Scanner scanner = getScanner();
        System.out.print("\nPress ENTER to continue");
        Printer.printEmpty();
        scanner.nextLine();
    }
}

