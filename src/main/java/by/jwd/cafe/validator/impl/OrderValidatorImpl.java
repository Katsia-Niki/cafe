package by.jwd.cafe.validator.impl;

import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.validator.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class OrderValidatorImpl implements OrderValidator {
    static Logger logger = LogManager.getLogger();

    private OrderValidatorImpl() {

    }

    private static final OrderValidatorImpl instance = new OrderValidatorImpl();

    public static OrderValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateOrderData(PaymentType paymentType, BigDecimal balance, BigDecimal loyaltyPoints, BigDecimal cartSum) {
        boolean isValid = false;
        switch (paymentType) {
            case CASH -> isValid = true;
            case ACCOUNT -> isValid = balance.compareTo(cartSum) >= 0 ? true : false;
            case LOYALTY_POINTS -> isValid = loyaltyPoints.compareTo(cartSum) >= 0 ? true : false;
        }
        return isValid;
    }
}
