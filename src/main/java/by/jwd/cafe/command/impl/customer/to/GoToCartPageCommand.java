package by.jwd.cafe.command.impl.customer.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.service.impl.MenuItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.jwd.cafe.command.SessionAttribute.CART;
import static by.jwd.cafe.command.SessionAttribute.CART_SUM;
import static by.jwd.cafe.command.SessionAttribute.CURRENT_PAGE;

import java.math.BigDecimal;
import java.util.Map;

public class GoToCartPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<MenuItem, Integer> cart = (Map<MenuItem, Integer>) session.getAttribute(CART);
        Router router = new Router(PagePath.CART);
        if (cart.isEmpty()) {
            return router;
        }
        MenuItemService service = MenuItemServiceImpl.getInstance();
        BigDecimal cartSum = service.calculateCartSum(cart);
        session.setAttribute(CURRENT_PAGE, Command.extract(request));
        session.setAttribute(CART_SUM, cartSum);
        return router;
    }
}
