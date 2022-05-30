package by.jwd.cafe.command.impl.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.command.SessionAttribute;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToMainPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String currentPage = CurrentPageExtractor.extract(request);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return session.getAttribute(SessionAttribute.CURRENT_LOGIN_SESSION) != null
                ? new Router(PagePath.HOME) //fixme check page
                : new Router(PagePath.MAIN);
    }
}
