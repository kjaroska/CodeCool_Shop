package com.codecool.shop;


import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.RenderingController;
import com.codecool.shop.controller.SupplierController;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.Request;
import spark.Response;
import spark.Route;

public class Application {

    private static Application app;
    private RenderingController renderingController;
    private BasketController basketController;
    private ProductCategoryController productCategoryController;
    private ProductController productController;
    private SupplierController supplierController;
    private Connection connection;


    private Application() {
        this.basketController = new BasketController();
        this.renderingController = new RenderingController();
        this.productCategoryController = new ProductCategoryController();
        this.productController = new ProductController();
        this.supplierController = new SupplierController();
        this.connection = null;

    }

    public static void run(String[] args) {
        System.out.println("Application starting...");
        try {
            app = new Application();
            if (args.length > 0) {
                app.connectToDb(args[0]);
            } else {
                app.connectToDb("loadDatabase");
            }
            app.routes();
        } catch (Exception e) {
            System.out.println("There was an error " + e + " when running application.");
            System.exit(0);
        }
        System.out.println("Application started successfully.");
    }

    private void connectToDb() throws SQLException {
        if (!new File("shop.db").exists()) throw new SQLException("Database file no exist");
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void disconnectDb() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void routes() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");

        port(8888);

        get("/", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(
                        productController.renderProducts(productController.showAvailableProducts()),
                    "product/index");
            }

        });

        get("/find", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(
                        productController.renderProducts(productController.showProductByName(req, res)),
                    "product/index");
            }
        });

        get("/Category/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(productController.renderProducts(
                        productCategoryController.showProductsFromCategory(req, res)),
                    "product/index");
            }
        });

        get("/Supplier/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(productController.renderProducts(
                    supplierController.productBySuppliers(req, res)),
                    "product/index");
            }
        });
        get("/basket", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(basketController.renderProducts(
                        basketController.getBasket()),
                    "product/basket");
            }
        });

        post("/basket/add", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                basketController.setBasket(
                    basketController.addToBasket(basketController.getBasket(), req));
                res.redirect("/");
                return "";
            }
        });

        post("/basket/remove", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                basketController.setBasket(
                    basketController.removeFromBasket(basketController.getBasket(), req));
                res.redirect("/basket");
                return "";
            }
        });

        post("/item/edit", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                basketController.setBasket(
                    basketController.editBasket(basketController.getBasket(), req));
                res.redirect("/basket");
                return "";
            }
        });
      
        get("/user", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "user/userform");
            }
        });
      
        get("/payment", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "user/payment");
            }
        });
        
        get("/confirmation", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "user/confirmation");
            }
        });
      
        get("/product/new", new Route() {
           @Override
            public Object handle(Request req, Response res) {
                return renderingController.render(basketController.renderProducts(
                    basketController.getBasket()),
                    "product/new");
            }
        });

        post("/product/new", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                productController.addNewProduct(req, res);
                res.redirect("/");
                return "";
            }
        });
    }

    public static Application getApp() {
        return app;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
