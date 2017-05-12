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
import org.omg.PortableServer.THREAD_POLICY_ID;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    private static Application app;
    private RenderingController renderingController;
    private BasketController basketController;
    private Connection connection;


    private Application() {
        this.basketController = new BasketController();
        this.renderingController = new RenderingController();
        this.connection = null;

    }

    public static void run() {
        System.out.println("Application starting...");
        try {
            app = new Application();
            app.connectToDb();
            System.out.println("Application started successfully.");
            app.routes();
        } catch (Exception e) {
            System.out.println("There was an error " + e + " when running application.");
            System.exit(0);
        }
    }

    private void connectToDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (!new File("shop.db").exists()) throw new SQLException("Database file no exist");
            this.connection = DriverManager.getConnection("jdbc:sqlite:shop.db");
        } catch (SQLException e) {
            e.printStackTrace();
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
                        ProductController.renderProducts(ProductController.showAvailableProducts()),
                    "product/index");
            }

        });

        get("/find", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(
                        ProductController.renderProducts(ProductController.showProductByName(req, res)),
                    "product/index");
            }
        });

        get("/Category/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(ProductController.renderProducts(
                        ProductCategoryController.showProductsFromCategory(req, res)),
                    "product/index");
            }
        });

        get("/Supplier/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(ProductController.renderProducts(
                        SupplierController.productBySuppliers(req, res)),
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
                ProductController.addNewProduct(req, res);
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
