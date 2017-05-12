package com.codecool.shop.controller;


import com.codecool.shop.dao.ProductCategoryDaoImpl;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.dao.SupplierDaoImpl;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;

public class ProductController {
    private SupplierController supplierController;

    public ProductController() {
        this.supplierController = new SupplierController();
    }

    private static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }


    public ArrayList<Product> showAvailableProducts() {
        ArrayList<Product> products = new ProductDaoImpl().getAll();
        return products;
    }

    public ArrayList<Product> showProductByName(Request req, Response res) {
        String productName = req.queryParams("search");
        System.out.println(productName);
        ArrayList<Product> products = new ProductDaoImpl().getByName(productName);
        return products;

    }

    public Map<String, Object> renderProducts(List<Product> products) {
        Map<String, Object> params = new HashMap<>();
        params.put("products", products);
        params.put("categories", ProductCategoryController.showAvailableCategories());
        params.put("suppliers", supplierController.showAvailableSuppliers());

        return params;
    }

    public void addNewProduct(Request req, Response res) {
        String productName = req.queryParams("productName");
        String productDescription = req.queryParams("description");
        String productCategory = req.queryParams("category");
        String productSupplier = req.queryParams("supplier");
        String supplierDescription = req.queryParams("supplierDescription");
        String categoryDescription = req.queryParams("categoryDescription");
        String categoryDepartment = req.queryParams("categoryDepartment");
        Float productPrice = Float.parseFloat(req.queryParams("price"));
        if (productPrice < 0) {
            productPrice = productPrice * (-1);
        } else if (productPrice == 0.0) {
            productPrice += 10000000;
        }
        Supplier supplier = new Supplier(productSupplier, supplierDescription);
        new SupplierDaoImpl().add(supplier);
        Integer supplierId = new SupplierDaoImpl().findId(supplier);
        supplier.setId(supplierId);
        ProductCategory category = new ProductCategory(productCategory, categoryDepartment,
            categoryDescription);
        new ProductCategoryDaoImpl().add(category);
        Integer categoryId = new ProductCategoryDaoImpl().findId(category);
        category.setId(categoryId);
        Product product = new Product(productName, productPrice, productDescription, "PLN",
            category, supplier);
        new ProductDaoImpl().add(product);
    }
}
