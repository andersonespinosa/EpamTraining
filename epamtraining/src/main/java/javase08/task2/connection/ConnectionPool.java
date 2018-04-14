package javase08.task2.connection;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;

import static lombok.AccessLevel.PRIVATE;

@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ConnectionPool {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Books?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "root";
    int poolSize = 10;

    final Queue<Connection> connectionQueue;

    public ConnectionPool() {
        ConnectionFactory connectionFactory = new ConnectionFactory(driver, url, user, pass);
        connectionQueue = new LinkedList<>();
        for (int i = 0; i < poolSize; i++) {
            Connection connection = connectionFactory.get();
            connectionQueue.add(connection);
        }
    }

    public Connection getConnection() {
        return connectionQueue.poll();

    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            connectionQueue.add(connection);
        }
    }
}
