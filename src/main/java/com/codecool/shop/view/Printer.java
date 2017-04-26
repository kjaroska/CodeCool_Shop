package com.codecool.shop.view;

import com.codecool.shop.controller.ProductIterator;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.Iterator;

public class Printer {

    public static void printObject(String stringObject) {
        System.out.println(stringObject);
    }

    public static void printBasket(ArrayList<Product> basket) {
        Iterator<Product> iter = new ProductIterator(basket).Iterator();
        while (iter.hasNext()) {
            Object product = iter.next();
            System.out.print(product.toString());
        }
    }

    private static void printEmpty() {
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
