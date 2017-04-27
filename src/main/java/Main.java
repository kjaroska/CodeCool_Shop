
import com.codecool.shop.controller.BasketController;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.controller.SummaryController;
import com.codecool.shop.controller.SupplierController;
import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import com.codecool.shop.ui.InputGetter;
import com.codecool.shop.view.Menu;
import com.codecool.shop.view.Printer;
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
              ProductController.showProductByName();
              basket = BasketController.addToBasket(basket);
              continue;
            case 2:
            ProductCategoryController.showAvailableCategories();
            ArrayList<Integer> productFromCategoryIDs;
            productFromCategoryIDs = ProductCategoryController.showProductsFromCategory();
            basket = BasketController.addToBasket(basket, productFromCategoryIDs);
            continue;
            case 3:
            SupplierController.showAvailableSuppliers();
            ArrayList<Integer> productFromSupplierIDs;
            productFromSupplierIDs = SupplierController.productBySuppliers();
            basket = BasketController.addToBasket(basket, productFromSupplierIDs);
            continue;
            case 4:
            ArrayList<Integer> productFromAllIDs;
            productFromAllIDs = ProductController.showAvailableProducts();
            basket = BasketController.addToBasket(basket, productFromAllIDs);
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
            ArrayList<Integer> productByNameID;
            productByNameID = ProductController.showProductByName();
            basket = BasketController.productExist(basket, productByNameID);
            InputGetter.waitForEnter();
            continue;
          case 0:
            break mainLoop;
          case 5:
              basket = BasketController.basketOptions(basket, menu);
          default:
              System.out.println("Try again");
            }
        }
    }
}
