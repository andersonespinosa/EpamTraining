package javase08.task2.connection;

import javase08.task2.exception.Messages;
import lombok.*;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.function.Supplier;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PUBLIC;

@Log4j2
//@Value
@Getter(NONE)
@AllArgsConstructor
public class ConnectionFactory implements Supplier<Connection> {
    @NonFinal
    static boolean isNotInited = false;

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Books?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String pass = "root";

    /*@Getter(PUBLIC)
    int poolSize;*/

    @Override
    @SneakyThrows
    public Connection get() {
        if (isNotInited) {
            isNotInited = true;
            Class.forName(driver);
        }
        return DriverManager.getConnection(url, user, pass);
    }

    /*public static void close(PooledConnection con) {
        if (con != null) {
                con.close();
        } else
            log.info("PolledConnection in null");
    }*/

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

    public static void close(ResultSet resultSet, Statement statement) {
        close(resultSet);
        close(statement);
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