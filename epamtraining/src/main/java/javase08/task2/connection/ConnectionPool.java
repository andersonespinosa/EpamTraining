package javase08.task2.connection;

public class ConnectionPool {

    String driver;
    String url;
    String user;
    String password;

    public ConnectionPool(String url, String driver, int connections) {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.url = url;
        for (int i = 0; i < connections; i++) {
            availableConns.addElement(getConnection());
        }
    }

}
