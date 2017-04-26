
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
        ArrayList<Product> newBasket = new ArrayList<>();
        Basket basket = new Basket(newBasket);
        while (true) {
            Printer.printMenu();
            Integer option = InputGetter.getIntegerInput();
            if (option == 1) {
                ProductCategoryController.showAvailableCategories();
                inputGetter.waitForEnter();
            } else if (option == 2) {
                SupplierController.showAvailableSuppliers();
                inputGetter.waitForEnter();
            } else if (option == 3) {
                ProductController.showAvailableProducts();
                inputGetter.waitForEnter();
            } else if (option == 4) {
                Printer.printBasket(basket.getProductList());
                inputGetter.waitForEnter();
            } else if (option == 0) {
                System.exit(0);
            }
        }
    }
}
