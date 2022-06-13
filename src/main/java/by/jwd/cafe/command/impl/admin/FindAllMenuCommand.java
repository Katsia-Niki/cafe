package by.jwd.cafe.command.impl.admin;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.service.impl.MenuItemServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.jwd.cafe.command.RequestAttribute.MENU_ITEM_LIST;
import static by.jwd.cafe.command.RequestParameter.DIRECTION;
import static by.jwd.cafe.command.SessionAttribute.*;

public class FindAllMenuCommand implements Command {
    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, Integer> paginationData = session.getAttribute(PAGINATION_SESSION) != null
                ? (Map<String, Integer>) session.getAttribute(PAGINATION_SESSION)
                : new HashMap<>();
        String direction = request.getParameter(DIRECTION);
        MenuItemService service = MenuItemServiceImpl.getInstance();
        Router router;
        try {
            List<MenuItem> menu = service.findAllMenuItems(direction, paginationData);
            session.setAttribute(MENU_ITEM_ALL_SESSION, menu);
            System.out.println(session.getAttribute(MENU_ITEM_ALL_SESSION));
            session.setAttribute(PAGINATION_SESSION, paginationData);
            session.setAttribute(CURRENT_PAGE, Command.extract(request));
            router = new Router(PagePath.ALL_MENU);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllMenuCommand was failed.", e);
            throw new CommandException("Try to execute FindAllMenuCommand was failed.", e);
        }
        return router;
    }
}
