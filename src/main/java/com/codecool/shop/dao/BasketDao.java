package com.codecool.shop.dao;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;

interface BasketDao {

    void add(Basket basket, String clientFullname, String clientAdress);

    Basket find(Integer id);

    void remove(Integer id);

    void update(Item item);
}
