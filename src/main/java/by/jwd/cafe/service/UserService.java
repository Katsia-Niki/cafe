package by.jwd.cafe.service;

import by.jwd.cafe.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
}
