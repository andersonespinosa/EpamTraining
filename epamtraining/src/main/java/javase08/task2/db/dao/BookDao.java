package javase08.task2.db.dao;

import javase08.task2.connection.PooledConnection;
import javase08.task2.db.model.Book;
import javase08.task2.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {

    Book getBookById(Long id) throws DaoException;

    List<Book> getBookByTitle(String title) throws DaoException;

    void insertBook(Connection con, Book book) throws SQLException;

    void updateBook(Connection con, Book book) throws SQLException;

}
