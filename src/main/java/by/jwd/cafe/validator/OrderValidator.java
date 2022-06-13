package by.jwd.cafe.validator;

import by.jwd.cafe.entity.PaymentType;

import java.math.BigDecimal;
import java.util.Map;

public interface OrderValidator {
    String WRONG_DATA_MARKER = "Wrong data";

    /**
     * Validate order data boolean.
     *
     * @param orderData     the order data
     * @param balance       the balance
     * @param loyaltyPoints the loyalty points
     * @return the boolean
     */
//    /**
//     * Validate order data boolean.
//     *
//     * @param paymentType   the payment type
//     * @param pickUpTime    the pick up time
//     * @param balance       the balance
//     * @param loyaltyPoints the loyalty points
//     * @param cartSum       the cart sum
//     * @return the boolean
//     */
 //   boolean validateOrderData2(String paymentType, String pickUpTime, BigDecimal balance, BigDecimal loyaltyPoints, BigDecimal cartSum);
    boolean validateOrderData(Map<String, String> orderData, BigDecimal balance, BigDecimal loyaltyPoints);
}
