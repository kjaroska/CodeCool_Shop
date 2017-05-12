package com.codecool.shop.dao;

import com.codecool.shop.Application;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Iterator;

public class BasketDaoImpl implements BasketDao {

    private Connection connection = Application.getApp().getConnection();

    @Override
    public void add(Basket basket, String FullName, String Address) {
        addBasketToDb(basket, FullName, Address);
        Iterator<Item> iterBasket = basket.getIterator();
        while (iterBasket.hasNext()) {
            Item item = iterBasket.next();
            addItemToDb(item);
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

    private void addBasketToDb(Basket basket, String FullName, String Address) {
        FullName = FullName.replaceAll("\\r|\\n", "");
        Address = Address.replaceAll("\\r|\\n", "");
        String sql = "INSERT INTO Orders (Id, TotalPrice) "
                + "VALUES (" + basket.getId() + "," + basket.getTotalPrice() + ");";
        //I want this to write the strings passed in by the method, and to the table passed in by the method
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in addBasketToDb: " + e.getMessage());
            System.exit(0);
        }
    }

    private void addItemToDb(Item item) {
        String sql = "INSERT INTO Items(Id,BasketId,IdProduct,Quantity) "
                + "VALUES (" + item.getId() + "," + item.getIdBasket() + "," + item.getProduct().getId()
                + "," + item.getQuantity() + ");";
        //I want this to write the strings passed in by the method, and to the table passed in by the method
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in addItemToDB: " + e.getMessage());
            System.exit(0);
        }
    }
}
