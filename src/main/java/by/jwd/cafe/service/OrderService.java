package by.jwd.cafe.service;

import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.model.entity.MenuItem;
import by.jwd.cafe.model.entity.Order;
import by.jwd.cafe.model.entity.PaymentType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {
    BigDecimal calculateLoyaltyPoints(BigDecimal cartSum, PaymentType paymentType);  //todo ??

    boolean createOrder(Map<String, String> orderData, Map<MenuItem, Integer> cart) throws ServiceException;

    List<Order> findAllOrders() throws ServiceException;

    List<Order> findOrderByUserId(int userId) throws ServiceException;
    List<Order> findOrdersByStatus(Map<String, String> searchParameters) throws ServiceException;

    List<Order> findOrdersByDateRange(Map<String, String> searchParameters) throws ServiceException;

    Optional<Order> findOrderById(String orderId) throws ServiceException;

    boolean updateStatus(String role, String newStatus, Order order) throws ServiceException;

    Map<Integer, Boolean> createСanBeCanceledMap(List<Order> orders);

    Map<Integer, Boolean> createCanBeUpdatedMap(List<Order> orders);
    List<Order.Status> findAvailableStatuses(Order.Status status);
}
