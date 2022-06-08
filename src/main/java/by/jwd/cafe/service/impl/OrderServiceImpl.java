package by.jwd.cafe.service.impl;

import by.jwd.cafe.dao.MenuItemDao;
import by.jwd.cafe.dao.OrderDao;
import by.jwd.cafe.dao.UserDao;
import by.jwd.cafe.dao.impl.MenuItemDaoImpl;
import by.jwd.cafe.dao.impl.OrderDaoImpl;
import by.jwd.cafe.dao.impl.UserDaoImpl;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.Order;
import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.exception.DaoException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.OrderService;
import by.jwd.cafe.validator.OrderValidator;
import by.jwd.cafe.validator.impl.OrderValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static by.jwd.cafe.command.SessionAttribute.*;

public class OrderServiceImpl implements OrderService {
    static Logger logger = LogManager.getLogger();
    private static OrderServiceImpl instance = new OrderServiceImpl();
    private MenuItemDao itemDao = MenuItemDaoImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    private OrderDao orderDao = OrderDaoImpl.getInstance();

    @Override
    public void addItemToCart(Map<MenuItem, Integer> cart, MenuItem itemToAdd, int quantity) {
        if (!cart.containsKey(itemToAdd)) {
            cart.put(itemToAdd, quantity);
        } else {
            int previousQuantity = cart.get(itemToAdd);
            cart.put(itemToAdd, previousQuantity + quantity);
        }
    }

    @Override
    public boolean removeItemFromCart(Map<MenuItem, Integer> cart, int itemToRemoveId) {
        boolean result;
        Set<MenuItem> cartSet = cart.keySet();
        Optional<MenuItem> menuItemToRemove = cartSet.stream()
                .filter(menuItem -> itemToRemoveId == menuItem.getMenuItemId())
                .findAny();
        if (menuItemToRemove.isPresent()) {
            cart.remove(menuItemToRemove.get());
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public BigDecimal calculateCartSum(Map<MenuItem, Integer> cart) {
        BigDecimal cartSum = new BigDecimal(0);
        Set<Map.Entry<MenuItem, Integer>> menuItems = cart.entrySet();

        for (Map.Entry<MenuItem, Integer> menuItem : menuItems) {
            BigDecimal price = menuItem.getKey().getPrice();
            BigDecimal quantity = BigDecimal.valueOf(menuItem.getValue());
            cartSum = cartSum.add(price.multiply(quantity));
        }
        return cartSum;
    }

    @Override
    public BigDecimal calculateLoyaltyPoints(BigDecimal cartSum, PaymentType paymentType) {
        BigDecimal percentLoyaltyPoints = new BigDecimal(paymentType.getPercentLoyaltyPoints());
        BigDecimal result = cartSum.multiply(percentLoyaltyPoints.divide(new BigDecimal(100)));
        result.setScale(2, RoundingMode.HALF_UP);
        return result;
    }

    @Override
    public boolean createOrder(Map<String, String> orderData, Map<MenuItem, Integer> cart) throws ServiceException {
        boolean isCreated = false;
        try {
            int userId = Integer.parseInt(orderData.get(USER_ID_SESSION));
            UserDao userDao = UserDaoImpl.getInstance();
            BigDecimal userBalance = userDao.findBalanceByUserId(userId);
            BigDecimal userLoyaltyPoints = userDao.findLoyaltyPointsByUserId(userId);
            String paymentTypeStr = orderData.get(PAYMENT_TYPE_SESSION);
            PaymentType paymentType = PaymentType.valueOf((paymentTypeStr).toUpperCase());
            BigDecimal cartSum = new BigDecimal(orderData.get(CART_SUM));

            OrderValidator validator = OrderValidatorImpl.getInstance();
            if (!validator.validateOrderData(paymentType, userBalance, userLoyaltyPoints, cartSum)) {
                logger.info("Not enough money/loyalty points.");
                return isCreated;
            }
            userBalance = paymentType == PaymentType.ACCOUNT
                    ? userBalance.subtract(cartSum)
                    : userBalance;

            if (paymentType == PaymentType.LOYALTY_POINTS) {
                userLoyaltyPoints = userLoyaltyPoints.subtract(cartSum);
            } else {
                BigDecimal pointsToAdd = this.calculateLoyaltyPoints(cartSum, paymentType);
                userLoyaltyPoints = userLoyaltyPoints.add(pointsToAdd);
            }
            boolean isPaid = paymentType != PaymentType.CASH;
            String pickUpTimeStr = orderData.get(PICK_UP_TIME_SESSION);
            LocalDateTime pickUpTime = LocalDateTime.parse(pickUpTimeStr);

            Order order = new Order.OrderBuilder()
                    .withUserId(userId)
                    .withPaymentType(paymentType)
                    .withPickUpTime(pickUpTime)
                    .withOrderCost(cartSum)
                    .withIsPaid(isPaid)
                    .build();

            isCreated = orderDao.createOrder(order, userBalance, userLoyaltyPoints, cart);

        } catch (IllegalArgumentException | DateTimeParseException e) {
            logger.error("Not valid data.", e);
            return isCreated;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public List<Order> findAllOrders() throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDao.findAll();
        } catch (DaoException e) {
            logger.error("Try to find all orders was failed ", e);
            throw new ServiceException("Try to find all orders was failed", e);
        }
        return orderList;
    }

    @Override
    public List<Order> findOrderByUserId(int userId) throws ServiceException {
        List<Order> orderList;
        try {
            orderList = orderDao.findOrderByUserId(userId);
        } catch (DaoException e) {
            logger.error("Try to findOrderByUserId was failed ", e);
            throw new ServiceException("Try to findOrderByUserId was failed", e);
        }
        return orderList;
    }

    @Override
    public Optional<Order> findOrderById(String orderId) throws ServiceException {
        return Optional.empty();
    }
}
