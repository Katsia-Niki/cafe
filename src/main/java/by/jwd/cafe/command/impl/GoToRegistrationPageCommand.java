package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.command.SessionAttribute;
import by.jwd.cafe.command.PagePath;
import by.jwd.cafe.exception.CommandException;
import by.jwd.cafe.util.CurrentPageExtractor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class GoToRegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.CREATE_NEW_ACCOUNT_RESULT);
        Map<String, String> userData = new HashMap<>();
        session.setAttribute(SessionAttribute.USER_DATA_SES, userData);
        String currentPage = CurrentPageExtractor.extract(request);
        session.setAttribute(SessionAttribute.CURRENT_PAGE, currentPage);
        return new Router(PagePath.REGISTRATION_PAGE);
    }
}
