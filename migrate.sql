CREATE TABLE IF NOT EXISTS Orders (Id INTEGER NOT NULL, TotalPrice REAL, clientFullName TEXT, clientAdress INTEGER);
CREATE TABLE IF NOT EXISTS ProductCategories(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name TEXT, department TEXT, description TEXT);
CREATE TABLE IF NOT EXISTS Products(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name TEXT, price NUMERIC, description TEXT, currency TEXT, id_productCategory INTEGER, id_supplier INTEGER);
CREATE TABLE IF NOT EXISTS Suppliers(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, name TEXT, description TEXT);
CREATE TABLE IF NOT EXISTS Items (Id INTEGER NOT NULL, BasketId INTEGER NOT NULL, IdProduct INTEGER NOT NULL, Quantity INTEGER NOT NULL);