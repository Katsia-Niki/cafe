package by.jwd.cafe.dao.mapper.impl;

import by.jwd.cafe.dao.mapper.Mapper;
import by.jwd.cafe.entity.Order;
import by.jwd.cafe.entity.PaymentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static by.jwd.cafe.dao.ColumnName.*;

public class OrderMapper implements Mapper<Order> {
    static Logger logger = LogManager.getLogger();
    private static final OrderMapper instance = new OrderMapper();

    private OrderMapper() {

    }

    public static OrderMapper getInstance() {
        return instance;
    }

    @Override
    public Optional<Order> map(ResultSet resultSet) {
        Optional<Order> optionalOrder;
        try {
            Order order = new Order.OrderBuilder()
                    .withOrderId(resultSet.getInt(ORDER_ID))
                    .withUserId(resultSet.getInt(USERS_USER_ID))
                    .withPaymentType(PaymentType.valueOf(resultSet.getString(PAYMENT_TYPE).toUpperCase()))
                    .withPickUpTime(resultSet.getDate(PICK_UP_TIME).toLocalDate().atTime(resultSet.getTime(PICK_UP_TIME).toLocalTime()))
                    .withOrderCost(resultSet.getBigDecimal(ORDER_COST))
                    .withIsPaid(resultSet.getBoolean(IS_PAID))
                    .withStatus(Order.Status.valueOf(resultSet.getString(STATUS).toUpperCase()))
                    .withCreationDate(resultSet.getDate(CREATION_DATE).toLocalDate())
                    .build();
            optionalOrder = Optional.of(order);
        } catch (SQLException e) {
            logger.error("SQL exception while map User resultSet", e);
            optionalOrder = Optional.empty();
        }
        return optionalOrder;
    }
}
