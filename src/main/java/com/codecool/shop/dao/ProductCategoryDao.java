package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import java.util.List;

interface ProductCategoryDao {

    void add(ProductCategory category);

    ProductCategory find(Integer id);
    void remove(int id);

    List<ProductCategory> getAll();

}
