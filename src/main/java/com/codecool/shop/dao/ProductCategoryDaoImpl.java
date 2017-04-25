package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by marek on 25.04.17.
 */
public class ProductCategoryDaoImpl implements ProductCategoryDao {

    @Override
    public void add(ProductCategory category) {

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
        return newProductCategory;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
