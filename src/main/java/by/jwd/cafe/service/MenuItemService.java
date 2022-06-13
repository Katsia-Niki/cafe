package by.jwd.cafe.service;

import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MenuItemService {
    List<MenuItem> findAllMenuItems() throws ServiceException;

    List<MenuItem> findAvailableMenuItems(String direction, Map<String, Integer> paginationData) throws ServiceException;

    List<MenuItem> findAllMenuItems(String direction, Map<String, Integer> paginationData) throws ServiceException;
    Optional<MenuItem> findMenuItemById(String menuItemId) throws ServiceException;

    boolean addMenuItem(Map<String, String> menuData) throws ServiceException;
    boolean updateMenuItem(Map<String, String> menuData) throws ServiceException;
    boolean createImage (byte[] newImage, String menuItemId) throws ServiceException;

    void addItemToCart(Map<MenuItem, Integer> cart, MenuItem itemToAdd, int quantity); //todo ??
    boolean removeItemFromCart(Map<MenuItem, Integer> cart, int itemToRemoveId); //todo ??
    BigDecimal calculateCartSum(Map<MenuItem, Integer> cart);  //todo ??
}
