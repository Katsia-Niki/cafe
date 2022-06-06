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

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.jwd.cafe.command.RequestParameter.MENU_ITEM_ID;
import static by.jwd.cafe.command.RequestParameter.MENU_ITEM_QUANTITY;
import static by.jwd.cafe.command.SessionAttribute.*;

public class AddItemToCartCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        List<MenuItem> menuItemList = (List<MenuItem>) session.getAttribute(MENU_ITEM_AVAILABLE_SESSION);
        Map<MenuItem, Integer> cart = (Map<MenuItem, Integer>) session.getAttribute(CART);
        int menuItemId = Integer.parseInt(request.getParameter(MENU_ITEM_ID));
        int quantity = Integer.parseInt(request.getParameter(MENU_ITEM_QUANTITY));
        Optional<MenuItem> optionalItemToAdd = menuItemList.stream()
                .filter(menuItem -> menuItemId == menuItem.getMenuItemId()).findAny();
        MenuItemService service = MenuItemServiceImpl.getInstance();
        if (optionalItemToAdd.isPresent()) {
            service.addItemToCart(cart, optionalItemToAdd.get(), quantity);
            session.setAttribute(MESSAGE_ITEM_ADDED_TO_CART, true);
        } else {
            session.setAttribute(MESSAGE_ITEM_ADDED_TO_CART, false);
        }
        session.setAttribute(CURRENT_PAGE, Command.extract(request));
        Router router = new Router(PagePath.MENU, Router.Type.REDIRECT);
        return router;
    }
}
