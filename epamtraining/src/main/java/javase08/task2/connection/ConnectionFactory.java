package javase08.task2.connection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.function.Supplier;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PUBLIC;

@Value
@Getter(NONE)
public class ConnectionFactory implements Supplier<Connection> {
    @NonFinal
    static boolean isNotInited;

    String driver;
    String url;
    String user;
    String password;

    @Getter(PUBLIC)
    int poolSize;

    @Getter(PUBLIC)
    String initScriptsPath;

    @Override
    @SneakyThrows
    public Connection get() {
        if (isNotInited) {
            isNotInited = true;
            Class.forName(driver);
        }
        return DriverManager.getConnection(url, user, password);
    }
}