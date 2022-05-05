package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.jwd.cafe.command.SessionAttribute.CURRENT_ROLE;
import static by.jwd.cafe.command.SessionAttribute.CURRENT_PAGE;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = null;
        if (session.getAttribute(CURRENT_ROLE) != null) {
            session.setAttribute(CURRENT_PAGE, PagePath.HOME);
            router = new Router(PagePath.HOME);
        } else {
            session.setAttribute(CURRENT_PAGE, PagePath.MAIN);
            router = new Router(PagePath.MAIN);
        }
        return router;
    }
}
