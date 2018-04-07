package javase08.task2.connection;

import javase08.task2.exception.Messages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.function.Supplier;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PUBLIC;

@Log4j2
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

    public static void close(PooledConnection con) {
        if (con != null) {
                con.close();
        } else
            log.info("PolledConnection in null");
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, e);
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, e);
            }
        }
    }

    public static void close(ResultSet resultSet, Statement statement, PooledConnection con) {
        close(resultSet);
        close(statement);
        con.close();
    }

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                log.error("Cannot rollback transaction", e);
            }
        }
    }
}