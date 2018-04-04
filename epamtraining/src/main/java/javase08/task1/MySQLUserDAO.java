package javase08.task1;

import java.sql.*;

public class MySQLUserDAO {

    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users(id, name, surname) "
                    + "VALUES (?, ?, ?)";

    private static final String SQL_GET_USER_BY_ID = "SELECT id, name, surname FROM users HAVING id = ?";

    private static final String SQL_GET_ALL_USERS = "SELECT id, name, surname FROM users";

    private static final String DELETE_USERS_TABLE = "DROP TABLE users";


    public User getUserById(Long id) {
        User user = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try (Connection con =DriverManager.getConnection("")){

        } catch (SQLException e){
            //handle
        }
        User user1 = new User();
        return  user1;
    }
}
