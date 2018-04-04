package javase08.task1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO {

    private static final String SQL_GET_USER_BY_ID = "SELECT id, name, surname FROM users HAVING id = ?";
    private static final String SQL_GET_ALL_USERS = "SELECT id, name, surname FROM users";
    private static final String SQL_DELETE_USERS_TABLE = "DROP TABLE users";
    private static final String SQL_ADD_USER = "INSERT INTO users (id, name, surname) VALUES (?, ?, ?)";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/Users?useSSL=false";

    public User getUserById(int userId) {
        User user = null;
        try (
                Connection con = DriverManager.getConnection(dbUrl, "root", "root");
                PreparedStatement preparedStatement = createPreparedStatementGetUser(con, userId);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot obtain user from DB", e);
        }
        return user;
    }

    public List<User> getAllUsers() {
        User user;
        List<User> userList = new ArrayList<>();
        try (
                Connection con = DriverManager.getConnection(dbUrl, "root", "root");
                PreparedStatement preparedStatement = con.prepareStatement(SQL_GET_ALL_USERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            if (resultSet.next()) {
                user = extractUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot obtain user from DB", e);
        }
        return userList;
    }

    public void addUser(int userId, String name, String surname){
        try (
                Connection con = DriverManager.getConnection(dbUrl, "root", "root");
                PreparedStatement preparedStatement = createPreparedStatementAddUser(con, userId, name, surname);

        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot add user to DB", e);
        }
    }

    public void deleteUsersTable(){
        try(
                Connection con = DriverManager.getConnection(dbUrl, "root", "root");
                PreparedStatement preparedStatement = con.prepareStatement(SQL_DELETE_USERS_TABLE);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot drop table", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        return user;
    }

    private PreparedStatement createPreparedStatementGetUser(Connection con, int userId) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_GET_USER_BY_ID);
        ps.setLong(1, userId);
        return ps;
    }

    private PreparedStatement createPreparedStatementAddUser(Connection con, int userId,
                                                             String name, String surname) throws SQLException {
        PreparedStatement ps = con.prepareStatement(SQL_ADD_USER);
        ps.setInt(1, userId);
        ps.setString(2, name);
        ps.setString(3, surname);
        return ps;
    }
}
