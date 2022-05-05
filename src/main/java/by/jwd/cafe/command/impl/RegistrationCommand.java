package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.jwd.cafe.command.SessionAttribute.*;
import static by.jwd.cafe.command.RequestParameter.*;

public class RegistrationCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Map<String, String> userData = (Map<String, String>) session.getAttribute(USER_DATA_SES);
        removeWrongMessage(userData);
        updateUserDataFromRequest(request, userData);
        UserService service = UserServiceImpl.getInstance();
        Router router;
        try{
            int sizeBefore = userData.size();
            boolean result = service.createNewAccount(userData);
            int sizeAfter = userData.size();
            if (sizeBefore == sizeAfter) {
                session.removeAttribute(USER_DATA_SES);
                session.setAttribute(REGISTRATION_RESULT, result);
            } else {
                session.setAttribute(USER_DATA_SES, userData);
            }
            session.setAttribute(CURRENT_PAGE, PagePath.REGISTRATION);
            router = new Router(PagePath.REGISTRATION, Router.Type.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Try to create new account was failed.", e);
            throw new CommandException("Try to create new account was failed.", e);
        }
        return router;
    }
    private void removeWrongMessage(Map<String, String> userData) {
        userData.remove(WRONG_LOGIN_SES);
        userData.remove(WRONG_EMAIL_SES);
        userData.remove(WRONG_PASSWORD_SES);
        userData.remove(WRONG_FIRST_NAME_SES);
        userData.remove(WRONG_LAST_NAME_SES);
        userData.remove(MISMATCH_PASSWORDS_SES);
        userData.remove(WRONG_EMAIL_EXISTS_SES);
    }
    private void updateUserDataFromRequest(HttpServletRequest request, Map<String, String> userData) {
        userData.put(EMAIL_SES, request.getParameter(EMAIL));
        userData.put(LOGIN_SES, request.getParameter(LOGIN));
        userData.put(FIRST_NAME_SES, request.getParameter(FIRST_NAME));
        userData.put(LAST_NAME_SES, request.getParameter(LAST_NAME));
        userData.put(PASSWORD_SES, request.getParameter(PASS));
        userData.put(REPEAT_PASSWORD_SES, request.getParameter(REPEAT_PASSWORD));
    }
}
