package by.jwd.cafe.validator.impl;

import by.jwd.cafe.validator.MenuItemValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.jwd.cafe.command.RequestParameter.*;
import static by.jwd.cafe.command.SessionAttribute.*;

public class MenuItemValidatorImpl implements MenuItemValidator {
    static Logger logger = LogManager.getLogger();
    private static final String NAME_REGEX = "[\\wА-яЁё\\s]{2,45}";
    private static final String DESCRIPTION_REGEX = "[^><]+";
    private static final String PRICE_REGEX = "\\d{1,5}\\.?\\d{0,2}";
    private static final MenuItemValidatorImpl instance = new MenuItemValidatorImpl();

    private MenuItemValidatorImpl() {
    }

    public static MenuItemValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateName(String name) {
        return (name != null && name.matches(NAME_REGEX));
    }

    @Override
    public boolean validateDescription(String description) {
        return (description != null && description.matches(DESCRIPTION_REGEX));
    }

    @Override
    public boolean validatePrice(String price) {
        return (price != null && price.matches(PRICE_REGEX));
    }

    @Override
    public boolean validateItemDataCreate(Map<String, String> menuItemData) {
        boolean isValid = true;
        String itemName = menuItemData.get(MENU_ITEM_NAME_SESSION);
        String description = menuItemData.get(MENU_ITEM_DESCRIPTION_SESSION);
        String price = menuItemData.get(MENU_ITEM_PRICE_SESSION);
        if (!validateName(itemName)) {
            isValid = false;
            logger.error("Invalid menu item name.");
            menuItemData.put(WRONG_MENU_ITEM_NAME, WRONG_DATA_MARKER);
        }
        if (!validateDescription(description)) {
            isValid = false;
            logger.error("Invalid menu item description.");
            menuItemData.put(WRONG_MENU_ITEM_DESCRIPTION, WRONG_DATA_MARKER);
        }
        if (!validatePrice(price)) {
            isValid = false;
            logger.error("Invalid menu item price.");
            menuItemData.put(WRONG_MENU_ITEM_PRICE, WRONG_DATA_MARKER);
        }
        return isValid;
    }

    @Override
    public boolean validateDirection(String direction) {
        return direction.equals(PREVIOUS_SHEET) || direction.equals(NEXT_SHEET);
    }
}
