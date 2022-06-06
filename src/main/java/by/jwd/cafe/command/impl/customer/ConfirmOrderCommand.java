package by.jwd.cafe.command.impl.customer;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.Router;
import by.jwd.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class ConfirmOrderCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {


        return null;
    }
}
