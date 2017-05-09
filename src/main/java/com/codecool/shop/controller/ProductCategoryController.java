package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import spark.Request;
import spark.Response;


public abstract class ProductCategoryController {

    public static void showAvailableCategories() {
        ArrayList<ProductCategory> productCategories = new ProductCategoryDaoImpl().getAll();
        for (ProductCategory productCategory : productCategories) {
            Printer.printObject(productCategory.toString());
        }
    }

    public static ArrayList<Product> showProductsFromCategory(Request req, Response res) {
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
            }
        }
        return productsFromCategory;
    }

}
