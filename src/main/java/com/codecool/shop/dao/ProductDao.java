package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.T;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {

    void add(T product);

    T find(int id);
    void remove(int id);

    List<T> getAll();

    List<T> getBy(Supplier supplier);

    List<T> getBy(ProductCategory productCategory);

}
