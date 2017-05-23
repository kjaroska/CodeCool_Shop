package com.codecool.shop.controller;

import com.codecool.shop.dao.BasketDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.Request;


public class BasketController {

    BasketDaoImpl basketDao = new BasketDaoImpl();
    Basket basket;
    private SupplierController supplierController;

    public BasketController() {
        this.supplierController = new SupplierController();
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

    public static Basket removeFromBasket(Basket basket, Request req) {
        Integer itemId = Integer.parseInt(req.queryParams("productId"));
        java.util.Iterator<Item> itemIter = basket.getIterator();
        while (itemIter.hasNext()) {
            Item item = itemIter.next();
            if (item.getId().equals(itemId)) {
                itemIter.remove();
            }
        }
        return basket;
    }

    public static Basket editBasket(Basket basket, Request req) {
        Integer quantity = Integer.parseInt(req.queryParams("quantity"));
        Integer itemId = Integer.parseInt(req.queryParams("productId"));
        java.util.Iterator<Item> itemIter = basket.getIterator();
        while (itemIter.hasNext()) {
            Item item = itemIter.next();
            if (item.getId().equals(itemId)) {
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * item.getProduct().getDefaultPrice());
            }
        }
        return basket;
    }

    public Map<String, Object> renderProducts(Basket basket) {
        Map<String, Object> params = new HashMap<>();

        params.put("basket", basket.getItemList());
        params.put("price", basket.getTotalPrice());
        params.put("categories", ProductCategoryController.showAvailableCategories());
        params.put("suppliers", supplierController.showAvailableSuppliers());
        return params;
    }

    public void saveBasket(Request req) {
        basketDao.add(this.getBasket(), req.queryParams("userName"), req.queryParams("userAdress"));
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
