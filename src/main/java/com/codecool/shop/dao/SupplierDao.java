package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

interface SupplierDao {

    void add(Supplier supplier);

    Supplier find(Integer id);
    void remove(int id);

    List<Supplier> getAll();
}
