package by.jwd.cafe.command.impl.customer;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.entity.PaymentType;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.OrderService;
import by.jwd.cafe.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static by.jwd.cafe.command.RequestParameter.PAYMENT_TYPE;
import static by.jwd.cafe.command.RequestParameter.PICK_UP_TIME;
import static by.jwd.cafe.command.SessionAttribute.*;

public class ConfirmOrderCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> orderData = (Map<String, String>) session.getAttribute(ORDER_DATA_SESSION);
        removeTempData(orderData);
        updateOrderDataFromRequest(request, orderData);

        Map<MenuItem, Integer> cart = (Map<MenuItem, Integer>) session.getAttribute(CART);
        OrderService service = OrderServiceImpl.getInstance();
        Router router;
        try {
            boolean isConfirmed = service.createOrder(orderData, cart);
            session.setAttribute(ORDER_CONFIRMED_MESSAGE, isConfirmed);
            session.setAttribute(CURRENT_PAGE, PagePath.PLACE_ORDER);
            router = new Router(PagePath.PLACE_ORDER);
        } catch (ServiceException e) {
            logger.error("Try to execute ConfirmOrderCommand was failed.", e);
            throw new CommandException("Try to execute ConfirmOrderCommand was failed.", e);
        }
        return router;
    }

    private void updateOrderDataFromRequest(HttpServletRequest request, Map<String, String> orderData) {
        String paymentTypeStr = request.getParameter(PAYMENT_TYPE);
        String pickUpTimeStr = request.getParameter(PICK_UP_TIME);
        orderData.put(PAYMENT_TYPE_SESSION, paymentTypeStr);
        orderData.put(PICK_UP_TIME_SESSION, pickUpTimeStr);
    }

    private void removeTempData(Map<String, String> orderData) {
        orderData.remove(WRONG_PAYMENT_TYPE_SESSION);
        orderData.remove(WRONG_PICK_UP_TIME_SESSION);
        orderData.remove(NOT_ENOUGH_MONEY_SESSION);
        orderData.remove(NOT_ENOUGH_LOYALTY_POINTS_SESSION);
    }
}
