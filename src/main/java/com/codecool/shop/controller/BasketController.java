package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import com.codecool.shop.ui.InputGetter;

/**
 * Created by marek on 26.04.17.
 */
@SuppressWarnings("ALL")
public class BasketController {

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
        Integer quantity = InputGetter.getIntegerInput();
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
}
