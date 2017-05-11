package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductCategoryDaoImpl implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {
        Statement stmt = Connector.getStatement(Connector.getConnection());
        String sql =
            "INSERT INTO ProductCategories (name, department, description) "
                + "VALUES ('" + category.getName() + "','" + category
                .getDepartment() + "','" + category.getDescription() + "');";
        try {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in add category: " + e.getMessage());
            System.exit(0);
        }

    }

    @Override
    public ProductCategory find(Integer idToFind) {
        String query =
            "SELECT id, name, department, description FROM ProductCategories WHERE id = '"
                + idToFind + "'";
        ResultSet resultSet = Connector.getQueryResult(query);
        ProductCategory newProductCategory = null;
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                newProductCategory = new ProductCategory(id, name, department, description);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in productcategory :" + e.getMessage());
            System.exit(0);
        }
      try {
        resultSet.close();
      } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
        ResultSet resultSet = Connector.getQueryResult(query);
        try {
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
        ResultSet resultSet = Connector.getQueryResult(query);
        Integer id = null;
        try {
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (Exception e) {
            System.err.println(
                e.getClass().getName() + " in product category Dao FindId :" + e.getMessage());
            System.exit(0);
        }
        try {
            resultSet.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return id;
    }
}
