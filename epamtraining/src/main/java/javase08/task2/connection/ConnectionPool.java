package javase08.task2.connection;

import io.vavr.CheckedFunction1;
import io.vavr.Function2;
import javase08.task2.exception.ConnectionPoolException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.io.Closeable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javase08.task2.connection.PropertyReader.getFromProperties;
import static lombok.AccessLevel.PRIVATE;

@ToString
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class ConnectionPool implements Supplier<Connection>, Closeable {

    final BlockingQueue<PooledConnection> connectionQueue;
    volatile boolean opened;

    @SneakyThrows
    public ConnectionPool() {

        ConnectionFactory connectionFactory = getFromProperties(ConnectionFactory.class, "db");

        Function<Connection, PooledConnection> pooledConnectionFactory = Function2.narrow(
                PooledConnection::new)
                .apply(this::closePooledConnection);

        int poolSize = connectionFactory.getPoolSize();
        connectionQueue = IntStream.range(0, poolSize)
                .mapToObj(__ -> connectionFactory.get())
                .map(pooledConnectionFactory)
                .collect(Collectors.toCollection(() -> new ArrayBlockingQueue<>(poolSize)));

        Function<String, Optional<String>> getFileAsString = Function2.narrow(
                ConnectionPool::getFileAsString)
                .apply(connectionFactory.getInitScriptsPath());

        try (Connection connection = get();
             val statement = connection.createStatement()) {
            statement.executeUpdate(
                    IntStream.iterate(1, operand -> operand + 1)
                            .mapToObj(String::valueOf)
                            .map(getFileAsString)
                            .takeWhile(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.joining()));
        }
    }

    public PooledConnection takeConnection() {
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(
                    "Error connecting to the data source.", e);
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

    @SneakyThrows
    private static Optional<String> getFileAsString(String initScriptsPath, String name) {
        val path = String.format("/%s/%s.sql", initScriptsPath, name);
        return Optional.ofNullable(ConnectionPool.class.getResource(path))
                .map(CheckedFunction1.narrow(URL::toURI).unchecked())
                .map(Paths::get)
                .map(CheckedFunction1.<Path, Stream<String>>narrow(Files::lines).unchecked())
                .map(stringStream -> stringStream.collect(Collectors.joining()));
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
