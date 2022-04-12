package by.jwd.cafe.controller;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.CommandType;
import by.jwd.cafe.command.impl.AddUserCommand;
import by.jwd.cafe.command.impl.DefaultCommand;
import by.jwd.cafe.command.impl.LoginCommand;
import by.jwd.cafe.command.impl.LogoutCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

public final class CommandProvider {
    static Logger logger = LogManager.getLogger();
    private static EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    static {
        commands.put(CommandType.LOGIN, new LoginCommand());
        commands.put(CommandType.LOGOUT, new LogoutCommand());
        commands.put(CommandType.ADD_USER, new AddUserCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
    }

    public static Command defineCommand(String commandName) {
        Command currentCommand;
        try {
            currentCommand = commands.get(CommandType.valueOf(commandName.toUpperCase()));
        } catch (IllegalArgumentException e) {
            logger.error("Command " + commandName + " is not present.", e);
            currentCommand = new DefaultCommand();
        }
        return currentCommand;
    }
}
