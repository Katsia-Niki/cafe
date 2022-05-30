package by.jwd.cafe.command.impl.admin.to;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToUserManagementPageCommand implements Command {
    static Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
