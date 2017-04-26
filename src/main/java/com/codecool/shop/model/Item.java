package com.codecool.shop.model;

/**
 * Created by marek on 26.04.17.
 */
public class Item {

    private Product product;


    private Integer quantity;
    private Float totalPrice;

    public Item(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getDefaultPrice() * quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }
}
