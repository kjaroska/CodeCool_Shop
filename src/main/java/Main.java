import com.codecool.shop.Application;
import com.codecool.shop.DatabaseManager;

import java.sql.SQLException;


/**
 * 'O for a muse of fire!'
 **/
class Main {
    public static void main(String[] args) {
            try {
                Application.run(args);
            } finally {
                Thread mainThread = Thread.currentThread();
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Closing database connection...");
                    Application.getApp().disconnectDb();
                    System.out.println("Connection successfully closed.");
                    try {
                        mainThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }
        }
}

