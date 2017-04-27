
import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.SummaryController;
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
        ArrayList<Item> newBasket = new ArrayList<>();
        Basket basket = new Basket(newBasket);
        Menu menu = new Menu();
      mainLoop:
      while (true) { // "mainLoop" is a label, that is later used to stop the while loop
            Printer.printMenu(menu.getMainMenu());
            Integer option = InputGetter.getIntegerInput();
        switch (option) {
          case 1:
            continue;
          case 2:
            ProductCategoryController.showAvailableCategories();
            ProductCategoryController.showProductsFromCategory();
            basket = BasketController.addToBasket(basket);
            InputGetter.waitForEnter();
            continue;
          case 3:
            SupplierController.showAvailableSuppliers();
            SupplierController.productBySuppliers();
            basket = BasketController.addToBasket(basket);
            continue;
          case 4:
            ProductController.showAvailableProducts();
            basket = BasketController.addToBasket(basket);
            continue;
          case 0:
            break mainLoop;
          case 5:
            basketLoop:
            //loop for basket menu
            while (true) {
              Printer.printBasket(basket.getItemList());
              Printer.printMenu(menu.getBasketMenu());
              Integer basketOption = InputGetter.getIntegerInput();
              switch (basketOption) {
                case 1:
                  basket = BasketController.removeFromBasket(basket);
                  continue;
                case 2:
                  basket = BasketController.editBasket(basket);
                  continue;
                case 3:
                  SummaryController.summary(basket);
                  continue;
                case 0:
                  break basketLoop;
                default:
                  System.out.println("Invalid input. Try again.");
              }
            }
          default:
            System.out.println("Invalid input. Try again.");
            }
        }
    }
}
