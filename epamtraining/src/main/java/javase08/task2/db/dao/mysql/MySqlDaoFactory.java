package javase08.task2.db.dao.mysql;

import javase08.task2.db.dao.BookDao;
import javase08.task2.db.dao.DaoFactory;
import javase08.task2.exception.DaoException;
import javase08.task2.exception.Messages;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class MySqlDaoFactory extends DaoFactory {

    private static DataSource getDataSource() throws SQLException, DaoException {
        DataSource dataSource = null;
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext
                    .lookup("java:/comp/env/jdbc/books");
        } catch (NamingException e) {
            log.error(Messages.CANNOT_OBTAIN_DATA_SOURSE, e);
            throw new DaoException(Messages.CANNOT_OBTAIN_DATA_SOURSE, e);
        }
        return dataSource;
    }

    public static Connection getConnection() throws DaoException {
        Connection con;
        try {
            con = getDataSource().getConnection();
        } catch (SQLException e) {
            log.error(Messages.CANNOT_OBTAIN_CONNECTION, e);
            throw new DaoException(Messages.CANNOT_OBTAIN_CONNECTION, e);
        }
        return con;
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, e);
            }
        }
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

    public static void close(Connection con, Statement statement, ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(con);
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

    @Override
    public BookDao getBookDao() throws DaoException {
        return new MySqlBookDao();
    }
}
