package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;
import java.util.Iterator;
import com.codecool.shop.ui.InputGetter;

public abstract class SupplierController {

    public static void showAvailableSuppliers() {
        ArrayList<Supplier> suppliers = new SupplierDaoImpl().getAll();
        for (Supplier supplier : suppliers) {
            Printer.printObject(supplier.toString());
        }
    }
    public static void productBySuppliers() {
        Printer.printObject("\nEnter Supplier's Id");
        Integer supplierId = InputGetter.getIntegerInput();
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
                Printer.printObject(e + ", No Supplier with given id");
                Printer.printObject("Insert proper id: ");
                supplierId = InputGetter.getIntegerInput();
            }
        }
        Iterator<Product> iterProducts = productsFromSupplier.iterator();
        Printer.printObject("\n'"+ supplier.getName() +"' products:\n");
        while (iterProducts.hasNext()) {
            Product singleProduct = iterProducts.next();
            Printer.printObject(singleProduct.toString());
        }
    }
}
