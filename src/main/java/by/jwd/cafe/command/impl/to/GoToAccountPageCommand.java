package by.jwd.cafe.command.impl.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.RequestParameter;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.entity.User;
import by.jwd.cafe.entity.UserRole;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import by.jwd.cafe.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.jwd.cafe.command.SessionAttribute.*;

public class GoToAccountPageCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(UPDATE_PERSONAL_DATA_RESULT);
        UserService service = UserServiceImpl.getInstance();
        String roleName = request.getAttribute(CURRENT_ROLE).toString();
        UserRole userRole = UserRole.valueOf(roleName);
        String userId = userRole == UserRole.ADMIN
                ? request.getParameter(RequestParameter.USER_ID)
                : session.getAttribute(CURRENT_USER_ID).toString();
        Router router;
        try {
            Optional<User> optionalUser = service.findUserById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Map<String, String> userData = new HashMap<>();
                userData.put(USER_ID_SES, userId);
                userData.put(LOGIN_SES, user.getLogin());
                userData.put(FIRST_NAME_SES, user.getFirstName());
                userData.put(LAST_NAME_SES, user.getLastName());
                userData.put(EMAIL_SES, user.getEmail());
                userData.put(BALANCE_SES, user.getBalance().toString());
                userData.put(LOYALTY_POINTS_SES, user.getLoyaltyPoints().toString());
                userData.put(IS_ACTIVE_SES, String.valueOf(user.isActive()));
                userData.put(ROLE_NAME_SES, user.getRole().toString());
                session.setAttribute(USER_DATA_SES, userData);
                session.setAttribute(CURRENT_PAGE, CurrentPageExtractor.extract(request));
            } else {
                session.setAttribute(NOT_FOUND_SES, true);
            }
            router = userRole == UserRole.ADMIN
                    ? new Router(PagePath.ADMIN_ACCOUNT, Router.Type.REDIRECT)
                    : new Router(PagePath.CUSTOMER_ACCOUNT, Router.Type.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Try to execute GoToAccountPageCommand was failed.", e);
            throw new CommandException("Try to execute GoToAccountPageCommand was failed.", e);
        }
        return router;
    }
}
