package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;

/**
 * Created by marek on 25.04.17.
 */
public class ProductCategoryController {

    public static void showAvailableCategories() {
        ArrayList<ProductCategory> productCategories = new ProductCategoryDaoImpl().getAll();
        for (ProductCategory productCategory : productCategories) {
            Printer.printObject(productCategory.toString());
        }
    }

    public static void showProductsFromCategory() {
        System.out.println("Enter Product's Category ID:");
        Integer categoryId = InputGetter.getIntegerInput();
        ProductCategory productCategory = new ProductCategoryDaoImpl().find(categoryId);
        ArrayList<Product> productsFromCategory = new ProductDaoImpl().getBy(productCategory);
        java.util.Iterator<Product> iterProducts = new ProductIterator(productsFromCategory).Iterator();
        while (iterProducts.hasNext()) {
            Product singleProduct = iterProducts.next();
            Printer.printObject(singleProduct.toString());
        }
    }

}
