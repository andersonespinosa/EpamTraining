package javase08.task1;

import javase08.task1.exception.DatabaseException;
import javase08.task1.exception.Messages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao {

    private static final String SQL_GET_USER_BY_ID = "SELECT id, name, surname FROM users HAVING id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT id, name, surname FROM users";
    private static final String SQL_DELETE_USERS_TABLE = "DROP TABLE users";
    private static final String SQL_ADD_USER = "INSERT INTO users (id, name, surname) VALUES (?, ?, ?)";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Users?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    public User getUserById(int userId) throws DatabaseException {
        User user = null;
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement preparedStatement = createPreparedStatementGetUser(con, userId);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DatabaseException(Messages.CANNOT_OBTAIN_USER, e);
        }
        return user;
    }

    public List<User> getAllUsers() throws DatabaseException {
        List<User> userList = new ArrayList<>();
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement preparedStatement = con.prepareStatement(SQL_GET_ALL_USERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                User user;
                user = extractUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new DatabaseException(Messages.CANNOT_OBTAIN_USER, e);
        }
        return userList;
    }

    public void addUser(User userToAdd) throws DatabaseException {
        int userId = userToAdd.getId();
        String name = userToAdd.getName();
        String surname = userToAdd.getSurname();
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement preparedStatement = createPreparedStatementAddUser(con, userToAdd)

        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(Messages.CANNOT_INSERT_USER, e);
        }
    }

    private void deleteUsersTable() throws DatabaseException {
        try (
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement preparedStatement = con.prepareStatement(SQL_DELETE_USERS_TABLE);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(Messages.CANNOT_DROP_TABLE, e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        return user;
    }

    private PreparedStatement createPreparedStatementGetUser(Connection con, int userId) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_GET_USER_BY_ID);
        ps.setLong(1, userId);
        return ps;
    }

    private PreparedStatement createPreparedStatementAddUser(Connection con, User user) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_ADD_USER);
        int userId = user.getId();
        String name = user.getName();
        String surname = user.getSurname();
        ps.setInt(1, userId);
        ps.setString(2, name);
        ps.setString(3, surname);
        return ps;
    }
}
