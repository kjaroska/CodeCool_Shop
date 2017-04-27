package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;


public abstract class ProductCategoryController {

    public static void showAvailableCategories() {
        ArrayList<ProductCategory> productCategories = new ProductCategoryDaoImpl().getAll();
        for (ProductCategory productCategory : productCategories) {
            Printer.printObject(productCategory.toString());
        }
    }

    public static void showProductsFromCategory() {
        Printer.printObject("Enter Product's Category ID:");
        Integer categoryId = InputGetter.getIntegerInput();
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
                Printer.printObject("No Supplier with given id");
                Printer.printObject("Insert proper id: ");
                categoryId = InputGetter.getIntegerInput();
            }
        }
        java.util.Iterator<Product> iterProducts = new ProductIterator(productsFromCategory).Iterator();
        while (iterProducts.hasNext()) {
            Product singleProduct = iterProducts.next();
            Printer.printObject(singleProduct.toString());
        }
    }

}
