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

    RenderingController renderingController;
    BasketController basketController;
    public Application() {
        this.basketController = new BasketController();
        this.renderingController = new RenderingController();
        run();
    }

    public void run() {
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");

        port(8888);

        get("/product/all", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return renderingController.render(
                        ProductController.renderProducts(ProductController.showAvailableProducts()),
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
                res.redirect("/product/all");
                return "";
            }
        });
    }
}
