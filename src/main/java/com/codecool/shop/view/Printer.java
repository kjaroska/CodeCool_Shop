package com.codecool.shop.view;

import com.codecool.shop.model.Item;
import java.util.ArrayList;

public class Printer {
    public static void printObject(String stringObject) {
        System.out.println(stringObject);
    }

    public static void printBasket(ArrayList<Item> basket) {
        for (Item product : basket) {
            System.out.println(product.toString());
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
