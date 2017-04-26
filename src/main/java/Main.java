import com.codecool.shop.view.Menu;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Basket;
import com.codecool.shop.ui.inputGetter;
import com.codecool.shop.view.Printer;
import com.codecool.shop.controller.SupplierController;
import com.codecool.shop.controller.ProductCategoryController;
import com.codecool.shop.controller.ProductController;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Product> newBasket = new ArrayList<>();
        Basket basket = new Basket(newBasket);
        Menu menu = new Menu();
        while (true) {
            Printer.printMenu(menu.getMainMenu());
            Integer option = inputGetter.getIntegerInput();
            if (option == 1) {
                SupplierController.showAvailableSuppliers();
            } else if (option == 2) {
                ProductCategoryController.showAvailableCategories();
            } else if (option == 3) {
                ProductController.showAvailableProducts();
            } else if (option == 4) {
                Printer.printBasket(basket.getProductList());
            } else if (option == 0) {
                System.exit(0);
            }
        }
    }
}
