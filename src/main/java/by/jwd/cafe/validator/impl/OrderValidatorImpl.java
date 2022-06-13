package by.jwd.cafe.validator.impl;

import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.validator.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;

import static by.jwd.cafe.command.SessionAttribute.*;

public final class OrderValidatorImpl implements OrderValidator {
    static Logger logger = LogManager.getLogger();

    private OrderValidatorImpl() {

    }

    private static final OrderValidatorImpl instance = new OrderValidatorImpl();

    public static OrderValidatorImpl getInstance() {
        return instance;
    }

//    @Override
//    public boolean validateOrderData2(String paymentTypeStr, String pickUpTimeStr, BigDecimal balance, BigDecimal loyaltyPoints, BigDecimal cartSum) {
//        boolean isValid = false;
//        PaymentType paymentType;
//        try {
//            paymentType = PaymentType.valueOf((paymentTypeStr).toUpperCase());
//            LocalDateTime pickUpTime = LocalDateTime.parse(pickUpTimeStr);
//        } catch (IllegalArgumentException | DateTimeParseException e) {
//            logger.error("Not valid order data.", e);
//            return isValid;
//        }
//        switch (paymentType) {
//            case CASH -> isValid = true;
//            case ACCOUNT -> isValid = balance.compareTo(cartSum) >= 0 ? true : false;
//            case LOYALTY_POINTS -> isValid = loyaltyPoints.compareTo(cartSum) >= 0 ? true : false;
//        }
//        return isValid;
//    }

    @Override
    public boolean validateOrderData(Map<String, String> orderData, BigDecimal balance, BigDecimal loyaltyPoints) {
        boolean isValid = false;
        String paymentTypeStr = orderData.get(PAYMENT_TYPE_SESSION);
        BigDecimal cartSum = new BigDecimal(orderData.get(CART_SUM));
        String pickUpTimeStr = orderData.get(PICK_UP_TIME_SESSION);
        PaymentType paymentType;
        try {
            paymentType = PaymentType.valueOf((paymentTypeStr).toUpperCase());
            LocalDateTime pickUpTime = LocalDateTime.parse(pickUpTimeStr);
        } catch (IllegalArgumentException ex) {
            logger.error("Not valid order data.", ex);
            orderData.put(WRONG_PAYMENT_TYPE_SESSION, OrderValidator.WRONG_DATA_MARKER);
            return isValid;
        } catch (DateTimeParseException e) {
            logger.error("Not valid order data.", e);
            orderData.put(WRONG_PICK_UP_TIME_SESSION, OrderValidator.WRONG_DATA_MARKER);
            return isValid;
        }
        switch (paymentType) {
            case CASH -> isValid = true;
            case ACCOUNT -> {
                if (balance.compareTo(cartSum) >= 0) {
                    isValid = true;
                } else {
                    orderData.put(NOT_ENOUGH_MONEY_SESSION, OrderValidator.WRONG_DATA_MARKER);
                    return isValid;
                }
            }
            case LOYALTY_POINTS -> {
                if (loyaltyPoints.compareTo(cartSum) >= 0) {
                    isValid = true;
                } else {
                    orderData.put(NOT_ENOUGH_LOYALTY_POINTS_SESSION, OrderValidator.WRONG_DATA_MARKER);
                    return isValid;
                }
            }
        }
        return isValid;
    }
}
