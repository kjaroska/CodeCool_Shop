package com.codecool.shop.controller;

/**
 * Created by marek on 25.04.17.
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marek on 17.04.17.
 */
public class ProductIterator<T> implements com.codecool.shop.controller.Iterator {

    private final ArrayList<T> products;
    private Integer index;

    public ProductIterator(ArrayList<T> products) {
        this.products = products;
    }

    @Override
    public Iterator<T> Iterator() {
        return products.iterator();
    }

    @Override
    public Boolean hasNext() {
        return index < products.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return products.get(index++);
        }
        return null;
    }
}
