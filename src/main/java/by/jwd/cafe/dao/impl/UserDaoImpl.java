package by.jwd.cafe.dao.impl;

import by.jwd.cafe.dao.BaseDao;
import by.jwd.cafe.dao.UserDao;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDaoImpl  implements BaseDao<User>,  UserDao {
    static Logger logger = LogManager.getLogger();
    private static final String INSERT_USER = "INSERT INTO cafe (login, password) value (?, ?)";
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static String SELECT_ALL = "SELECT * FROM cafe";
    private static UserDaoImpl instance = new UserDaoImpl();
    private UserDaoImpl() {
    }
    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        //connection pool
        return null;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery(); //добавить try
            String passFromDb;
            if (resultSet.next()) {
            passFromDb = resultSet.getString(1);
            match = password.equals(passFromDb);
            }
        } catch (SQLException e) {
            logger.error("SQL request authenticate from cafe was failed", e);
            throw new DaoException(e);
        }
        return match;
    }
}
