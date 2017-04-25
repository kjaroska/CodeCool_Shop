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

    private static final ArrayList<String> options = new ArrayList<>(Arrays.asList("Choose option",
        "1 to to show product categories.", "2 to show suppliers.",
        "3 to see all available products.",
        "4 to see all products in the basket.",
        "0 to exit."));

    public static void printProduct(String productToString) {
        System.out.println(productToString);
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

    public static void printMenu() {
        Printer.printEmpty();
        for (String option : Printer.options) {
            System.out.println(option);
        }
    }

}
