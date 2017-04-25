package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by marek on 25.04.17.
 */
public class SupplierDaoImpl implements SupplierDao {

    @Override
    public void add(Supplier supplier) {

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
    public List<Supplier> getAll() {
        return null;
    }
}
