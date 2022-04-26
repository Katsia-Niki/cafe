package by.jwd.cafe.service.impl;

import by.jwd.cafe.dao.UserDao;
import by.jwd.cafe.dao.impl.UserDaoImpl;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.util.PasswordEncryptor;
import by.jwd.cafe.validator.UserValidator;
import by.jwd.cafe.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.jwd.cafe.command.RequestParameter.WRONG_DATA_MARKER;
import static by.jwd.cafe.command.SessionAttribute.*;

public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        UserValidator validator = UserValidatorImpl.getInstance();
        if (!validator.validateLogin(login) || !validator.validatePassword(password)) {
            return false;
        }
        boolean match;
        try {
            String secretPassword = PasswordEncryptor.md5Apache(password);
            match = userDao.authenticate(login, secretPassword);
        } catch (DaoException e) {
            logger.error("Try to authenticate user " + login + password + " was failed.", e);
            throw new ServiceException("Try to authenticate user " + login + password + " was failed.", e);
        }

        return match;
    }

    @Override
    public boolean createNewAccount(Map<String, String> userData) throws ServiceException {
        boolean isCreated = false;
        UserValidator validator = UserValidatorImpl.getInstance();
        if (!validator.validateUserDataCreate(userData)) {
            return isCreated;
        }
        String login = userData.get(LOGIN_MSG);
        String password = userData.get(PASSWORD_SES);
        String firstName = userData.get(FIRST_NAME_SES);
        String lastName = userData.get(LAST_NAME_SES);
        String email = userData.get(EMAIL_SES);
        try {
            if (userDao.isEmailExist(email)) {
                userData.put(WRONG_EMAIL_EXISTS_SES, WRONG_DATA_MARKER);
                return isCreated;
            }
            String secretPassword = PasswordEncryptor.md5Apache(password);
            User newUser = new User.UserBuilder()
                    .withLogin(login)
                    .withPassword(secretPassword)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .build();
            isCreated = userDao.add(newUser);
        } catch (DaoException e) {
            logger.error("Try to create new account was failed.", e);
            throw new ServiceException("Try to create new account was failed.", e);
        }
        return isCreated;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        return null;
    }

    @Override
    public boolean changePassword(Map<String, String> passwordData) throws ServiceException {
        boolean isChanged = false;
        int userId = Integer.parseInt(passwordData.get(USER_ID_SES));
        String login = passwordData.get(LOGIN_SES);
        String oldPassword = passwordData.get(PASSWORD_SES);
        String newPassword = passwordData.get(NEW_PASSWORD_SES);
        UserValidator validator = UserValidatorImpl.getInstance();
        if (!validator.validatePassword(newPassword) || !validator.validatePassword(oldPassword)) {
            logger.info("Wrong password.");
            passwordData.put(WRONG_PASSWORD_SES, WRONG_DATA_MARKER);
            return isChanged;
        }
        String oldSecretPassword = PasswordEncryptor.md5Apache(oldPassword);
        try {
            Optional<User> userCheck = userDao.findUserByLoginAndPassword(login, oldSecretPassword);
            if(userCheck.isEmpty()) {
                logger.info("Wrong password.");
                passwordData.put(WRONG_PASSWORD_SES, WRONG_DATA_MARKER);
                return isChanged;
            }
            String newSecretPassword = PasswordEncryptor.md5Apache(newPassword);
            isChanged = userDao.changePassword(userId, newSecretPassword);
        } catch (DaoException e) {
            logger.error("Try to change password was failed.", e);
            throw new ServiceException("Try to change password was failed.", e);
        }
        return isChanged;
    }

    @Override
    public boolean refillBalance(Map<String, String> balanceData) throws ServiceException {
        return false;
    }
}
