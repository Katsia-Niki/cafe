package by.jwd.cafe.dao;

import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends BaseDao<Integer, User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    Optional<User> findUserByLogin(String login) throws DaoException;
    boolean changePassword(int userId, String newPassword) throws DaoException;
    boolean updateBalance(int userId, BigDecimal amount) throws DaoException;
    boolean isEmailExist(String email) throws DaoException;
    boolean isLoginExist(String login) throws DaoException;
}
