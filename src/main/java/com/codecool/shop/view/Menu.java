package com.codecool.shop.view;


import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

  private final ArrayList<String> mainMenu = new ArrayList<>(Arrays.asList("Choose option",
      "[1] FIND PRODUCT BY NAME.",
      "[2] SHOW PRODUCTS BY PRODUCT CATEGORY.",
      "[3] SHOW PRODUCTS BY SUPPLIER.",
      "[4] SHOW ALL AVAILABLE PRODUCTS.",
      "[5] SHOW MY BASKET.",

      "[Any other key] EXIT."));

  private final ArrayList<String> basketMenu = new ArrayList<>(Arrays.asList("Choose option",
      "[1] REMOVE ITEM FROM BASKET.",
      "[2] EDIT ITEM QUANTITY IN BASKET",
      "[3] ORDER SUMMARY",
      "[0] BACK TO MAIN MENU"));

  public ArrayList<String> getMainMenu() {
    return mainMenu;
  }

  public ArrayList<String> getBasketMenu() {
    return basketMenu;
  }
}
