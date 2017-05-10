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
        String sql =
            "INSERT INTO ProductCategories (name, department, description) "
                + "VALUES ('" + category.getName() + "','" + category
                .getDepartment() + "','" + category.getDescription() + "');";
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in add category: " + e.getMessage());
        }
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

    public Integer findId(ProductCategory category) {
        String query =
            "SELECT id FROM ProductCategories WHERE name = '"
                + category.getName() + "' AND description = '" + category.getDescription() + "'";
        Integer id = null;
        try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            System.err.println(
                e.getClass().getName() + " in product category Dao FindId :" + e.getMessage());
        }
        return id;
    }
}
