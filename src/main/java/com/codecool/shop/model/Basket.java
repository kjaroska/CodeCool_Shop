package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Basket {

    private final ArrayList<Item> itemsList;
    private static Integer nextId = 1;
    private final Integer id;

    private Float totalPrice;

    public Basket(ArrayList<Item> items) {
        this.itemsList = items;
        this.id = Basket.nextId++;
        this.totalPrice = setTotalPrice();
    }

    public Basket(ArrayList<Item> items, Integer id) {
        this.itemsList = items;
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }

    private Float setTotalPrice() {
        Float orderPrice = 0.0f;
        for (Item item : this.itemsList) {
            orderPrice = orderPrice + item.getTotalPrice();
            System.out.println(item.getTotalPrice());
        }

        return orderPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Item> getItemList() {
        return this.itemsList;
    }

    public void addProduct(Item item) {
        itemsList.add(item);
    }

    public Iterator<Item> getIterator() {
        return this.itemsList.iterator();
    }

}
