package com.codecool.shop;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DatabaseManager {

    private Connection connection;
    private static DatabaseManager dbManager;

    public Connection getConnection() {
        return connection;
    }

    private void connectToDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
            if (this.connection != null) {
                DatabaseMetaData meta = this.connection.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    public static DatabaseManager getDbManager() {
        return dbManager;
    }
    }

    private void disconnectDb() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void executeStatements(List<String> statements) {
        dbManager.connectToDb();
        if (this.connection != null && statements != null) {
            try {
                for (String statement : statements) {
                    this.connection.createStatement().execute(statement);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getStatements(String fileName) {
        List<String> statements = new ArrayList<>();
        File scriptFile = new File(fileName);
        if (scriptFile.exists()) {
            try {
                statements = Files.readAllLines(scriptFile.toPath(), Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return statements;
    }

    public static void run(String argument) throws SQLException, ClassNotFoundException {
        dbManager = new DatabaseManager();
        dbManager.connectToDb();
        switch (argument) {
            case "--init-db":
                List<String> initStatements = dbManager.getStatements("init.sql");
                dbManager.executeStatements(initStatements);
                System.out.println("Database successfully initialized.");
            case "--migrate-db":
                List<String> migrateStatements = dbManager.getStatements("migrate.sql");
                dbManager.executeStatements(migrateStatements);
                System.out.println("Database successfully migrated");
        }
    }
}

