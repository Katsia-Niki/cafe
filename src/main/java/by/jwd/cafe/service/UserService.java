package by.jwd.cafe.service;

import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> authenticate(Map<String, String> userData) throws ServiceException;
    boolean createNewAccount(Map<String, String> userData) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    boolean changePassword(Map<String, String> passwordData) throws ServiceException;
    Optional<User> findUserById(String userId) throws ServiceException;
    boolean updatePersonalData(Map<String, String> userData) throws ServiceException;
    boolean updateUserStatus(int userId, int newUserStatus) throws ServiceException;
    boolean refillBalance(Map<String, String> balanceData) throws ServiceException;
}
