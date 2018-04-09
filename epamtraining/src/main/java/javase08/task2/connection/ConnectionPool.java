package javase08.task2.connection;

import javase08.task2.exception.ConnectionPoolException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

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
        connectionQueue = new LinkedList<Connection>();
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

    /*@Override
    public void close() {
        opened = false;
        connectionQueue.forEach(PooledConnection::reallyClose);
    }*/

    /*@SneakyThrows
    private void closePooledConnection(PooledConnection connection) {
        if (opened) {
                throw new RuntimeException("Attempting to close closed connection.");
            if (connection.isReadOnly())
                connection.setReadOnly(false);
            if (!connectionQueue.offer(connection))
                throw new SQLException("Error allocating connection in the pool.");
        } else
            connection.reallyClose();
    }*/
}
