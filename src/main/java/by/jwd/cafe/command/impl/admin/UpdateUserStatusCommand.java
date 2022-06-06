package by.jwd.cafe.command.impl.admin;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.command.SessionAttribute;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.jwd.cafe.command.RequestParameter.*;
import static by.jwd.cafe.command.SessionAttribute.*;

public class UpdateUserStatusCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        boolean isActive = Boolean.parseBoolean(request.getParameter(USER_IS_ACTIVE));
        int newUserStatus = isActive == true ? 0 : 1;
        UserService service = UserServiceImpl.getInstance();
        Router router;
        try {
            boolean result = service.updateUserStatus(userId, newUserStatus);
            session.setAttribute(UPDATE_USER_STATUS_RESULT, result);
            logger.info("Update user status result = " + result); //fixme
            router = new Router(PagePath.USERS);
        } catch (ServiceException e) {
            logger.error("Try to execute UpdateUserStatusCommand was failed.", e);
            throw new CommandException("Try to execute UpdateUserStatusCommand was failed.", e);
        }
        return router;
    }
}
