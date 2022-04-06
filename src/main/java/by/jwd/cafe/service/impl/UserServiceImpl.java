package by.jwd.cafe.service.impl;

import by.jwd.cafe.dao.impl.UserDaoImpl;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;

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
}
