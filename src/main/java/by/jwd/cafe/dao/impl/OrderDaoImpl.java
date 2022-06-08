package by.jwd.cafe.dao.impl;

import by.jwd.cafe.dao.OrderDao;
import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.dao.mapper.impl.OrderMapper;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.Order;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class OrderDaoImpl implements OrderDao {
    static Logger logger = LogManager.getLogger();

    private static final String INSERT_ORDER = """
            INSERT INTO cafe.order (order_id, users_user_id, payment_type, pick_up_time, order_cost, 
            is_paid)
            VALUES (?,?,?,?,?,?)""";
    private static final String INSERT_ORDERED_MENU_ITEM = """
            INSERT INTO cafe.ordered_items (order_order_id, menu_item_menu_item_id, item_price, item_quantity)
            VALUES (?,?,?,?)""";
    private static final String SELECT_ALL_ORDERS = """
            SELECT order_id, users_user_id, payment_type, pick_up_time, order_cost, is_paid, status, creation_date
            FROM cafe.order
            ORDER BY creation_date DESC""";
    private static final String SELECT_ORDER_BY_USER_ID = """
            SELECT order_id, users_user_id, payment_type, pick_up_time, order_cost, is_paid, status, creation_date
            FROM cafe.order
            WHERE users_user_id=?
            ORDER BY creation_date DESC""";

    private static final String UPDATE_USER_BALANCE_AND_LOYALTY_POINTS = """
            UPDATE cafe.users SET balance=?, loyalty_points=?
            WHERE user_id=?
            """;
    private static OrderDaoImpl instance = new OrderDaoImpl();

    private OrderDaoImpl() {
    }

    public static OrderDaoImpl getInstance() {
        return instance;
    }


    @Override
    public boolean createOrder(Order order, BigDecimal balance, BigDecimal loyaltyPoints, Map<MenuItem, Integer> cart) throws DaoException {
        boolean result = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getOrderId());
            statement.setInt(2, order.getUserId());
            statement.setString(3, order.getPaymentType().name());
            statement.setTimestamp(4, Timestamp.valueOf(order.getPickUpTime()));
            statement.setBigDecimal(5, order.getOrderCost());
            statement.setBoolean(6, order.isPaid());
            int row = statement.executeUpdate();
            if (row == 0) {
                return result;
            }
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                order.setOrderId(resultSet.getInt(1));
            } else {
                logger.error("No generated keys.");
                throw new DaoException("No generated keys.");
            }

            statement = connection.prepareStatement(UPDATE_USER_BALANCE_AND_LOYALTY_POINTS);
            statement.setBigDecimal(1, balance);
            statement.setBigDecimal(2, loyaltyPoints);
            statement.setInt(3, order.getUserId());
            row = statement.executeUpdate();
            if (row == 0) {
                return result;
            }

            statement = connection.prepareStatement(INSERT_ORDERED_MENU_ITEM);
            Set<Map.Entry<MenuItem, Integer>> menuItems = cart.entrySet();
            for (Map.Entry<MenuItem, Integer> menuItem : menuItems) {
                statement.setInt(1, order.getOrderId());
                statement.setInt(2, menuItem.getKey().getMenuItemId());
                statement.setBigDecimal(3, menuItem.getKey().getPrice());
                statement.setInt(4, menuItem.getValue());
                row = statement.executeUpdate();
                if (row == 0) {
                    return result;
                }
            }
            result = true;
            connection.commit();

        } catch (SQLException e) {
            logger.error("Try to create order was failed.", e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error("Rollback create order was failed.", ex);
                throw new DaoException("Rollback create order was failed.", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("Connection or statement close was failed" + e);
                throw new DaoException("Connection or statement close was failed", e);
            }
        }
        return result;
    }

    @Override
    public List<Order> findOrderByUserId(int userId) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Mapper<Order> mapper = OrderMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Optional<Order> optionalOrder = mapper.map(resultSet);
                    if (optionalOrder.isPresent()) {
                        orderList.add(optionalOrder.get());
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find all order by user id was failed.", e);
            throw new DaoException("Try to find all order by user id was failed.", e);
        }
        return orderList;
    }

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Mapper<Order> mapper = OrderMapper.getInstance();
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Optional<Order> optionalOrder = mapper.map(resultSet);
                if (optionalOrder.isPresent()) {
                    orderList.add(optionalOrder.get());
                }
            }
        } catch (SQLException e) {
            logger.error("Try to find all orders was failed.", e);
            throw new DaoException("Try to find all orders was failed.", e);
        }
        return orderList;
    }

    @Override
    public boolean add(Order order) throws DaoException {
        logger.error("Unavailable operation to entity <Order>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Order>");
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        logger.error("Unavailable operation to entity <Order>");
        throw new UnsupportedOperationException("Unavailable operation to entity <Order>");
    }

    @Override
    public boolean update(Order order) throws DaoException {
        return false;
    }

    @Override
    public Optional<Order> findEntityById(Integer entityId) throws DaoException {
        return Optional.empty();
    }


}
