package by.jwd.cafe.command;

import by.jwd.cafe.command.impl.AddUserCommand;
import by.jwd.cafe.command.impl.DefaultCommand;
import by.jwd.cafe.command.impl.LoginCommand;
import by.jwd.cafe.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER (new AddUserCommand()),
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    DEFAULT (new DefaultCommand());
    Command command;
    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
