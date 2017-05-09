package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class ProductController {

  private static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

  public static ArrayList<Product> showAvailableProducts() {
    ArrayList<Product> products = new ProductDaoImpl().getAll();
    return products;
  }

  public static ArrayList<Integer> showProductByName() {
    Printer.printObject("Enter Product's name: ");
    String productName = InputGetter.getStringInput();
    ArrayList<Product> products = new ProductDaoImpl().getByName(productName);
    Iterator<Product> productIterator = ProductController.getIterator(products);
    ArrayList<Integer> productsIDs = new ArrayList<>();
    while (productIterator.hasNext()) {
      Product product = productIterator.next();
      Printer.printObject(product.toString());
      productsIDs.add(product.getId());
    }
    return productsIDs;
  }

  public static Map<String, ArrayList<Product>> renderProducts(List<Product> products) {
    Map<String, ArrayList<Product>> params = new HashMap<>();

    params.put("products", (ArrayList<Product>) products);
    return params;
  }
}
