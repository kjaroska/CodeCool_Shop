import com.codecool.shop.Application;

/**
 * 'O for a muse of fire!'
 **/
class Main {

    public static void main(String[] args) {
        try {
            Application.run();
        } finally {
            final Thread mainThread = Thread.currentThread();
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
