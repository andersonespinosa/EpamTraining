package javase08.task2.db.dao;

import javase08.task1.exception.DatabaseException;
import javase08.task2.db.dao.mysql.MySqlDaoFactory;
import javase08.task2.exception.DaoException;

public abstract class DaoFactory {

    public static final int MY_SQL = 1;

    private static DaoFactory instance;

    public static synchronized DaoFactory getInstance(int name)
            throws DatabaseException {
        if (instance == null) {
            switch (name) {
                case 1:
                    instance = new MySqlDaoFactory();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        return instance;
    }

    public abstract BookDao getBookDao() throws DaoException;
}
