package com.codecool.shop.model;

public class Item {

    private static Integer nextId = 1;
    private final Integer id;
    private final Product product;
    private Integer quantity;
    private Float totalPrice;

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

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getTotalPrice() {
        return this.product.getDefaultPrice() * this.quantity;
    }

    public Integer getId() {
        return this.id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return String.format("Item: " +
                "id=%1$d" +
                " product=%2$s" +
                ", quantity=%3$d" +
                ", totalPrice=%4$.2f ",
            id, product.getName(), quantity, totalPrice);
    }
}
