package by.jwd.cafe.dao.impl;

import by.jwd.cafe.dao.ColumnName;
import by.jwd.cafe.dao.MenuItemDao;
import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.dao.mapper.impl.ItemMapper;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemDaoImpl implements MenuItemDao {
    static Logger logger = LogManager.getLogger();
    private static final String SELECT_ALL_MENU_ITEMS = """
            SELECT menu_item_id, menu_item_type_id, menu_item_name, description, price, available, picture
            FROM cafe.menu_item""";
    private static final String SELECT_ALL_AVAILABLE_MENU_ITEMS = """
            SELECT menu_item_id, menu_item_type_id, menu_item_name, description, price, picture
            FROM cafe.menu_item WHERE available=true""";
    private static final String SELECT_NUMBER_AVAILABLE_MENU_ITEMS = """
            SELECT count(available) AS num
                       FROM cafe.menu_item
                       WHERE available=true""";

    private static final String SELECT_NUMBER_ALL_MENU_ITEMS = """
            SELECT count(menu_item_id) AS num
                       FROM cafe.menu_item""";

    private static final String SELECT_MENU_ITEM_BY_ID = """
            SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name
                       FROM cafe.menu_item
                       JOIN cafe.menu_item_type ON cafe.menu_item.menu_item_type_id=cafe.menu_item_type.id
                       WHERE menu_item_id=?""";
    private static final String SELECT_PREVIOUS_MENU_ITEM_SHEET = """
            SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name FROM (
                       SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name
                       FROM cafe.menu_item
                       JOIN cafe.menu_item_type ON cafe.menu_item.menu_item_type_id=cafe.menu_item_type.id
                       WHERE available=true AND cafe.menu_item.menu_item_id<?
                       ORDER BY cafe.menu_item.menu_item_id DESC LIMIT ?) AS revers_table
                       ORDER BY menu_item_id""";
    private static final String SELECT_NEXT_MENU_ITEM_SHEET = """
            SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name
                       FROM cafe.menu_item
                       JOIN cafe.menu_item_type ON cafe.menu_item.menu_item_type_id=cafe.menu_item_type.id
                       WHERE available=true AND cafe.menu_item.menu_item_id>? LIMIT ?""";

    private static final String SELECT_PREVIOUS_ALL_MENU_ITEM_SHEET = """
            SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name FROM (
                       SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name
                       FROM cafe.menu_item
                       JOIN cafe.menu_item_type ON cafe.menu_item.menu_item_type_id=cafe.menu_item_type.id
                       WHERE cafe.menu_item.menu_item_id<?
                       ORDER BY cafe.menu_item.menu_item_id DESC LIMIT ?) AS revers_table
                       ORDER BY menu_item_id""";
    private static final String SELECT_NEXT_ALL_MENU_ITEM_SHEET = """
            SELECT menu_item_id, menu_item_name, description, price, available, picture, type_name
                       FROM cafe.menu_item
                       JOIN cafe.menu_item_type ON cafe.menu_item.menu_item_type_id=cafe.menu_item_type.id
                       WHERE cafe.menu_item.menu_item_id>? LIMIT ?""";
    private static final String INSERT_MENU_ITEM = """
            INSERT INTO cafe.menu_item(menu_item_id, menu_item_type_id, menu_item_name, description, price, available, picture)
            VALUES (?,?,?,?,?,?,?)""";
    private static final String UPDATE_MENU_ITEM = """
            UPDATE cafe.menu_item SET menu_item_type_id=?, menu_item_name=?, description=?, price=?, 
            available=? WHERE menu_item_id=?""";

    private static final String UPDATE_IMAGE = """
            UPDATE cafe.menu_item SET picture=?
            WHERE menu_item_id=?""";
    private static final int DEFAULT_PAGE_CAPACITY = 5;
    private static final MenuItemDaoImpl instance = new MenuItemDaoImpl();

    private MenuItemDaoImpl() {
    }

    public static MenuItemDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<MenuItem> findAll() throws DaoException {
        List<MenuItem> availableItems = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MENU_ITEMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Mapper<MenuItem> mapper = ItemMapper.getInstance();
            while (resultSet.next()) {
                Optional<MenuItem> optionalItem = mapper.map(resultSet);
                if (optionalItem.isPresent()) {
                    availableItems.add(optionalItem.get());
                }
            }
            logger.log(Level.INFO, "Available menu item list: " + availableItems);
        } catch (SQLException e) {
            logger.error("Try to find all available items was failed.", e);
            throw new DaoException("Try to find all available items was failed.", e);
        }
        return availableItems;
    }

    @Override
    public boolean add(MenuItem menuItem) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_MENU_ITEM)) {
            statement.setInt(1, menuItem.getMenuItemId());
            statement.setInt(2, menuItem.getMenuItemType().ordinal());
            statement.setString(3, menuItem.getName());
            statement.setString(4, menuItem.getDescription());
            statement.setBigDecimal(5, menuItem.getPrice());
            statement.setBoolean(6, menuItem.isAvailable());
            statement.setBytes(7, menuItem.getPicture());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Try to add MenuItem was failed", e);
            throw new DaoException("Try to add MenuItem was failed", e);
        }
        return rows == 1;
    }

    @Override
    public boolean delete(MenuItem menuItem) throws DaoException {
        return false;//fixme
    }

    @Override
    public boolean update(MenuItem menuItem) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MENU_ITEM)) {
            statement.setInt(1, menuItem.getMenuItemType().ordinal());
            statement.setString(2, menuItem.getName());
            statement.setString(3, menuItem.getDescription());
            statement.setBigDecimal(4, menuItem.getPrice());
            statement.setBoolean(5, menuItem.isAvailable());
            statement.setInt(6, menuItem.getMenuItemId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Try to add MenuItem was failed", e);
            throw new DaoException("Try to add MenuItem was failed", e);
        }
        return rows == 1;
    }

    @Override
    public boolean updateImage(byte[] newImage, int menuItemId) throws DaoException {
        int rows;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MENU_ITEM)) {
            statement.setBytes(1, newImage);
            statement.setInt(2, menuItemId);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Try to update image was failed", e);
            throw new DaoException("Try to update image was failed", e);
        }
        return rows == 1;
    }

    @Override
    public Optional<MenuItem> findEntityById(Integer entityId) throws DaoException {
        Optional<MenuItem> optionalItem = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_MENU_ITEM_BY_ID)) {
            statement.setInt(1, entityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Mapper<MenuItem> mapper = ItemMapper.getInstance();
                    optionalItem = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find menu item by id was failed.", e);
            throw new DaoException("Try to find menu item by id was failed.", e);
        }
        return optionalItem;
    }

    @Override
    public List<MenuItem> findAllAvailableItems() throws DaoException {
        List<MenuItem> availableItems = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_AVAILABLE_MENU_ITEMS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Mapper<MenuItem> mapper = ItemMapper.getInstance();
            while (resultSet.next()) {
                Optional<MenuItem> optionalItem = mapper.map(resultSet);
                if (optionalItem.isPresent()) {
                    availableItems.add(optionalItem.get());
                }
            }
            logger.log(Level.INFO, "Available menu item list: " + availableItems);
        } catch (SQLException e) {
            logger.error("Try to find all available items was failed.", e);
            throw new DaoException("Try to find all available items was failed.", e);
        }
        return availableItems;
    }

    @Override
    public int findNumberOfAvailableItems() throws DaoException {
        int num = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NUMBER_AVAILABLE_MENU_ITEMS);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                num = resultSet.getInt(ColumnName.NUM);
                num = num % DEFAULT_PAGE_CAPACITY == 0
                        ? num / DEFAULT_PAGE_CAPACITY
                        : num / DEFAULT_PAGE_CAPACITY + 1;
            }
        } catch (SQLException e) {
            logger.error("SQL request to findNumberOfAvailableItems was failed.", e);
            throw new DaoException("SQL request to findNumberOfAvailableItems was failed.", e);
        }
        return num;
    }

    @Override
    public int findNumberOfAllItems() throws DaoException {
        int num = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NUMBER_ALL_MENU_ITEMS);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                num = resultSet.getInt(ColumnName.NUM);
                num = num % DEFAULT_PAGE_CAPACITY == 0
                        ? num / DEFAULT_PAGE_CAPACITY
                        : num / DEFAULT_PAGE_CAPACITY + 1;
            }
        } catch (SQLException e) {
            logger.error("SQL request to findNumberOfAvailableItems was failed.", e);
            throw new DaoException("SQL request to findNumberOfAvailableItems was failed.", e);
        }
        return num;
    }

    @Override
    public Optional<MenuItem> findItemByName() throws DaoException { //fixme

        return Optional.empty();
    }

    @Override
    public List<MenuItem> findPrevious(int menuItemId) throws DaoException {
        List<MenuItem> menu = new ArrayList<>();
        Mapper<MenuItem> mapper = ItemMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PREVIOUS_MENU_ITEM_SHEET)) {
            statement.setInt(1, menuItemId);
            statement.setInt(2, DEFAULT_PAGE_CAPACITY);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<MenuItem> optionalItem = mapper.map(resultSet);
                    if (optionalItem.isPresent()) {
                        menu.add(optionalItem.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find previous menu item sheet was failed.", e);
            throw new DaoException("Try to find previous menu item sheet was failed.", e);
        }
        return menu;
    }

    @Override
    public List<MenuItem> findAllPrevious(int menuItemId) throws DaoException {
        List<MenuItem> menu = new ArrayList<>();
        Mapper<MenuItem> mapper = ItemMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PREVIOUS_ALL_MENU_ITEM_SHEET)) {
            statement.setInt(1, menuItemId);
            statement.setInt(2, DEFAULT_PAGE_CAPACITY);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<MenuItem> optionalItem = mapper.map(resultSet);
                    if (optionalItem.isPresent()) {
                        menu.add(optionalItem.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find all previous menu item sheet was failed.", e);
            throw new DaoException("Try to find all previous menu item sheet was failed.", e);
        }
        return menu;
    }

    @Override
    public List<MenuItem> findNext(int menuItemId) throws DaoException {
        List<MenuItem> menu = new ArrayList<>();
        Mapper<MenuItem> mapper = ItemMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NEXT_MENU_ITEM_SHEET)) {
            statement.setInt(1, menuItemId);
            statement.setInt(2, DEFAULT_PAGE_CAPACITY);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<MenuItem> optionalItem = mapper.map(resultSet);
                    if (optionalItem.isPresent()) {
                        menu.add(optionalItem.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find next menu item sheet was failed.", e);
            throw new DaoException("Try to find next menu item sheet was failed.", e);
        }
        return menu;
    }

    @Override
    public List<MenuItem> findAllNext(int menuItemId) throws DaoException {
        List<MenuItem> menu = new ArrayList<>();
        Mapper<MenuItem> mapper = ItemMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_NEXT_ALL_MENU_ITEM_SHEET)) {
            statement.setInt(1, menuItemId);
            statement.setInt(2, DEFAULT_PAGE_CAPACITY);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<MenuItem> optionalItem = mapper.map(resultSet);
                    if (optionalItem.isPresent()) {
                        menu.add(optionalItem.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find next menu item sheet was failed.", e);
            throw new DaoException("Try to find next menu item sheet was failed.", e);
        }
        return menu;
    }
}
