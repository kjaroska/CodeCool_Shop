package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {

    private Connection connection = Application.getApp().getConnection();

    @Override
    public void add(Product product) {
        String sql =
            "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) "
                + "VALUES ('" + product.getName() + "','" + product.getPriceValue() + "','" + product
                .getDescription() + "','" + product.getDefaultCurrency() + "','" + product
                .getProductCategory().getId() + "','" + product.getSupplier().getId() + "');";
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in addProduct: " + e.getMessage());
        }
    }

    @Override
    public Product find(int id) {
        Product newProduct = null;
        String query = "SELECT * from Products where id='" + id + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                ProductCategory productCategory = new ProductCategoryDaoImpl()
                        .find(idProductCategory);
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl().find(idSupplier);
                newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in product :" + e.getMessage());
        }
        return newProduct;
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public ArrayList<Product> getAll() {
        ArrayList<Product> listProducts = new ArrayList<>();
        String query = "SELECT * from Products";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

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
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in product :" + e.getMessage());
        }
        return listProducts;
    }

    @Override
    public ArrayList<Product> getBy(Supplier supplier) {
        ArrayList<Product> listProducts = new ArrayList<>();
        Integer supplierId = supplier.getId();
        String query = "SELECT * FROM Products WHERE id_supplier = '" + supplierId + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                ProductCategory productCategory = new ProductCategoryDaoImpl()
                        .find(idProductCategory);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in productDao :" + e.getMessage());
        }
        return listProducts;
    }

    public ArrayList<Product> getBy(ProductCategory productCategory) {
        ArrayList<Product> listProducts = new ArrayList<>();
        Integer productCategoryId = productCategory.getId();
        String query = "SELECT * from Products WHERE id_productCategory = '" + productCategoryId + "'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl().find(idSupplier);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in productDaoimpl :" + e.getMessage());
        }
        return listProducts;
    }

    public ArrayList<Product> getByName(String searchName) {
        ArrayList<Product> listProducts = new ArrayList<>();
        String query = "SELECT * from Products WHERE name LIKE '%" + searchName + "%'";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("price");
                String description = resultSet.getString("description");
                String currency = resultSet.getString("currency");
                Integer idProductCategory = resultSet.getInt("id_productCategory");
                Integer idSupplier = resultSet.getInt("id_supplier");
                Supplier supplier = new SupplierDaoImpl().find(idSupplier);
                ProductCategory productCategory = new ProductCategoryDaoImpl()
                        .find(idProductCategory);
                Product newProduct = new Product(id, name, price, description, currency,
                        productCategory, supplier);
                listProducts.add(newProduct);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + "in product :" + e.getMessage());
        }
        return listProducts;
    }
}
