package by.jwd.cafe.service;

import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.Order;
import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {
    void addItemToCart(Map<MenuItem, Integer> cart, MenuItem itemToAdd, int quantity); //todo ??

    boolean removeItemFromCart(Map<MenuItem, Integer> cart, int itemToRemoveId); //todo ??

    BigDecimal calculateCartSum(Map<MenuItem, Integer> cart);  //todo ??

    BigDecimal calculateLoyaltyPoints(BigDecimal cartSum, PaymentType paymentType);  //todo ??

    boolean createOrder(Map<String, String> orderData, Map<MenuItem, Integer> cart) throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;

    List<Order> findOrderByUserId(int userId) throws ServiceException;

    Optional<Order> findOrderById(String orderId) throws ServiceException;
    //todo add updateStatus ?
}
