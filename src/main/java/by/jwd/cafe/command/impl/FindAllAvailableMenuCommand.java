package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.*;
import by.jwd.cafe.entity.MenuItem;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.MenuItemService;
import by.jwd.cafe.service.impl.MenuItemServiceImpl;
import by.jwd.cafe.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllAvailableMenuCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        //add pagination

        MenuItemService service = MenuItemServiceImpl.getInstance();
        Router router;
        try {
           List<MenuItem> menu = service.findAvailableMenuItems();
           request.setAttribute(RequestAttribute.MENU_ITEM_LIST, menu);
           session.setAttribute(SessionAttribute.CURRENT_PAGE, CurrentPageExtractor.extract(request));
           router = new Router(PagePath.MENU);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllAvailableMenuCommand was failed.", e);
            throw new CommandException("Try to execute FindAllAvailableMenuCommand was failed.", e);
        }
        return router;
    }
}
