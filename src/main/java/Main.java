import com.codecool.shop.Application;
import com.codecool.shop.DatabaseManager;


/**
 * 'O for a muse of fire!'
 **/
class Main {
    public static void main(String[] args) {
        if (args.length != 0) {
            DatabaseManager.run(args);
        } else {
            try {
                Application.run();
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
}

