package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Request;

public abstract class ProductController {

  private static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

  public static ArrayList<Product> showAvailableProducts() {
    ArrayList<Product> products = new ProductDaoImpl().getAll();
    return products;
  }

    public static ArrayList<Integer> showProductByName(Request req) {
        String productName = "";
    ArrayList<Product> products = new ProductDaoImpl().getByName(productName);
    Iterator<Product> productIterator = ProductController.getIterator(products);
    ArrayList<Integer> productsIDs = new ArrayList<>();
    while (productIterator.hasNext()) {
        Product product = productIterator.next();
        ;
      productsIDs.add(product.getId());
    }
    return productsIDs;
  }

  public static Map<String, Object> renderProducts(List<Product> products) {
    Map<String, Object> params = new HashMap<>();
    params.put("products", products);
    params.put("categories", ProductCategoryController.showAvailableCategories());
    params.put("suppliers", SupplierController.showAvailableSuppliers());

    return params;
  }
}
