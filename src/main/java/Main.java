import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.ProductDaoImpl;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ArrayList<Product> products = new ProductDaoImpl().getAll();
        Iterator<Product> productIterator = ProductController.getIterator(products);
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            System.out.println(product.toString());
        }

    }


}
