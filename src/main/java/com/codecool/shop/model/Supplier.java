package com.codecool.shop.model;

import java.util.ArrayList;


public class Supplier extends BaseModel {

    private ArrayList<Product> products;

    public Supplier(Integer id, String name, String description) {
        super(id, name, description);
        this.products = new ArrayList<>();
    }

    public Supplier(String name, String description) {
        super(name, description);
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    }
}