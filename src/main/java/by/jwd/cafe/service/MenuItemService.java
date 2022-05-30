package by.jwd.cafe.service;

import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface MenuItemService {
    List<MenuItem> findAllMenuItems() throws ServiceException;
    List<MenuItem> findAvailableMenuItems() throws ServiceException;
    boolean addMenuItem(Map<String, String> menuData) throws ServiceException;
}
