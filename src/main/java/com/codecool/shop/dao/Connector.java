package com.codecool.shop.dao;

import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import java.sql.*;

class Connector {

    static Statement connector() {
        Connection connection;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return stmt;
    }

    static ResultSet getQueryResult(String query) {
        Statement stmt = Connector.connector();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    static void addBasketToDb(Basket basket) {
        Statement stmt = Connector.connector();
        String sql = "INSERT INTO Basket (Id, TotalPrice) "
            + "VALUES (" + basket.getId() + "," + basket.getTotalPrice() + ");";
        //I want this to write the strings passed in by the method, and to the table passed in by the method
        try {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in addBasketToDb: " + e.getMessage());
            System.exit(0);
        }
    }

    static void addItemToDb(Item item) {
        Statement stmt = Connector.connector();
        String sql = "INSERT INTO Items(Id,BasketId,IdProduct,Quantity) "
            + "VALUES (" + item.getId() + "," + item.getIdBasket() + "," + item.getProduct().getId()
            + "," + item.getQuantity() + ");";
        //I want this to write the strings passed in by the method, and to the table passed in by the method
        try {
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in addItemToDB: " + e.getMessage());
            System.exit(0);
        }
    }
}
