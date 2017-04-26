package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.T;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductController {

    public static java.util.Iterator<T> getIterator(ArrayList<T> productList) {
        return new ProductIterator(productList).Iterator();
    }

    public static void showAvailableProducts() {
        ArrayList<T> products = new ProductDaoImpl().getAll();
        Iterator<T> productIterator = ProductController.getIterator(products);
        while (productIterator.hasNext()) {
            T product = productIterator.next();
            Printer.printObject(product.toString());
        }
    }

}
