
import com.codecool.shop.controller.BasketController;
import com.codecool.shop.model.Item;
import com.codecool.shop.view.Menu;
import com.codecool.shop.model.Product;
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
            Printer.printMenu();
            Integer option = InputGetter.getIntegerInput();
            if (option == 1) {
                ProductCategoryController.showAvailableCategories();
                basket = BasketController.addToBasket(basket);
                inputGetter.waitForEnter();
            } else if (option == 2) {
                SupplierController.showAvailableSuppliers();
                inputGetter.waitForEnter();
                basket = BasketController.addToBasket(basket);
            } else if (option == 3) {
                ProductController.showAvailableProducts();
                inputGetter.waitForEnter();
                basket = BasketController.addToBasket(basket);
            } else if (option == 4) {
                Printer.printBasket(basket.getItemList());
                inputGetter.waitForEnter();
            } else if (option == 0) {
                break;
            }
        }
    }
}
