package com.codecool.shop.view;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import java.util.ArrayList;
import java.util.Arrays;

public class Printer {
    public static void printObject(String stringObject) {
        System.out.println(stringObject);
    }

    public static void printBasket(ArrayList<Item> basket) {
        for (Item product : basket) {
            System.out.println(product.toString());
        }
    }

  public static void printOrderSummary(Basket basket) {
    System.out.println("Your order summary:");
    Float orderPrice = 0.0f;
    for (Item product : basket.getItemList()) {
      System.out.println(product);
      orderPrice = orderPrice + product.getTotalPrice();
    }
    System.out.println("\nOrder overall price: " + orderPrice);
    System.out.println("Advance to payment?");
  }

  public static void printFakePayment() {
    ArrayList<String> paymentOptions = new ArrayList<>(Arrays.asList("Choose option",
        "[1] PAY IN CASH", "[2] PAY BY CARD", "[3] OTHER",
        "[0] EXIT."));
    for (String option : paymentOptions) {
      System.out.println(option);
    }
  }

    public static void printEmpty() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    public static void printMenu(ArrayList<String> options) {
        Printer.printEmpty();
        for (String option : options) {
            System.out.println(option);
        }
    }

}
