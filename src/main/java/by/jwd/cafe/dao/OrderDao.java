package by.jwd.cafe.dao;

import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.Order;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderDao extends BaseDao<Integer, Order> {
    boolean createOrder(Order order, BigDecimal balance, BigDecimal loyaltyPoints, Map<MenuItem, Integer> cart) throws DaoException;
    List<Order> findOrderByUserId(int userId) throws DaoException;
    List<Order> findAll() throws DaoException;
}
