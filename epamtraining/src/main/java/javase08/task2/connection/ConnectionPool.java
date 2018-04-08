package javase08.task2.connection;

import javase08.task2.exception.ConnectionPoolException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import static lombok.AccessLevel.PRIVATE;

@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ConnectionPool implements Supplier<Connection>, Closeable {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Books?useSSL=false";
    String user = "root";
    String password = "root";
    int poolSize = 10;

    final BlockingQueue<PooledConnection> connectionQueue;
    volatile boolean opened;

    @SneakyThrows
    public ConnectionPool() {

        ConnectionFactory connectionFactory = new ConnectionFactory(driver, url, user, password);
        connectionQueue = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            PooledConnection pooledConnection = (PooledConnection) connectionFactory.get();
            connectionQueue.add(pooledConnection);
        }

    }

    @Override
    public PooledConnection get() {
        return takeConnection();
    }

    @Override
    public void close() {
        opened = false;
        connectionQueue.forEach(PooledConnection::reallyClose);
    }

    private PooledConnection takeConnection() {
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(
                    "Error connecting to the data source.", e);
        }
    }

    @SneakyThrows
    private void closePooledConnection(PooledConnection connection) {
        if (opened) {
            if (connection.isClosed())
                throw new RuntimeException("Attempting to close closed connection.");
            if (connection.isReadOnly())
                connection.setReadOnly(false);
            if (!connectionQueue.offer(connection))
                throw new SQLException("Error allocating connection in the pool.");
        } else
            connection.reallyClose();
    }

}
