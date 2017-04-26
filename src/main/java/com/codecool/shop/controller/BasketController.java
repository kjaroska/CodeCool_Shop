package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import com.codecool.shop.ui.inputGetter;
import java.util.Iterator;

/**
 * Created by marek on 26.04.17.
 */
public class BasketController {

    public static void showOrder(Basket basket) {
        Iterator<Item> itemIterator = basket.getIterator();

    }

    public static Basket addToBasket(Basket basket) {
        Printer.printObject("Which product you want to add? ");
        Integer productId = inputGetter.getIntegerInput();
        Product product = new ProductDaoImpl().find(productId);
        Printer.printObject("How many " + product.getName() + " do you want? ");
        Integer quantity = inputGetter.getIntegerInput();
        Item item = new Item(product, quantity);
        basket.addProduct(item);
        return basket;
    }
}
