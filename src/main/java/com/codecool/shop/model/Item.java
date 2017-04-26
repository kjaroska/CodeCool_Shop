package com.codecool.shop.model;

/**
 * Created by marek on 26.04.17.
 */
public class Item {

    private static Integer nextId = 1;
    private final Integer id;
    private final Product product;
    private final Integer quantity;
    private final Float totalPrice;

    public Item(Product product, Integer quantity) {
        this.id = Item.nextId++;
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

    public Integer getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Item: " +
            "id=" + id +
            "product=" + product.getName() +
            ", quantity=" + quantity +
            ", totalPrice=" + totalPrice;
    }
}
