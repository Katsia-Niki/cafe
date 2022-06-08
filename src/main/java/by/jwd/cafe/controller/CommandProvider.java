package by.jwd.cafe.controller;

import by.jwd.cafe.command.Command;
import by.jwd.cafe.command.CommandType;
import by.jwd.cafe.command.impl.*;
import by.jwd.cafe.command.impl.admin.FindAllUsersCommand;
import by.jwd.cafe.command.impl.admin.UpdateUserStatusCommand;
import by.jwd.cafe.command.impl.admin.to.GoToEditMenuPageCommand;
import by.jwd.cafe.command.impl.customer.AddItemToCartCommand;
import by.jwd.cafe.command.impl.customer.ConfirmOrderCommand;
import by.jwd.cafe.command.impl.customer.RefillBalanceCommand;
import by.jwd.cafe.command.impl.customer.RemoveItemFromCartCommand;
import by.jwd.cafe.command.impl.customer.to.GoToCartPageCommand;
import by.jwd.cafe.command.impl.customer.to.GoToPlaceOrderPageCommand;
import by.jwd.cafe.command.impl.customer.to.GoToRefillBalancePageCommand;
import by.jwd.cafe.command.impl.to.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.jwd.cafe.command.CommandType.*;

import java.util.EnumMap;

public final class CommandProvider {
    static Logger logger = LogManager.getLogger();
    private static EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    static {
        commands.put(ADD_ITEM_TO_CART, new AddItemToCartCommand());
        commands.put(CHANGE_PASSWORD, new ChangePasswordCommand());
        commands.put(CHANGE_LANGUAGE, new ChangeLanguageCommand());
        commands.put(CONFIRM_ORDER, new ConfirmOrderCommand());
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(FIND_ALL_USERS, new FindAllUsersCommand());
        commands.put(FIND_ALL_AVAILABLE_MENU, new FindAllAvailableMenuCommand());
        commands.put(GO_TO_ACCOUNT_PAGE, new GoToAccountPageCommand());
        commands.put(GO_TO_CART_PAGE, new GoToCartPageCommand());
        commands.put(GO_TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPageCommand());
        commands.put(GO_TO_CONTACT_PAGE, new GoToContactPageCommand());
        commands.put(GO_TO_EDIT_MENU_PAGE, new GoToEditMenuPageCommand());
        commands.put(GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
        commands.put(GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(GO_TO_MENU_PAGE, new GoToMenuPageCommand());
        commands.put(GO_TO_REFILL_BALANCE_PAGE, new GoToRefillBalancePageCommand());
        commands.put(GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogoutCommand());
        commands.put(GO_TO_PLACE_ORDER_PAGE, new GoToPlaceOrderPageCommand());
        commands.put(REFILL_BALANCE, new RefillBalanceCommand());
        commands.put(REGISTRATION, new RegistrationCommand());
        commands.put(REMOVE_ITEM_FROM_CART, new RemoveItemFromCartCommand());
        commands.put(UPDATE_PERSONAL_DATA, new UpdatePersonalDataCommand());
        commands.put(UPDATE_USER_STATUS, new UpdateUserStatusCommand());
    }

    public static Command of(String commandName) {
        Command currentCommand = commands.get(DEFAULT);
        if (commandName != null) {
            try {
                currentCommand = commands.get(CommandType.valueOf(commandName.toUpperCase()));
            } catch (IllegalArgumentException e) {
                logger.error("Command " + commandName + " is not present.", e);
            }
        }
        return currentCommand;
    }
}
