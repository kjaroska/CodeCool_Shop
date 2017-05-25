package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import java.util.ArrayList;
import spark.Request;
import spark.Response;


public class ProductCategoryController {

    static ArrayList<ProductCategory> showAvailableCategories() {
        return new ProductCategoryDaoImpl().getAll();
    }

    public ArrayList<Product> showProductsFromCategory(Request req, Response res) {
        Integer categoryId = Integer.parseInt(req.params(":id"));
        ProductCategory productCategory;
        ArrayList<Product> productsFromCategory;
        while (true) {
            try {
                productCategory = new ProductCategoryDaoImpl().find(categoryId);
                productsFromCategory = new ProductDaoImpl().getBy(productCategory);
                if (productCategory != null) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return productsFromCategory;
    }


}
