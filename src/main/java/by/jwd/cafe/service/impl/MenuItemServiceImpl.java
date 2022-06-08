package by.jwd.cafe.service.impl;

import by.jwd.cafe.dao.MenuItemDao;
import by.jwd.cafe.dao.impl.MenuItemDaoImpl;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.validator.MenuItemValidator;
import by.jwd.cafe.validator.impl.MenuItemValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static by.jwd.cafe.command.SessionAttribute.*;
import static by.jwd.cafe.validator.MenuItemValidator.PREVIOUS_SHEET;
import static by.jwd.cafe.validator.MenuItemValidator.NEXT_SHEET;

public class MenuItemServiceImpl implements MenuItemService {
    static Logger logger = LogManager.getLogger();
    private static MenuItemServiceImpl instance = new MenuItemServiceImpl();
    private MenuItemDao itemDao = MenuItemDaoImpl.getInstance();

    private MenuItemServiceImpl() {
    }

    public static MenuItemServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<MenuItem> findAllMenuItems() throws ServiceException {
        List<MenuItem> menu;
        try {
            menu = itemDao.findAll();
        } catch (DaoException e) {
            logger.error("Try to find all menu was failed.", e);
            throw new ServiceException("Try to find all menu was failed.", e);
        }
        return menu;
    }

    @Override
    public List<MenuItem> findAvailableMenuItems(String direction, Map<String, Integer> paginationData) throws ServiceException {
        List<MenuItem> menu = new ArrayList<>();
        try {
            if (paginationData.isEmpty() || direction == null
                    || (paginationData.get(CURRENT_SHEET_SESSION) == 1 && PREVIOUS_SHEET.equals(direction))
                    || (paginationData.get(CURRENT_SHEET_SESSION) == paginationData.get(ALL_SHEETS_SESSION))
                    && NEXT_SHEET.equals(direction)) {
                direction = NEXT_SHEET;
                buildPaginationData(paginationData);
            }
            MenuItemValidator validator = MenuItemValidatorImpl.getInstance();
            if (!validator.validateDirection(direction)) {
                paginationData.clear();
                logger.info("Invalid direction.");
                return menu;
            }
            if (direction.equals(NEXT_SHEET)) {
                int lastId = paginationData.get(LAST_ID_SESSION);
                menu = itemDao.findNext(lastId);
            } else {
                int firstId = paginationData.get(FIRST_ID_SESSION);
                menu = itemDao.findPrevious(firstId);
            }
            updatePaginationData(paginationData, direction, menu);
        } catch (DaoException e) {
            logger.error("Try to find all available menu items was failed.", e);
            throw new ServiceException("Try to find all available menu items was failed.", e);
        }
        return menu;
    }

    @Override
    public boolean addMenuItem(Map<String, String> menuData) throws ServiceException {
        boolean isAdded = false;
        MenuItemValidator validator = MenuItemValidatorImpl.getInstance();
        if (!validator.validateItemDataCreate(menuData)) {
            logger.info("Invalid menu item data.");
            return isAdded;
        }
        String itemName = menuData.get(MENU_ITEM_NAME_SESSION);
        String description = menuData.get(MENU_ITEM_DESCRIPTION_SESSION);
        BigDecimal price = new BigDecimal(menuData.get(MENU_ITEM_PRICE_SESSION));
        boolean isAvailable = menuData.get(MENU_ITEM_AVAILABLE_SESSION) != null
                ? Boolean.parseBoolean(menuData.get(MENU_ITEM_AVAILABLE_SESSION))
                : Boolean.TRUE;
        String picture = menuData.get(MENU_ITEM_PICTURE_SESSION);
        MenuItem item = new MenuItem.MenuItemBuilder()
                .withName(itemName)
                .withDescription(description)
                .withPrice(price)
                .withIsAvailable(isAvailable)
                .withPicture(picture)
                .build();
        try {
            isAdded = itemDao.add(item);
        } catch (DaoException e) {
            logger.info("Try to add new menu item was failed.", e);
            throw new ServiceException("Try to add new menu item was failed.", e);
        }
        return isAdded;
    }

    @Override
    public void addItemToCart(Map<MenuItem, Integer> cart, MenuItem itemToAdd, int quantity) {
        if(!cart.containsKey(itemToAdd)) {
            cart.put(itemToAdd, quantity);
        } else {
            int previousQuantity = cart.get(itemToAdd);
            cart.put(itemToAdd, previousQuantity + quantity);
        }
    }

    @Override
    public boolean removeItemFromCart(Map<MenuItem, Integer> cart, int itemToRemoveId) {
        boolean result;
        Set<MenuItem> cartSet = cart.keySet();
        Optional<MenuItem> menuItemToRemove = cartSet.stream()
                .filter(menuItem -> itemToRemoveId == menuItem.getMenuItemId())
                .findAny();
        if (menuItemToRemove.isPresent()) {
            cart.remove(menuItemToRemove.get());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public BigDecimal calculateCartSum(Map<MenuItem, Integer> cart) {
        BigDecimal cartSum = new BigDecimal(0);
        Set<Map.Entry<MenuItem, Integer>> menuItems = cart.entrySet();

        for(Map.Entry<MenuItem, Integer> menuItem : menuItems) {
            BigDecimal price = menuItem.getKey().getPrice();
            BigDecimal quantity = BigDecimal.valueOf(menuItem.getValue());
            cartSum = cartSum.add(price.multiply(quantity));
        }
        return cartSum;
    }
    private void buildPaginationData(Map<String, Integer> paginationData) throws DaoException {
        int num = itemDao.findNumberOfAvailableItems();
        paginationData.put(ALL_SHEETS_SESSION, Integer.valueOf(num));
        paginationData.put(CURRENT_SHEET_SESSION, 0);
        paginationData.put(FIRST_ID_SESSION, 0);
        paginationData.put(LAST_ID_SESSION, 0);
    }

    private void updatePaginationData(Map<String, Integer> paginationData, String direction, List<MenuItem> menu) throws DaoException {
        if (!menu.isEmpty()) {
            int newFirstId = menu.get(0).getMenuItemId();
            int newLastId = menu.get(menu.size() - 1).getMenuItemId();
            paginationData.put(FIRST_ID_SESSION, newFirstId);
            paginationData.put(LAST_ID_SESSION, newLastId);
            int currentSheet = paginationData.get(CURRENT_SHEET_SESSION);
            paginationData.put(CURRENT_SHEET_SESSION, currentSheet + Integer.parseInt(direction));
        }
    }
}
