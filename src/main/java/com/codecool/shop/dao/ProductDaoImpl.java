package com.codecool.shop.dao;

import com.codecool.shop.model.T;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marek on 25.04.17.
 */
public class ProductDaoImpl implements ProductDao {

    @Override
    public void add(T product) {

    }

    @Override
    public T find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public ArrayList<T> getAll() {
        ArrayList<T> listProducts = new ArrayList<>();
        String query = "SELECT * from Products";
        ResultSet resultSet = Connector.getQueryResult(query);
        try {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                ProductCategory productCategory = new ProductCategoryDaoImpl()
                    .find(idProductCategory);
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl().find(idSupplier);
                T newProduct = new T(id, name, price, description, currency,
                    productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in product :" + e.getMessage());
            System.exit(0);
        }
        return listProducts;
    }

    @Override
    public List<T> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<T> getBy(ProductCategory productCategory) {
        return null;
    }
}
