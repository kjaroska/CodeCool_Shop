package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public void add(Supplier supplier) {
        Statement stmt = Connector.getStatement(Connector.getConnection());
        String sql =
            "INSERT INTO Suppliers (name, description) "
                + "VALUES ('" + supplier.getName() + "','" + supplier
                .getDescription() + "');";
        try {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in addsupplier: " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public Supplier find(Integer idToFind) {
        String query = "SELECT id, name, description FROM Suppliers WHERE id = '" + idToFind + "'";
        ResultSet resultSet = Connector.getQueryResult(query);
        Supplier newsupplier = null;
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                newsupplier = new Supplier(id, name, description);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in supplier :" + e.getMessage());
            System.exit(0);
        }
        return newsupplier;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public ArrayList<Supplier> getAll() {
        ArrayList<Supplier> listSuppliers = new ArrayList<>();
        String query = "SELECT * from Suppliers ORDER BY name";
        ResultSet resultSet = Connector.getQueryResult(query);
        try {
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

    public Integer findId(Supplier supplier) {
        String query =
            "SELECT id FROM Suppliers WHERE name = '" + supplier.getName() + "' AND description = '"
                + supplier.getDescription() + "'";
        ResultSet resultSet = Connector.getQueryResult(query);
        Integer id = null;
        try {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in supplierFindID :" + e.getMessage());
            System.exit(0);
        }
        return id;
    }
}
