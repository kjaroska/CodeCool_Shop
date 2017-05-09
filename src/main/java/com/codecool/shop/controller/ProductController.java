package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Response;
import spark.Request;

public abstract class ProductController {

    private static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }


    public static ArrayList<Product> showAvailableProducts() {
        ArrayList<Product> products = new ProductDaoImpl().getAll();
        return products;
    }

    public static ArrayList<Product> showProductByName(Request req, Response res) {
        String productName = req.queryParams("search");
        System.out.println(productName);
        ArrayList<Product> products = new ProductDaoImpl().getByName(productName);
        return products;

    }

    public static Map<String, Object> renderProducts(List<Product> products) {
        Map<String, Object> params = new HashMap<>();
        params.put("products", products);
        params.put("categories", ProductCategoryController.showAvailableCategories());
        params.put("suppliers", SupplierController.showAvailableSuppliers());

        return params;
    }
}
