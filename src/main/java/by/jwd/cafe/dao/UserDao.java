package by.jwd.cafe.dao;

import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Interface implements functional of {@link BaseDao}.
 */
public interface UserDao extends BaseDao<Integer, User> {
    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByLogin(String login) throws DaoException;

    /**
     * Update password boolean.
     *
     * @param userId      the user id
     * @param newPassword the new password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updatePassword(int userId, String newPassword) throws DaoException;

    /**
     * Update user status boolean.
     *
     * @param userId        the user id
     * @param newUserStatus the new user status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean updateUserStatus(int userId, int newUserStatus) throws DaoException;

    /**
     *  Update user balance
     *  @param userId - user id
     *  @param amount - amount value which should write off the balance
     *  @return true - if balance was updated and false - if was not
     *  @throws DaoException - if request from database was failed
     */
    boolean updateBalance(int userId, BigDecimal amount) throws DaoException;

    /**
     * Find user with that email in database
     * @param email - user email
     * @return true - if user with that email was found and false - if was not
     * @throws DaoException - if request from database was failed
     */
    boolean isEmailExist(String email) throws DaoException;

    /**
     * Find user with that login in database
     * @param login - user email
     * @return true - if user with that email was found and false - if was not
     * @throws DaoException - if request from database was failed
     */
    boolean isLoginExist(String login) throws DaoException;

    /**
     * Find balance by user id.
     *
     * @param userId the user id
     * @return value of user's balance
     * @throws DaoException - if request from database was failed
     */
    BigDecimal findBalanceByUserId(int userId) throws DaoException;

    /**
     * Find loyalty points by user id.
     *
     * @param userId the user id
     * @return  value of user's loyalty points
     * @throws DaoException - if request from database was failed
     */
    BigDecimal findLoyaltyPointsByUserId(int userId) throws DaoException;
}
