package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.Request;


public class BasketController {

    Basket basket;

    public BasketController() {
        this.basket = new Basket(new ArrayList<Item>());
    }

    public Basket addToBasket(Basket basket, Request req) {
        Product product;
        Integer productId = Integer.parseInt(req.queryParams("productId"));
        while (true) {
            try {
                product = new ProductDaoImpl().find(productId);
                if (product != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        Boolean found = false;
        Item item = new Item(product, quantity, basket.getId());
        for (Item existingItem : basket.getItemList()) {
            if (existingItem.getProduct().getName().equals(item.getProduct().getName())) {
                found = true;
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
            }
        }
        if (!found) {
            basket.addProduct(item);
        }
        return basket;
    }

  private static Basket removeFromBasket(Basket basket) {
        Printer.printBasket(basket.getItemList());
        Printer.printObject("Which product you want to remove? ");
        Integer itemId = InputGetter.getIntegerInput();
        java.util.Iterator<Item> itemIter = basket.getIterator();
        while (itemIter.hasNext()) {
            Item item = itemIter.next();
            if (item.getId().equals(itemId)) {
                itemIter.remove();
            }
        }
        return basket;
    }

    private static Basket editBasket(Basket basket, Request req) {
        Integer itemId = InputGetter.getIntegerInput();
        java.util.Iterator<Item> itemIter = basket.getIterator();
        while (itemIter.hasNext()) {
            Item item = itemIter.next();
            if (item.getId().equals(itemId)) {
                Printer.printObject("Insert new quantity of item in basket: ");
                Integer newQuantity = quantityCheck();
                item.setQuantity(newQuantity);
                item.setTotalPrice(newQuantity * item.getProduct().getDefaultPrice());
            }
        }
        return basket;
    }

    private static Integer quantityCheck() {
        Integer newQuantity;
        while (true) {
            newQuantity = InputGetter.getIntegerInput();
            if (newQuantity > 1000) {
                Printer.printObject("You are asking for too much. Try again: ");
            } else if (newQuantity <= 0) {
                Printer.printObject("Quantity have to above 0");
                Printer.printObject("Provide proper quantity of item: ");
            } else {
                break;
            }
        }
        return newQuantity;
    }

    private static Integer idValidation(ArrayList<Integer> productFromCategoryIDs) {
        Integer productId;
        do {
            System.out.println("Make sure you enter id from the list above.");
            productId = InputGetter.getIntegerInput();
        } while (!productFromCategoryIDs.contains(productId));
        return productId;
    }

    public static Map<String, Object> renderProducts(Basket basket) {
        Map<String, Object> params = new HashMap<>();

        params.put("basket", basket.getItemList());
        return params;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
