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
                while (true){
                  ProductCategoryController.showAvailableCategories();
                  Integer option1 = inputGetter.getIntegerInput();
                  if (option1 == 0){
                      break;
                  }
                }
            } else if (option == 2) {
                while (true) {
                  SupplierController.showAvailableSuppliers();
                  Integer option2 = inputGetter.getIntegerInput();
                  if (option2 == 0){
                      break;
                  }
                }
            } else if (option == 3) {
                while (true) {
                  ProductController.showAvailableProducts();
                  Integer option3 = inputGetter.getIntegerInput();
                  if (option3 == 0){
                    break;
                  }
                }
            } else if (option == 4) {
                while (true) {
                  Printer.printBasket(basket.getProductList());
                  Integer option4 = inputGetter.getIntegerInput();
                  if (option4 == 0) {
                    break;
                  }
                }
            } else if (option == 0) {
                break;
            }
        }
    }
}
