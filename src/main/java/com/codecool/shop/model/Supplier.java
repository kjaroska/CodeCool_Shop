package com.codecool.shop.model;

import java.util.ArrayList;


public class Supplier extends BaseModel {

    private ArrayList<T> products;

    public Supplier(Integer id, String name, String description) {
        super(id, name, description);
        this.products = new ArrayList<>();
    }

    public void setProducts(ArrayList<T> products) {
        this.products = products;
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public void addProduct(T product) {
        this.products.add(product);
    }


    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}