package by.jwd.cafe.command.impl.customer;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.service.impl.MenuItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;

import static by.jwd.cafe.command.SessionAttribute.CART;
import static by.jwd.cafe.command.RequestParameter.MENU_ITEM_ID_TO_REMOVE;
import static by.jwd.cafe.command.SessionAttribute.CART_SUM;

public class RemoveItemFromCartCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<MenuItem, Integer> cart = (Map<MenuItem, Integer>) session.getAttribute(CART);
        int menuItemIdToRemove = Integer.parseInt(request.getParameter(MENU_ITEM_ID_TO_REMOVE));
        MenuItemService service = MenuItemServiceImpl.getInstance();
        boolean result = service.removeItemFromCart(cart, menuItemIdToRemove);
        if (!result) {
            logger.error("Invalid menu item id to remove."); //fixme может удалить?
        }
        BigDecimal cartSum = service.calculateCartSum(cart);
        session.setAttribute(CART_SUM, cartSum);
        return new Router(PagePath.CART, Router.Type.REDIRECT);
    }
}
