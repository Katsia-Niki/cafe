package by.jwd.cafe.dao;

import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MenuItemDao extends BaseDao<Integer, MenuItem> {
    List<MenuItem> findAllAvailableItems() throws DaoException;
    int findNumberOfAvailableItems () throws DaoException;

    int findNumberOfAllItems () throws DaoException;
    Optional<MenuItem> findItemByName() throws DaoException;

    List<MenuItem> findPrevious(int menuItemId) throws DaoException;

    List<MenuItem> findAllNext(int menuItemId) throws DaoException;

    List<MenuItem> findAllPrevious(int menuItemId) throws DaoException;

    List<MenuItem> findNext(int menuItemId) throws DaoException;
    boolean updateImage(byte[] newImage, int menuItemId) throws DaoException;
}
