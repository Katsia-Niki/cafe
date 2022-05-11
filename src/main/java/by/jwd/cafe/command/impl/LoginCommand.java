package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.*;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Optional;

import static by.jwd.cafe.command.RequestParameter.*;
import static by.jwd.cafe.command.SessionAttribute.*;

public class LoginCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SES);
        removeWrongMessage(userData);
        updateUserDataFromRequest(request, userData);
        UserService userService = UserServiceImpl.getInstance();
        Router router;
        try {
            Optional<User> optionalUser = userService.authenticate(userData);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.removeAttribute(USER_DATA_SES);
                session.setAttribute(USER_ID_SES, user.getUserId());
                session.setAttribute(LOGIN_SES, user.getLogin());
                session.setAttribute(CURRENT_ROLE, user.getRole().toString());
                session.setAttribute(CURRENT_PAGE, PagePath.LOGIN);
                request.setAttribute(RequestAttribute.USER, user.getFirstName());
                router = new Router(PagePath.CUSTOMER_ACCOUNT);
            } else {
                logger.info("User was not found."); //fixme
                request.setAttribute(RequestAttribute.LOGIN_MSG, "Incorrect login or password.");//fixme
                session.setAttribute(USER_DATA_SES, userData);
                session.setAttribute(CURRENT_PAGE, PagePath.LOGIN);
                router = new Router(PagePath.LOGIN);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }

    private void removeWrongMessage(Map<String, String> userData) {
        userData.remove(WRONG_EMAIL_OR_PASSWORD_SES);
        userData.remove(NOT_FOUND_SES);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(LOGIN_SES, request.getParameter(LOGIN));
        userData.put(PASSWORD_SES, request.getParameter(PASS));
    }
}
