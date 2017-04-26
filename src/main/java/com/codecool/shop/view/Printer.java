package com.codecool.shop.view;

import com.codecool.shop.controller.ProductIterator;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by marek on 25.04.17.
 */
public class Printer {

    private static final ArrayList<String> options = new ArrayList<>(
        Arrays.asList("Choose option:\n",
            "[1] PRODUCT CATEGORIES", "[2] SUPPLIERS",
            "[3] ALL PRODUCTS",
            "[4] MY BASKET",
            "\n[0] EXIT"));

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

    public static void printEmpty() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    public static void printMenu() {
        Printer.printEmpty();
        for (String option : Printer.options) {
            System.out.println(option);
        }
    }

}
