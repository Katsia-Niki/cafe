package by.jwd.cafe.command;

import by.jwd.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
    default void refresh() {}
}
