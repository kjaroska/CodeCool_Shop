package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;


import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import java.util.*;
import java.util.Iterator;

public class ProductController {

    public static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

    public static void showAvailableProducts() {
        ArrayList<Product> products = new ProductDaoImpl().getAll();
        Iterator<Product> productIterator = ProductController.getIterator(products);
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            System.out.println(product.toString());
        }
    }

}
