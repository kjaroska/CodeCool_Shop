package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import java.util.Iterator;

public class BasketDaoImpl implements BasketDao {

    @Override
    public void add(Basket basket) {
        Connector.addBasketToDb(basket);
        Iterator<Item> iterBasket = basket.getIterator();
        while (iterBasket.hasNext()) {
            Item item = iterBasket.next();
            Connector.addItemToDb(item);
        }
    }

    @Override
    public Basket find(Integer id) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public void update(Item item) {

    }
}
