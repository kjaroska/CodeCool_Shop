package com.codecool.shop.dao;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;

public interface BasketDao {

    void add(Basket basket);

    Basket find(Integer id);

    void remove(Integer id);

    void update(Item item);
}
