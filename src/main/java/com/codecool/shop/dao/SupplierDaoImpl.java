package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SupplierDaoImpl implements SupplierDao {

    private Connection connection = Application.getApp().getConnection();

    @Override
    public void add(Supplier supplier) {
    }

    @Override
    public Supplier find(Integer idToFind) {
        String query = "SELECT id, name, description FROM Suppliers WHERE id = '" + idToFind + "'";
        Supplier newSupplier = null;
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                newSupplier = new Supplier(id, name, description);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in supplier :" + e.getMessage());
            System.exit(0);
        }
        return newSupplier;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public ArrayList<Supplier> getAll() {
        ArrayList<Supplier> listSuppliers = new ArrayList<>();
        String query = "SELECT * from Suppliers ORDER BY name";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Supplier supplier = new Supplier(id, name, description);
                listSuppliers.add(supplier);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in SupplierDaoImpl :" + e.getMessage());
            System.exit(0);
        }
        return listSuppliers;
    }
}
