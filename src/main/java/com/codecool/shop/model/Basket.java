package com.codecool.shop.model;

import com.codecool.shop.controller.ProductIterator;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marek on 25.04.17.
 */
public class Basket {

    private final ArrayList<Product> productList;
    private final Iterator<Product> iterator;

    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    public Basket(ArrayList<Product> productList) {
        this.productList = productList;
        this.iterator = getIterator(this.productList);
    }


    public void addProduct(Product product) {
        productList.add(product);
    }

    public boolean removeProduct(Product product) {
        boolean found;
        if (productList.contains(product)) {
            productList.remove(product);
            found = true;
        } else {
            found = false;
        }
        return found;
    }

    private Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

}
