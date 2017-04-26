package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ProductController {

    public static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

    public static void showAvailableProducts() {
        ArrayList<Product> products = new ProductDaoImpl().getAll();
        Iterator<Product> productIterator = ProductController.getIterator(products);
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            Printer.printObject(product.toString());
        }
    }

}
