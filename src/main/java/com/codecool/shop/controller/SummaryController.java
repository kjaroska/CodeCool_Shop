package com.codecool.shop.controller;


import com.codecool.shop.dao.BasketDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

class SummaryController {

  private static void printOrderSummary(Basket basket) {

      Printer.printObject("Your order summary:");
      Float orderPrice = 0.0f;
      for (Item product : basket.getItemList()) {
          Printer.printObject(product.toString());
          orderPrice = orderPrice + product.getTotalPrice();
      }
      Printer.printObject("\nOrder overall price: " + orderPrice);
      basket.setTotalPrice(orderPrice);
  }

  private static void printFakePayment(Basket basket, String fullName, String deliveryAdress) {
    ArrayList<String> paymentOptions = new ArrayList<>(Arrays.asList("\nChoose option",
        "[1] PAY IN CASH.", "[2] PAY BY CARD.", "[3] OTHER.",
        "[0] EXIT."));
    Integer userInput;

    Printer
        .printObject("\nYour full name: " + fullName + "\nYour delivery adress: " + deliveryAdress);
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
            break loop;
        default:
            Printer.printObject("Invalid Input. Try again.\n");
      }
    }
        new BasketDaoImpl().add(basket);
  }

  public static void summary(Basket basket) {
    printOrderSummary(basket);
    loop:
    while (true) {
        Printer.printObject("[1] ADVANCE TO PAYMENT.\n[0] EXIT.");
      Integer userInput = InputGetter.getIntegerInput();
      switch (userInput) {
        case 1:
          String fullName = getFullName();
          String deliveryAddress = getDeliveryAddress();
          printFakePayment(basket, fullName, deliveryAddress);
        case 0:
          break loop;
        default:
            Printer.printObject("Invalid input. Try again.\n");
      }
    }
  }

  public static String getFullName() {

    Printer.printObject("Enter your full name: ");
    return InputGetter.getLineInput();
  }

  public static String getDeliveryAddress() {
    Printer.printObject("Enter your delivery address: ");
    return InputGetter.getLineInput();
  }
}
