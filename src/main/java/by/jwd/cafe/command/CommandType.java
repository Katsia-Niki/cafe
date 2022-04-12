package by.jwd.cafe.command;

import by.jwd.cafe.command.impl.AddUserCommand;
import by.jwd.cafe.command.impl.DefaultCommand;
import by.jwd.cafe.command.impl.LoginCommand;
import by.jwd.cafe.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER,
    LOGIN,
    LOGOUT,
    DEFAULT
}
