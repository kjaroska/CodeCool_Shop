package com.codecool.shop.dao;

import java.sql.*;

public class Connector {

  private static Connection connection = null;

  private static Connection getConnection() {

    if (connection == null) {
      try {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
      } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
      }
    }
    return connection;
  }

  private static Statement getStatement(Connection connection) {
    Statement stmt = null;
    try {
            stmt = connection.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return stmt;
    }

  public static void closeConnection() {
    try {
      if (connection != null) {
        connection.close();
        connection = null;
      }
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  static ResultSet getQueryResult(String query) {
    Connection connection = getConnection();
    Statement stmt = getStatement(connection);
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
