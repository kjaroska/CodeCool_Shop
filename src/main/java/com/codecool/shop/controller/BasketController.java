package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import com.codecool.shop.ui.InputGetter;


public abstract class BasketController {

    public static Basket addToBasket(Basket basket) {
        Printer.printObject("Which product you want to add? ");
        Integer productId = InputGetter.getIntegerInput();
        Product product;
        while (true) {
            try {
                product = new ProductDaoImpl().find(productId);
                Printer.printObject("How many " + product.getName() + " do you want? ");
                if (product != null) {
                    break;
                }
            } catch (Exception e) {
                Printer.printObject(e + ", No product with given id");
                Printer.printObject("Insert proper id: ");
                productId = InputGetter.getIntegerInput();
            }
        }
        Integer quantity;
        while (true) {
            quantity = InputGetter.getIntegerInput();
            if (quantity <= 0) {
                Printer.printObject("Quantity have to above 0");
                Printer.printObject("Provide proper quantity of item: ");
            } else {
                break;
            }
        }
        Item item = new Item(product, quantity);
        basket.addProduct(item);
        return basket;
    }

    public static Basket removeFromBasket(Basket basket) {
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

    public static Basket editBasket(Basket basket) {
        Printer.printBasket(basket.getItemList());
        Printer.printObject("Which product you want to edit? ");
        Integer itemId = InputGetter.getIntegerInput();
        java.util.Iterator<Item> itemIter = basket.getIterator();
        while (itemIter.hasNext()) {
            Item item = itemIter.next();
            if (item.getId().equals(itemId)) {
                Printer.printObject("Insert new quantity of item in basket: ");
                Integer newQuantity;
                while (true) {
                    newQuantity = InputGetter.getIntegerInput();
                    if (newQuantity <= 0) {
                        Printer.printObject("Quantity have to above 0");
                        Printer.printObject("Provide proper quantity of item: ");
                    } else {
                        break;
                    }
                }
                item.setQuantity(newQuantity);
                item.setTotalPrice(newQuantity * item.getProduct().getDefaultPrice());
            }
        }
        return basket;
    }
}
