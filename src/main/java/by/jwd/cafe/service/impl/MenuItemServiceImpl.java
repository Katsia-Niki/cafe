package by.jwd.cafe.service.impl;

import by.jwd.cafe.dao.MenuItemDao;
import by.jwd.cafe.dao.impl.MenuItemDaoImpl;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.validator.MenuItemValidator;
import by.jwd.cafe.validator.impl.MenuItemValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static by.jwd.cafe.command.SessionAttribute.*;

public class MenuItemServiceImpl implements MenuItemService {
    static Logger logger = LogManager.getLogger();
    private static MenuItemServiceImpl instance = new MenuItemServiceImpl();
    private MenuItemDao itemDao = MenuItemDaoImpl.getInstance();
    private MenuItemServiceImpl() {}
    public static MenuItemServiceImpl getInstance() {
        return instance;
    }
    @Override
    public List<MenuItem> findAllMenuItems() throws ServiceException {//add pagination??
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
    public List<MenuItem> findAvailableMenuItems() throws ServiceException {
        List<MenuItem> menu;
        try {
            menu = itemDao.findAllAvailableItems();
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
}
