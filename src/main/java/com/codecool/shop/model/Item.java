package com.codecool.shop.model;

public class Item {

    private static Integer nextId = 1;
    private final Integer id;
    private Integer idBasket;
    private final Product product;
    private Integer quantity;
    private Float totalPrice;

    public Item(Product product, Integer quantity, Integer idBasket) {
        this.id = Item.nextId++;
        this.product = product;
        this.quantity = quantity;
        this.idBasket = idBasket;
        this.totalPrice = this.product.getDefaultPrice() * quantity;
    }

    public Item(Integer id, Product product, Integer quantity, Integer idBasket) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.idBasket = idBasket;
        this.totalPrice = this.product.getDefaultPrice() * quantity;
    }


    public Integer getIdBasket() {
        return this.idBasket;
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
        return this.totalPrice;
    }

    public Integer getId() {
        return this.id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Item: " +
            "id=" + id +
            " product=" + product.getName() +
            ", quantity=" + quantity +
            ", totalPrice=" + totalPrice;
    }
}
