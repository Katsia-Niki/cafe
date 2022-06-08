package by.jwd.cafe.validator;

import by.jwd.cafe.entity.PaymentType;

import java.math.BigDecimal;
import java.util.Map;

public interface OrderValidator {
    boolean validateOrderData(PaymentType paymentType, BigDecimal balance, BigDecimal loyaltyPoints, BigDecimal cartSum);
}
