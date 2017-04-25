package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.view.Printer;
import java.util.ArrayList;

/**
 * Created by marek on 25.04.17.
 */
public class SupplierController {

    public static void showAvailableSuppliers() {
        ArrayList<Supplier> suppliers = new SupplierDaoImpl().getAll();
        for (Supplier supplier : suppliers) {
            Printer.printObject(supplier.toString());
        }
    }

}
