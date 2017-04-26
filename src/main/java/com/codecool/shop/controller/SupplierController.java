package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.Iterator;
import com.codecool.shop.ui.inputGetter;

public class SupplierController {

    public static void showAvailableSuppliers() {
        ArrayList<Supplier> suppliers = new SupplierDaoImpl().getAll();
        for (Supplier supplier : suppliers) {
            Printer.printObject(supplier.toString());
        }
    }
    public static void productBySuppliers() {
        Printer.printObject("\nEnter Supplier's Id");
        Integer supplierId = inputGetter.getIntegerInput();
        Supplier supplier = new SupplierDaoImpl().find(supplierId);
        ArrayList<Product> productsFromSupplier = new ProductDaoImpl().getBy(supplier);
        Iterator<Product> iterProducts = productsFromSupplier.iterator();
        Printer.printObject("\n'"+ supplier.getName() +"' products:\n");
        while (iterProducts.hasNext()) {
            Product singleProduct = iterProducts.next();
            Printer.printObject(singleProduct.toString());
        }
    }
}
