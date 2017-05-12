package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import java.util.ArrayList;
import spark.Request;
import spark.Response;

public class SupplierController {

    public ArrayList<Supplier> showAvailableSuppliers() {
        ArrayList<Supplier> suppliers = new SupplierDaoImpl().getAll();
        return suppliers;
    }

    public ArrayList<Product> productBySuppliers(Request req, Response res) {
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
