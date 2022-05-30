package by.jwd.cafe.command.impl.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static by.jwd.cafe.command.SessionAttribute.*;

public class GoToChangePasswordPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(CHANGE_PASSWORD_RESULT);
        Map<String, String> userData = new HashMap<>();
        userData.put(USER_ID_SESSION, session.getAttribute(CURRENT_USER_ID).toString());
        userData.put(LOGIN_SESSION, session.getAttribute(CURRENT_LOGIN_SESSION).toString());
        session.setAttribute(USER_DATA_SESSION, userData);
        session.setAttribute(CURRENT_PAGE, PagePath.CHANGE_PASSWORD);
        return new Router(PagePath.CHANGE_PASSWORD, Router.Type.REDIRECT);
    }
}
