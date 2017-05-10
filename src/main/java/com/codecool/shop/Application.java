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
import spark.Request;
import spark.Response;
import spark.Route;

public class Application {

    private static Application app;
    RenderingController renderingController;
    BasketController basketController;
    public Application() {
        this.basketController = new BasketController();
        this.renderingController = new RenderingController();

    }

    public static void run() {
        System.out.println("Applications starting...");
        try {
            System.out.println("Applications started succesfully....");
            Application app = new Application();
            app.routes();
        } catch (Exception e) {
            System.out.println("There was an error " + e + " when running application.");
            System.exit(0);
        }
    }

    public void routes() {
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
    }

    public static Application getApp() {
        return app;
    }
}
