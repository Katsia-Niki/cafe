package by.jwd.cafe.command.impl.admin;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.RequestAttribute;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import by.jwd.cafe.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.jwd.cafe.command.SessionAttribute.CURRENT_PAGE;

public class FindAllUsersCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService service = UserServiceImpl.getInstance();
        Router router;
        try {
            List<User> users = service.findAllUsers();
            request.setAttribute(RequestAttribute.USERS_LIST, users);
            session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
            router = new Router(PagePath.USERS);
        } catch (ServiceException e) {
            logger.error("Try to execute FindAllUsersCommand was failed.", e);
            throw new CommandException("Try to execute FindAllUsersCommand was failed.", e);
        }
        return router;
    }
}