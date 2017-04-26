
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
        while (true) {
            Printer.printMenu(menu.getMainMenu());
            Integer option = InputGetter.getIntegerInput();
            if (option == 1) {
                ProductCategoryController.showAvailableCategories();
                ProductCategoryController.showProductsFromCategory();
                basket = BasketController.addToBasket(basket);
                InputGetter.waitForEnter();
            } else if (option == 2) {
                SupplierController.showAvailableSuppliers();
                SupplierController.productBySuppliers();
                InputGetter.waitForEnter();
                basket = BasketController.addToBasket(basket);
            } else if (option == 3) {
                ProductController.showAvailableProducts();
                InputGetter.waitForEnter();
                basket = BasketController.addToBasket(basket);
            } else if (option == 4) {
                Printer.printBasket(basket.getItemList());
                InputGetter.waitForEnter();
            } else if (option == 5) {
                basket = BasketController.removeFromBasket(basket);
            } else if (option == 6) {
              basket = BasketController.editBasket(basket);
            } else if (option == 7) {
              SummaryController.summary(basket);
            } else if (option == 0) {
              break;
            }
        }
    }
}
