import com.codecool.shop.Application;
import com.codecool.shop.dao.DbCreator;

/**
 * 'O for a muse of fire!'
 **/
class Main {
  public static void main(String[] args) {
      DbCreator.checkArguments(args);
      Application.run();
  }
}
