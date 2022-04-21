package by.jwd.cafe.service;

import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
    boolean createNewAccount(Map<String, String> userData) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    boolean changePassword(Map<String, String> passwordData) throws ServiceException;
    boolean refillBalance(Map<String, String> balanceData) throws ServiceException;
}
