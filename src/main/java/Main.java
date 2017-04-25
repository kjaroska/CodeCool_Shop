import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ProductController.showAvailableProducts();
    }
}
