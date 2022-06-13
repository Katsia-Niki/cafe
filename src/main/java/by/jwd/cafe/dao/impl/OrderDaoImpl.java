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

        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement orderStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement balanceStatement = connection.prepareStatement(UPDATE_USER_BALANCE_AND_LOYALTY_POINTS);
                 PreparedStatement orderedItemStatement = connection.prepareStatement(INSERT_ORDERED_MENU_ITEM)) {

                orderStatement.setInt(1, order.getOrderId());
                orderStatement.setInt(2, order.getUserId());
                orderStatement.setString(3, order.getPaymentType().name());
                orderStatement.setTimestamp(4, Timestamp.valueOf(order.getPickUpTime()));
                orderStatement.setBigDecimal(5, order.getOrderCost());
                orderStatement.setBoolean(6, order.isPaid());
                orderStatement.executeUpdate();

                try (ResultSet resultSet = orderStatement.getGeneratedKeys()) {
                    resultSet.next();
                    order.setOrderId(resultSet.getInt(1));
                }

                balanceStatement.setBigDecimal(1, balance);
                balanceStatement.setBigDecimal(2, loyaltyPoints);
                balanceStatement.setInt(3, order.getUserId());
                balanceStatement.executeUpdate();

                Set<Map.Entry<MenuItem, Integer>> menuItems = cart.entrySet();
                for (Map.Entry<MenuItem, Integer> menuItem : menuItems) {
                    orderedItemStatement.setInt(1, order.getOrderId());
                    orderedItemStatement.setInt(2, menuItem.getKey().getMenuItemId());
                    orderedItemStatement.setBigDecimal(3, menuItem.getKey().getPrice());
                    orderedItemStatement.setInt(4, menuItem.getValue());
                    orderedItemStatement.executeUpdate();
                }
                result = true;
                connection.commit();
            }
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
                connection.close();
            } catch (SQLException e) {
                logger.error("Connection or statement close was failed", e);
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
