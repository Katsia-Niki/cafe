package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.UserRole;
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
import static by.jwd.cafe.command.SessionAttribute.PASSWORD_SESSION;

public class UpdatePersonalDataCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SESSION);
        removeTempData(userData);
        updateUserDataFromRequest(request, userData);
        UserService service = UserServiceImpl.getInstance();
        Router router;
        try {
            int sizeBefore = userData.size();
            boolean result = service.updatePersonalData(userData);
            int sizeAfter = userData.size();
            if (sizeBefore == sizeAfter) {
                session.removeAttribute(USER_DATA_SESSION);
                session.setAttribute(UPDATE_PERSONAL_DATA_RESULT, result);
            } else {
                session.setAttribute(USER_DATA_SESSION, userData);
            }
            System.out.println(userData);
            UserRole role = UserRole.valueOf(session.getAttribute(CURRENT_ROLE).toString());
            if (role == UserRole.ADMIN) {
                session.setAttribute(CURRENT_PAGE, PagePath.ADMIN_ACCOUNT);
                router = new Router(PagePath.ADMIN_ACCOUNT, Router.Type.REDIRECT);
            } else {
                session.setAttribute(CURRENT_PAGE, PagePath.CUSTOMER_ACCOUNT);
                router = new Router(PagePath.CUSTOMER_ACCOUNT, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Try to execute UpdatePersonalDataCommand was failed", e);
            throw new CommandException("Try to execute UpdatePersonalDataCommand was failed", e);
        }
        return router;
    }
    private void removeTempData(Map<String, String> userData) {
        userData.remove(WRONG_EMAIL_EXISTS_SESSION);
        userData.remove(WRONG_PASSWORD_SESSION);
        userData.remove(WRONG_FIRST_NAME_SESSION);
        userData.remove(WRONG_LAST_NAME_SESSION);
        userData.remove(PASSWORD_SESSION);
    }

    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(NEW_EMAIL_SESSION, request.getParameter(EMAIL));
        userData.put(FIRST_NAME_SESSION, request.getParameter(FIRST_NAME));
        userData.put(LAST_NAME_SESSION, request.getParameter(LAST_NAME));
        userData.put(ROLE_NAME_SESSION, request.getParameter(ROLE));
        userData.put(IS_ACTIVE_SESSION, request.getParameter(USER_IS_ACTIVE));
        userData.put(PASSWORD_SESSION, request.getParameter(PASS));
    }
}
