package com.codecool.shop.controller;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.ui.InputGetter;
import java.util.ArrayList;
import java.util.Arrays;

public class SummaryController {

  private static void printOrderSummary(Basket basket) {
    System.out.println("Your order summary:");
    Float orderPrice = 0.0f;
    for (Item product : basket.getItemList()) {
      System.out.println(product);
      orderPrice = orderPrice + product.getTotalPrice();
    }
    System.out.println("\nOrder overall price: " + orderPrice);
  }

  private static void printFakePayment() {
    ArrayList<String> paymentOptions = new ArrayList<>(Arrays.asList("Choose option",
        "[1] PAY IN CASH.", "[2] PAY BY CARD.", "[3] OTHER.",
        "[0] EXIT."));
    Integer userInput = 666;
    loop:
    while (userInput != 0) { // "loop" is a label, that is later used to stop the while loop
      for (String option : paymentOptions) {
        System.out.println(option);
      }
      userInput = InputGetter.getIntegerInput();
      switch (userInput) {
        case 1:
          System.out.println("You are paying in cash.");
          break loop;
        case 2:
          System.out.println("You are paying by card.");
          break loop;
        case 3:
          System.out.println("You are having fun!");
          break loop;
        case 0:
          break;
        default:
          System.out.println("Invalid Input. Try again.\n");
      }
    }
  }

  public static void summary(Basket basket) {
    printOrderSummary(basket);
    loop:
    while (true) {
      System.out.println("[1] ADVANCE TO PAYMENT.\n[0] EXIT.");
      Integer userInput = InputGetter.getIntegerInput();
      switch (userInput) {
        case 1:
          printFakePayment();
        case 0:
          break loop;
        default:
          System.out.println("Invalid input. Try again.\n");
      }
    }
  }
}

//                Printer.printOrderSummary(basket);
//              InputGetter.waitForEnter();
//              Printer.printFakePayment();
//              System.out.println("Choose payment method:");
//  Integer paymentMethod = InputGetter.getIntegerInput();
//              System.out.printf("You choose:" + paymentMethod);
//}
