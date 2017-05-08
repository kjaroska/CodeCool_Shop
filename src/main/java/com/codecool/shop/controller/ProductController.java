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
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class ProductController {

  private static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

  public static ArrayList<Integer> showAvailableProducts() {
    ArrayList<Product> products = new ProductDaoImpl().getAll();
    Iterator<Product> productIterator = ProductController.getIterator(products);
    ArrayList<Integer> productsIDs = new ArrayList<>();
    while (productIterator.hasNext()) {
      Product product = productIterator.next();
      Printer.printObject(product.toString());
      productsIDs.add(product.getId());
    }
    return productsIDs;
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

  public static Object renderProducts(Request req, Response res) {
    Map<String, ArrayList<Product>> params = new HashMap<>();
    List<Product> products = new ProductDaoImpl().getAll();
    params.put("products", (ArrayList<Product>) products);
    ModelAndView modelAndView = new ModelAndView(params, "product/index");
    return modelAndView;
  }
}
