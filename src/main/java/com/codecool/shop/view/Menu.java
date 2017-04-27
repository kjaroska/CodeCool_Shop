package com.codecool.shop.view;


import java.util.ArrayList;
import java.util.Arrays;

public class Menu {

  private final ArrayList<String> mainMenu = new ArrayList<>(Arrays.asList("Choose option",
      "[1] SHOW PRODUCTS BY PRODUCT CATEGORY.", "[2] SHOW PRODUCTS BY SUPPLIER.",
      "[3] SHOW ALL AVAILABLE PRODUCTS.",
      "[4] SHOW MY BASKET.",
      "[5] REMOVE ITEM FROM BASKET.",
      "[6] EDIT ITEM QUANTITY IN BASKET",
      "[7] ORDER SUMMARY",
      "[Any other] EXIT."));

  public ArrayList<String> getMainMenu() {
    return mainMenu;
  }
}
