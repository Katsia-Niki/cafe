package by.jwd.cafe.command.impl.customer;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.Order;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.OrderService;
import by.jwd.cafe.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.jwd.cafe.command.SessionAttribute.CURRENT_PAGE;
import static by.jwd.cafe.command.SessionAttribute.CURRENT_USER_ID;
import static by.jwd.cafe.command.RequestAttribute.ORDER_LIST;

public class FindOrderByUserIdCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute(CURRENT_USER_ID);
        OrderService service = OrderServiceImpl.getInstance();
        Router router;
        try {
            List<Order> orderList = service.findOrderByUserId(userId);
            request.setAttribute(ORDER_LIST, orderList);
            session.setAttribute(CURRENT_PAGE, Command.extract(request));
            router = new Router(PagePath.CUSTOMER_ORDERS);
        } catch (ServiceException e) {
            logger.error("Try to execute FindOrderByUserIdCommand was failed.", e);
            throw new CommandException("Try to execute FindOrderByUserIdCommand was failed.", e);
        }
        return router;
    }
}
