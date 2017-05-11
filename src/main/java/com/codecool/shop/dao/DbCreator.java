package com.codecool.shop.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DbCreator {

    public static void checkArguments(String[] args) {
        for (String arg : args) {
            if (arg.equals("--init-db")) {
                try {
                    DbCreator.create();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (arg.equals("--migrate-db")) {
                try {
                    DbCreator.migrate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void create() throws IOException, SQLException {
        Connection connection = null;
        boolean can = false;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
            can = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            can = false;
        } catch (SQLException e) {
            e.printStackTrace();
            can = false;
        }
        if (can) {
            Statement statement = connection.createStatement();
            List<String> queries = new ArrayList<>();
            queries.add("DROP TABLE Items;");
            queries.add("DROP TABLE Orders;");
            queries.add("DROP TABLE Products;");
            queries.add("DROP TABLE ProductCategories;");
            queries.add("DROP TABLE Suppliers;");
            queries.add("CREATE TABLE Items\n"
                + "(\n"
                + "    Id INTEGER NOT NULL,\n"
                + "    BasketId INTEGER NOT NULL,\n"
                + "    IdProduct INTEGER NOT NULL,\n"
                + "    Quantity INTEGER NOT NULL\n"
                + ");\n");
            queries.add("CREATE TABLE Orders\n"
                + "(\n"
                + "    Id INTEGER NOT NULL,\n"
                + "    TotalPrice REAL,\n"
                + "    clientFullName TEXT,\n"
                + "    clientAdress INTEGER\n"
                + ");\n");
            queries.add("CREATE TABLE ProductCategories\n"
                + "(\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                + "    name TEXT,\n"
                + "    department TEXT,\n"
                + "    description TEXT\n"
                + ");\n");
            queries.add("CREATE TABLE Products\n"
                + "(\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                + "    name TEXT,\n"
                + "    price NUMERIC,\n"
                + "    description TEXT,\n"
                + "    currency TEXT,\n"
                + "    id_productCategory INTEGER,\n"
                + "    id_supplier INTEGER\n"
                + ");\n");
            queries.add("CREATE TABLE Suppliers\n"
                + "(\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
                + "    name TEXT,\n"
                + "    description TEXT\n"
                + ");\n");
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
            statement.clearBatch();

        }
    }

    private static void migrate() throws IOException, SQLException {
        Connection connection = null;
        boolean can = false;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
            can = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            can = false;
        } catch (SQLException e) {
            e.printStackTrace();
            can = false;
        }
        if (can) {
            Statement statement = connection.createStatement();
            List<String> queries = new ArrayList<>();
            queries.add(
                "INSERT INTO ProductCategories (name, department, description) VALUES ('sport', 'sport department', 'sport description');");
            queries.add(
                "INSERT INTO ProductCategories (name, department, description) VALUES ('clothing', 'clothing department', 'clothing description');");
            queries.add(
                "INSERT INTO ProductCategories (name, department, description) VALUES ('home', 'home department', 'home description');");
            queries.add(
                "INSERT INTO ProductCategories (name, department, description) VALUES ('rtv', 'rtv department', 'rtv description');");
            queries.add(
                "INSERT INTO ProductCategories (name, department, description) VALUES ('agd', 'agd department', 'agd description');");
            queries.add(
                "INSERT INTO ProductCategories (name, department, description) VALUES ('home', 'home department', 'home description');");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('jacket', 23, 'jacket description', 'PLN', 2, 2);");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('ball', 12, 'ball description', 'PLN', 1, 1);");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('flower', 34, 'flower description', 'PLN', 3, 3)");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('swimsuit', 23.5, 'swimsiut description', 'PLN', 1, 1);");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('bike', 1002.25, 'bike description', 'PLN', 1, 4);");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('helmet', 321, 'helmet description', 'PLN', 1, 4);");
            queries.add(
                "INSERT INTO Products (name, price, description, currency, id_productCategory, id_supplier) VALUES ('tv', 2030.2, 'tv description', 'PLN', 4, 5);");
            queries.add(
                "INSERT INTO Suppliers (name, description) VALUES ('Nike', 'Nike description');");
            queries.add(
                "INSERT INTO Suppliers (name, description) VALUES ('H&M', 'H&M description');");
            queries.add(
                "INSERT INTO Suppliers (name, description) VALUES ('Giant', 'Giant description');");
            queries.add(
                "INSERT INTO Suppliers (name, description) VALUES ('Ikea', 'Ikea description')");
            queries.add(
                "INSERT INTO Suppliers (name, description) VALUES ('Alfa', 'Alfa description');");
            queries.add(
                "INSERT INTO Suppliers (name, description) VALUES ('Ecopuff', 'Ecopuff description');");
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
            statement.clearBatch();

        }
    }
}
