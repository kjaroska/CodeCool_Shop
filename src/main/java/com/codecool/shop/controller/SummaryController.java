package com.codecool.shop.controller;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.Arrays;

public class SummaryController {

  private static void printOrderSummary(Basket basket) {
      Printer.printObject("Your order summary:");
    Float orderPrice = 0.0f;
    for (Item product : basket.getItemList()) {
        Printer.printObject(product.toString());
      orderPrice = orderPrice + product.getTotalPrice();
    }
      Printer.printObject("\nOrder overall price: " + orderPrice);
  }

  private static void printFakePayment() {
    ArrayList<String> paymentOptions = new ArrayList<>(Arrays.asList("Choose option",
        "[1] PAY IN CASH.", "[2] PAY BY CARD.", "[3] OTHER.",
        "[0] EXIT."));
      Integer userInput;
    loop:
    while (true) {
      for (String option : paymentOptions) {
          Printer.printObject(option);
      }
      userInput = InputGetter.getIntegerInput();
      switch (userInput) {
        case 1:
            Printer.printObject("You are paying in cash.");
          break loop;
        case 2:
            Printer.printObject("You are paying by card.");
          break loop;
        case 3:
            Printer.printObject("You are having fun!");
          break loop;
        case 0:
          break;
        default:
            Printer.printObject("Invalid Input. Try again.\n");
      }
    }
  }

  public static void summary(Basket basket) {
    printOrderSummary(basket);
    loop:
    while (true) {
        Printer.printObject("[1] ADVANCE TO PAYMENT.\n[0] EXIT.");
      Integer userInput = InputGetter.getIntegerInput();
      switch (userInput) {
        case 1:
          printFakePayment();
        case 0:
          break loop;
        default:
            Printer.printObject("Invalid input. Try again.\n");
      }
    }
  }
}