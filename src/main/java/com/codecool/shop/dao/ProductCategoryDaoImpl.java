package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

    private Connection connection = Application.getApp().getConnection();

    @Override
    public void add(ProductCategory category) {
    }


    @Override
    public ProductCategory find(Integer idToFind) {
        String query =
            "SELECT id, name, department, description FROM ProductCategories WHERE id = '"
                + idToFind + "'";
        ProductCategory newProductCategory = null;

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                newProductCategory = new ProductCategory(id, name, department, description);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in productcategory :" + e.getMessage());
        }
        return newProductCategory;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public ArrayList<ProductCategory> getAll() {
        ArrayList<ProductCategory> listCategories = new ArrayList<>();
        String query = "SELECT * from ProductCategories ORDER BY name";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                ProductCategory productCategory = new ProductCategory(id, name, department,
                    description);
                listCategories.add(productCategory);
            }
        } catch (Exception e) {
            System.err
                .println(e.getClass().getName() + " in ProductCategoryDao :" + e.getMessage());
            System.exit(0);
        }
        return listCategories;
    }
}
