package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.Iterator;
import spark.Request;
import spark.Response;

public abstract class SupplierController {

    public static void showAvailableSuppliers() {
        ArrayList<Supplier> suppliers = new SupplierDaoImpl().getAll();
        for (Supplier supplier : suppliers) {
            Printer.printObject(supplier.toString());
        }
    }

    public static ArrayList<Product> productBySuppliers(Request req, Response res) {
        Integer supplierId = Integer.parseInt(req.params(":id"));
        Supplier supplier;
        ArrayList<Product> productsFromSupplier;
        while (true) {
            try {
                supplier = new SupplierDaoImpl().find(supplierId);
                productsFromSupplier = new ProductDaoImpl().getBy(supplier);
                if (supplier != null) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        return productsFromSupplier;
    }
}
