package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;


import com.codecool.shop.model.Product;
import java.util.*;

public class ProductController {

    public static java.util.Iterator<Product> getIterator(ArrayList<Product> productList) {
        return new ProductIterator(productList).Iterator();
    }

}
