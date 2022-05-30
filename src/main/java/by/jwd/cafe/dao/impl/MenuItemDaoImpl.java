package by.jwd.cafe.dao.impl;

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
            FROM cafe.menu_item WHERE available=1""";
    private static final String INSERT_MENU_ITEM = """
            INSERT INTO cafe.menu_item(menu_item_id, menu_item_type_id, menu_item_name, description, price, available, picture)
            VALUES (?,?,?,?,?,?,?)""";
    private static final String UPDATE_MENU_ITEM = """
            UPDATE cafe.menu_item SET menu_item_type_id=?, menu_item_name=?, description=?, price=?, 
            available=?, picture=? WHERE menu_item_id=?""";
    private static final MenuItemDaoImpl instance = new MenuItemDaoImpl();
    private MenuItemDaoImpl() {}
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
            statement.setString(7, menuItem.getPicture());
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
            statement.setString(6, menuItem.getPicture());
            statement.setInt(7, menuItem.getMenuItemId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Try to add MenuItem was failed", e);
            throw new DaoException("Try to add MenuItem was failed", e);
        }
        return rows == 1;
    }

    @Override
    public Optional<MenuItem> findEntityById(Integer entityId) throws DaoException {
        return Optional.empty();
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
    public Optional<MenuItem> findItemByName() throws DaoException {
        return Optional.empty();
    }
}