package by.jwd.cafe.service.impl;

import by.jwd.cafe.dao.impl.UserDaoImpl;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl() {
    }
    public static UserServiceImpl getInstance() {
        return instance;
    }
    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        //validate login, password + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return match;
    }

    @Override
    public boolean createNewAccount(Map<String, String> userData) throws ServiceException {
        return false;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        return null;
    }

    @Override
    public boolean changePassword(Map<String, String> passwordData) throws ServiceException {
        return false;
    }

    @Override
    public boolean refillBalance(Map<String, String> balanceData) throws ServiceException {
        return false;
    }
}
