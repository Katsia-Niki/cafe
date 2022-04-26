package by.jwd.cafe.command.impl;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX);
    }
}
