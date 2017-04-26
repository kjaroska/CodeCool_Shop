package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by marek on 25.04.17.
 */
public class Basket {

    private final ArrayList<Item> itemsList;

    public ArrayList<Item> getItemList() {
        return this.itemsList;
    }

    public Basket(ArrayList<Item> items) {
        this.itemsList = items;
    }


    public void addProduct(Item item) {
        itemsList.add(item);
    }


    public Iterator<Item> getIterator() {
        return this.itemsList.iterator();
    }

}
