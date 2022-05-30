package by.jwd.cafe.dao;

import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MenuItemDao extends BaseDao<Integer, MenuItem> {
    List<MenuItem> findAllAvailableItems() throws DaoException;
    Optional<MenuItem> findItemByName() throws DaoException;

}
