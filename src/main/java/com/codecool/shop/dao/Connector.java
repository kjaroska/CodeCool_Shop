package com.codecool.shop.dao;

/**
 * Created by marek on 25.04.17.
 */

import java.sql.*;

public class Connector {

    public static Statement connector() {
        Connection connection = null;
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

    public static ResultSet getQueryResult(String query) {
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
}
