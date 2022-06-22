package by.jwd.cafe.service;

import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.model.entity.MenuItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MenuItemService {
    List<MenuItem> findAllMenuItems(int currentPageNumber) throws ServiceException;

    int findNumberOfPages() throws ServiceException;

    int findNumberOfAvailablePages() throws ServiceException;

    List<MenuItem> findAvailablePaginatedMenuItems(int currentPageNumber) throws ServiceException;

    Optional<MenuItem> findMenuItemById(String menuItemId) throws ServiceException;

    boolean addMenuItem(Map<String, String> menuData) throws ServiceException;

    boolean updateMenuItem(Map<String, String> menuData) throws ServiceException;

    boolean createImage(byte[] newImage, String menuItemId) throws ServiceException;

    void addItemToCart(Map<MenuItem, Integer> cart, MenuItem itemToAdd, int quantity);

    boolean removeItemFromCart(Map<MenuItem, Integer> cart, int itemToRemoveId);

    BigDecimal calculateCartSum(Map<MenuItem, Integer> cart);
}
