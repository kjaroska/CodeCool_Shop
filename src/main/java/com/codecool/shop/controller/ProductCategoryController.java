package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDaoImpl;
import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
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

}
