package by.jwd.cafe.command.impl.admin.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class GoToEditMenuPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
