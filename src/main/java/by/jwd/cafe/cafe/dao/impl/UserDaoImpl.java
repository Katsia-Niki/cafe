package by.jwd.cafe.dao.impl;

import by.jwd.cafe.dao.UserDao;
import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.dao.mapper.impl.UserMapper;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    static Logger logger = LogManager.getLogger();
    private static final String INSERT_USER = """
            INSERT INTO cafe.users (login, password, first_name, last_name, email, balance, loyalty_points, is_active,
            role_id) values (?,?,?,?,?,?,?,?,?)""";
    private static final String UPDATE_BALANCE = """
            UPDATE cafe.users SET balance=balance+? WHERE user_id=?""";
    private static final String CHANGE_PASSWORD = """
            UPDATE cafe.users SET password=password? WHERE user_id=?""";
    private static final String UPDATE_USER = """
            UPDATE cafe.users SET first_name=?, last_name=?, balance=?, loyalty_points=?, is_active=?, 
            role_id=? WHERE user_id=?""";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = """
            SELECT user_id, login, password, first_name, last_name, email, balance, loyalty_points, 
            is_active, role_id 
            FROM cafe.users 
            WHERE login=? AND password=?""";
    private static final String SELECT_USER_BY_ID = """
            SELECT user_id, login, first_name, last_name, email, balance, loyalty_points, 
            is_active, role_name 
            FROM cafe.users JOIN users_role ON users_role.users_role_role_id=users.role_id 
            WHERE user_id=?""";
    private static final String SELECT_USER_BY_LOGIN = """
            SELECT user_id, login, first_name, last_name, email, balance, loyalty_points, 
            is_active, role_name 
            FROM cafe.users JOIN users_role ON users_role.users_role_role_id=users.role_id 
            WHERE login=?"""; //todo убирала пароль
    private static final String SELECT_USER_BY_EMAIL = """
            SELECT user_id, login, first_name, last_name, email, balance, loyalty_points, 
            is_active, role_name 
            FROM cafe.users JOIN users_role ON users_role.users_role_role_id=users.role_id 
            WHERE email=?"""; //todo убирала пароль
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static String SELECT_ALL_USERS = "SELECT * FROM cafe.users"; //todo write all columns (instead of *)
    private static UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        Mapper<User> mapper = UserMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<User> optionalUser = mapper.map(resultSet);
                if (optionalUser.isPresent()) {
                    userList.add(optionalUser.get());
                }
            }
            logger.log(Level.INFO, "List: " + userList);
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return userList;
    }

    @Override
    public boolean add(User user) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setBigDecimal(6, user.getBalance());
            statement.setBigDecimal(7, user.getLoyaltyPoints());
            statement.setBoolean(8, user.isActive());
            statement.setInt(9, user.getRole().ordinal());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL request add user to cafe.users was failed.", e);
            throw new DaoException("SQL request add user to cafe.users was failed.", e);
        }
        return rows == 1;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        return false;                                     //fixme
    }

    @Override
    public boolean update(User user) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setBigDecimal(3, user.getBalance());
            statement.setBigDecimal(4, user.getLoyaltyPoints());
            statement.setBoolean(5, user.isActive());
            statement.setString(6, user.getRole().toString());
            statement.setInt(7, user.getUserId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL request changePassword from table cafe.users was failed.", e);
            throw new DaoException("SQL request changePassword from table cafe.users was failed.", e);
        }
        return rows == 1;
    }

    @Override
    public Optional<User> findEntityById(Integer userId) throws DaoException {
        Optional<User> optionalUser;
        Mapper<User> mapper = UserMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()){
                optionalUser = mapper.map(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQL request findUserById from cafe was failed.", e);
            throw new DaoException("SQL request findUserById from cafe was failed.", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        Mapper<User> mapper = UserMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                optionalUser = mapper.map(resultSet).stream().findFirst();
            }
        } catch (SQLException e) {
            logger.error("SQL request findUserByLoginAndPassword from cafe was failed.", e);
            throw new DaoException("SQL request findUserByLoginAndPassword from cafe was failed.", e);
        }
        return optionalUser;
    }


    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {//fixme check whether I need this method
        Optional<User> optionalUser;
        Mapper<User> mapper = UserMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                optionalUser = mapper.map(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQL request findUserByLogin from cafe was failed.", e);
            throw new DaoException("SQL request findUserByLogin from cafe was failed.", e);
        }
        return optionalUser;
    }

    @Override
    public boolean changePassword(int userId, String newPassword) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setInt(2, userId);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL request changePassword from table cafe.users was failed.", e);
            throw new DaoException("SQL request changePassword from table cafe.users was failed.", e);
        }
        return rows == 1;
    }

    @Override
    public boolean updateBalance(int userId, BigDecimal amount) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE)) {
            statement.setBigDecimal(1, amount);
            statement.setInt(2, userId);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQL request updateBalance from table cafe.users was failed.", e);
            throw new DaoException("SQL request updateBalance from table cafe.users was failed.", e);
        }
        return rows == 1;
    }

    @Override
    public boolean isEmailExist(String email) throws DaoException {
        boolean isExist = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    isExist = true;
                }
            }
        } catch (SQLException e) {
            logger.error("SQL request isEmailExist from table cafe.users was failed.", e);
            throw new DaoException("SQL request isEmailExist from table cafe.users was failed.", e);
        }
        return isExist;
    }

    @Override
    public boolean isLoginExist(String login) throws DaoException {
        boolean isExist = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    isExist = true;
                }
            }
        } catch (SQLException e) {
            logger.error("SQL request isLoginExist from table cafe.users was failed.", e);
            throw new DaoException("SQL request isLoginExist from table cafe.users was failed.", e);
        }
        return isExist;
    }
}
