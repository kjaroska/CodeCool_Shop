package com.codecool.shop;


import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.SupplierController;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

/**
 * Created by marek on 08.05.17.
 */
public class Application {

    BasketController basketController;
    public Application() {

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
                return new ThymeleafTemplateEngine()
                    .render(new ModelAndView(
                        ProductController.renderProducts(ProductController.showAvailableProducts()),
                        "product/index"));
            }
        });

        get("/Category/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return new ThymeleafTemplateEngine()
                    .render(new ModelAndView(ProductController.renderProducts(
                        ProductCategoryController.showProductsFromCategory(req, res)),
                        "product/index"));
            }
        });

        get("/Supplier/:id", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                // process request
                return new ThymeleafTemplateEngine()
                    .render(new ModelAndView(ProductController.renderProducts(
                        SupplierController.productBySuppliers(req, res)),
                        "product/index"));
            }
        });

        post("/basket/add", new Route() {
            @Override
            public Object handle(Request req, Response res) {
                Integer productId = Integer.parseInt(req.queryParams("productId"));
                basketController.addToBasket(basketController.getBasket(), productId);
                res.redirect("/product/all");
                return "";
            }
        });
    }
}
