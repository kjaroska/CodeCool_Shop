
import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.SummaryController;
import com.codecool.shop.dao.Connector;
import com.codecool.shop.model.Item;
import com.codecool.shop.view.Menu;
import com.codecool.shop.model.Basket;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Printer;
import com.codecool.shop.controller.SupplierController;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
      try {
        ArrayList<Item> newBasket = new ArrayList<>();
        Basket basket = new Basket(newBasket);
        Menu menu = new Menu();
        mainLoop:
        while (true) { // "mainLoop" is a label, that is later used to stop the while loop
          Printer.printMenu(menu.getMainMenu());
          Integer option = InputGetter.getIntegerInput();
          switch (option) {
            case 1:
              ProductCategoryController.showAvailableCategories();
              ProductCategoryController.showProductsFromCategory();
              basket = BasketController.addToBasket(basket);
              InputGetter.waitForEnter();
              continue;
            case 2:
              SupplierController.showAvailableSuppliers();
              SupplierController.productBySuppliers();
              InputGetter.waitForEnter();
              basket = BasketController.addToBasket(basket);
              continue;
            case 3:
              ProductController.showAvailableProducts();
              InputGetter.waitForEnter();
              basket = BasketController.addToBasket(basket);
              continue;
            case 4:
              Printer.printBasket(basket.getItemList());
              InputGetter.waitForEnter();
              continue;
            case 5:
              basket = BasketController.removeFromBasket(basket);
              continue;
            case 6:
              basket = BasketController.editBasket(basket);
              continue;
            case 7:
              SummaryController.summary(basket);
              continue;
            case 8:
              ProductController.showProductByName();
              InputGetter.waitForEnter();
              basket = BasketController.addToBasket(basket);
            case 0:
              break mainLoop;
            default:
              System.out.println("Invalid input. Try again.");
          }
        }
      } finally {
        Connector.closeConnection();
      }
    }
}
