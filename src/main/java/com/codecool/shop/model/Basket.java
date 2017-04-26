package com.codecool.shop.model;

import com.codecool.shop.controller.ProductIterator;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marek on 25.04.17.
 */
public class Basket {

    private final ArrayList<T> productList;
    private final Iterator<T> iterator;

    public ArrayList<T> getProductList() {
        return this.productList;
    }

    public Basket(ArrayList<T> productList) {
        this.productList = productList;
        this.iterator = getIterator(this.productList);
    }


    public void addProduct(T product) {
        productList.add(product);
    }

    public boolean removeProduct(T product) {
        boolean found;
        if (productList.contains(product)) {
            productList.remove(product);
            found = true;
        } else {
            found = false;
        }
        return found;
    }

    private Iterator<T> getIterator(ArrayList<T> productList) {
        return new ProductIterator(productList).Iterator();
    }

}
