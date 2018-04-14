package javase08.task2.db.dao.mysql;

import javase08.task2.connection.ConnectionFactory;
import javase08.task2.connection.ConnectionPool;
import javase08.task2.db.dao.BookDao;
import javase08.task2.db.model.Book;
import javase08.task2.exception.DaoException;
import javase08.task2.exception.Messages;
import lombok.extern.log4j.Log4j2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MySqlBookDao implements BookDao {
    private ConnectionPool connectionPool = new ConnectionPool();

    private static final String SQL_INSERT_BOOK = "INSERT INTO books (id, title, author, publishingHouse, pages)"
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_GET_BOOK_BY_ID = "SELECT id, title, author, publishingHouse, pages FROM books HAVING id = ?";

    private static final String SQL_GET_ALL_BOOKS = "SELECT id, title, author, publishingHouse, pages FROM books";

    private static final String SQL_GET_BOOK_BY_TITLE = "SELECT id, title, author, publishingHouse, pages FROM books HAVING title = ?";

    private static final String SQL_UPDATE_BOOK = "UPDATE books SET title = ?, author = ?, publishingHouse = ?, pages = ? WHERE id = ?";

    @Override
    public Book getBookById(Long id) throws DaoException {
        Book book = null;
        Connection con = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = con.prepareStatement(SQL_GET_BOOK_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = extractBook(resultSet);
            }
        } catch (SQLException e) {
            log.error(Messages.CANNOT_OBTAIN_BOOK, e);
            throw new DaoException(Messages.CANNOT_OBTAIN_BOOK, e);
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement);
            connectionPool.closeConnection(con);
        }
        return book;
    }

    public List<Book>getAllBooks() throws DaoException {
        Book book = null;
        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            con = connectionPool.getConnection();
            preparedStatement = con.prepareStatement(SQL_GET_ALL_BOOKS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = extractBook(resultSet);
                bookList.add(book);
            }
        } catch (SQLException e) {
            log.error(Messages.CANNOT_OBTAIN_BOOK, e);
            throw new DaoException(Messages.CANNOT_OBTAIN_BOOK, e);
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement);
            connectionPool.closeConnection(con);
        }
        return bookList;
    }

    @Override
    public List<Book> getBookByTitle(String title) throws DaoException {
        Book book = null;
        List<Book> bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            con = connectionPool.getConnection();
            preparedStatement = con.prepareStatement(SQL_GET_BOOK_BY_TITLE);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = extractBook(resultSet);
                bookList.add(book);
            }
        } catch (SQLException e) {
            log.error(Messages.CANNOT_OBTAIN_BOOK, e);
            throw new DaoException(Messages.CANNOT_OBTAIN_BOOK, e);
        } finally {
            ConnectionFactory.close(resultSet, preparedStatement);
            connectionPool.closeConnection(con);
        }
        return bookList;
    }

    @Override
    public void insertBook(Connection con, Book book) throws SQLException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = con.prepareStatement(SQL_INSERT_BOOK);
            int k = 1;
            preparedStatement.setLong(k++, book.getId());
            preparedStatement.setString(k++, book.getTitle());
            preparedStatement.setString(k++, book.getAuthor());
            preparedStatement.setString(k++, book.getPublishingHouse());
            preparedStatement.setInt(k, book.getPages());
            preparedStatement.executeUpdate();
        } finally {
            ConnectionFactory.close(preparedStatement);
        }

    }

    @Override
    public void updateBook(Connection con, Book book) throws SQLException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = con.prepareStatement(SQL_UPDATE_BOOK);
            int k = 1;
            preparedStatement.setString(k++, book.getTitle());
            preparedStatement.setString(k++, book.getAuthor());
            preparedStatement.setString(k++, book.getPublishingHouse());
            preparedStatement.setInt(k++, book.getPages());
            preparedStatement.setLong(k, book.getId());
            preparedStatement.executeUpdate();
        } finally {
            ConnectionFactory.close(preparedStatement);
        }

    }

    private Book extractBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong(1));
        book.setTitle(resultSet.getString(2));
        book.setAuthor(resultSet.getString(3));
        book.setPublishingHouse(resultSet.getString(4));
        book.setPages(resultSet.getInt(5));
        return book;
    }
}
