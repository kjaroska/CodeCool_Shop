package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Menu;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;


public class BasketController {

    Basket basket;

    public BasketController() {
        this.basket = new Basket(new ArrayList<Item>());
    }

    public Basket addToBasket(Basket basket, Integer idToFind) {
        Product product;
        while (true) {
            try {
                product = new ProductDaoImpl().find(idToFind);
                if (product != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        Integer quantity = 2;
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

  private static Basket editBasket(Basket basket) {
        Printer.printBasket(basket.getItemList());
        Printer.printObject("Which product you want to edit? ");
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

  public static Basket basketOptions(Basket basket, Menu menu) {
    basketLoop:
    //loop for basket menu
    while (true) {
      Printer.printBasket(basket.getItemList());
      Printer.printMenu(menu.getBasketMenu());
      Integer basketOption = InputGetter.getIntegerInput();
      switch (basketOption) {
        case 1:
          basket = BasketController.removeFromBasket(basket);
          continue;
        case 2:
          basket = BasketController.editBasket(basket);
          continue;
        case 3:
          SummaryController.summary(basket);
          continue;
        case 0:
          break basketLoop;
        default:
          System.out.println("Invalid input. Try again.");
      }
    }
    return basket;
  }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
