package by.jwd.cafe.dao.mapper.impl;

import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.entity.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.jwd.cafe.dao.ColumnName.*;

public class ItemMapper implements Mapper<MenuItem> {
    static Logger logger = LogManager.getLogger();
    private static final ItemMapper instance = new ItemMapper();
    private ItemMapper() {}

    public static ItemMapper getInstance() {
        return instance;
    }
    @Override
    public Optional<MenuItem> map(ResultSet resultSet) {
        Optional<MenuItem> optionalMenuItem;
        try {
            MenuItem menuItem = new MenuItem.MenuItemBuilder()
                    .withMenuItemId(resultSet.getInt(MENU_ITEM_ID))
                    .withMenuItemTypeById(resultSet.getInt(MENU_ITEM_TYPE_ID))
                    .withName(resultSet.getString(MENU_ITEM_NAME))
                    .withDescription(resultSet.getString(DESCRIPTION))
                    .withPrice(resultSet.getBigDecimal(PRICE))
                    .withIsAvailable(resultSet.getBoolean(AVAILABLE))
                    .withPicture(resultSet.getString(PICTURE))
                    .build();
            optionalMenuItem = Optional.of(menuItem);
        } catch (SQLException e) {
            logger.info("SQL exception while map MenuItem resultSet", e);
            optionalMenuItem = Optional.empty();
        }
        return optionalMenuItem;
    }
}
