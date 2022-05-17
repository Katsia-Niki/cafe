package by.jwd.cafe.controller;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.CommandType;
import by.jwd.cafe.command.impl.*;
import by.jwd.cafe.command.impl.to.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.jwd.cafe.command.CommandType.*;

import java.util.EnumMap;

public final class CommandProvider {
    static Logger logger = LogManager.getLogger();
    private static EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    static {
        commands.put(CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(GO_TO_ACCOUNT_PAGE, new GoToAccountPageCommand());
        commands.put(GO_TO_CONTACT_PAGE, new GoToContactPageCommand());
        commands.put(GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(REGISTRATION, new RegistrationCommand());
    }

    public static Command defineCommand(String commandName) {
        Command currentCommand;
        if (commandName == null) {
            currentCommand = new DefaultCommand();
            return currentCommand;
        }
        try {
            currentCommand = commands.get(CommandType.valueOf(commandName.toUpperCase()));
        } catch (IllegalArgumentException e) {
            logger.error("Command " + commandName + " is not present.", e);
            currentCommand = new DefaultCommand();
        }
        return currentCommand;
    }
}
