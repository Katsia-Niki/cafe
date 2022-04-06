package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.*;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.exception.ServiceException;
import by.jwd.cafe.service.UserService;
import by.jwd.cafe.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASS);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if(userService.authenticate(login, password)) {
                 request.setAttribute(RequestAttribute.USER, login); //todo переделать на first_name, last_name

                 session.setAttribute(SessionAttribute.USER_NAME, login);

                 page = PagePath.MAIN;
            } else {
                request.setAttribute(SessionAttribute.LOGIN_MSG, "incorrect login or password");
                page = PagePath.INDEX;
            }
            session.setAttribute(SessionAttribute.CURRENT_PAGE, page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new Router(page);
    }
}
