package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import com.codecool.shop.ui.InputGetter;
import java.util.ArrayList;


public abstract class BasketController {

  public static Basket addToBasket(Basket basket, ArrayList<Integer> productFromCategoryIDs) {
    Printer.printObject("\nWhich product you want to add?");
    Integer productId = idValidation(productFromCategoryIDs);
        Product product;
    while (true) {
            try {
                product = new ProductDaoImpl().find(productId);
                Printer.printObject("How many " + product.getName() + " do you want? ");
                if (product != null) {
                    break;
                }
            } catch (Exception e) {
              Printer.printObject(e + ", No product with given id.");
                Printer.printObject("Insert proper id: ");
                productId = InputGetter.getIntegerInput();
            }
        }
    Integer quantity = newQuantityCheck();
        Item item = new Item(product, quantity);
        basket.addProduct(item);
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
        Integer newQuantity = newQuantityCheck();
        item.setQuantity(newQuantity);
        item.setTotalPrice(newQuantity * item.getProduct().getDefaultPrice());
      }
    }
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

  private static Integer newQuantityCheck() {
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
}
