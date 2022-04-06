package by.jwd.cafe.dao;

import by.jwd.cafe.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;

}
