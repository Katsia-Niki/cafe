package by.jwd.cafe.command;

import by.jwd.cafe.entity.UserRole;

import java.util.EnumSet;

import static by.jwd.cafe.entity.UserRole.*;

public enum CommandType {
    CHANGE_PASSWORD_COMMAND("change_password_command",EnumSet.of(CUSTOMER, ADMIN)),
    GO_TO_ACCOUNT_PAGE("go_to_account_page", EnumSet.of(CUSTOMER, ADMIN)),
    GO_TO_CONTACT_PAGE("go_to_contact_page", EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_LOGIN_PAGE("go_to_login_page", EnumSet.of(GUEST)),
    GO_TO_MAIN_PAGE("go_to_main_page", EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_REGISTRATION_PAGE("go_to_registration_page", EnumSet.of(GUEST)),
    LOGIN("login", EnumSet.of(GUEST)),
    LOGOUT("logout", EnumSet.of(CUSTOMER, ADMIN)),
    REGISTRATION("registration", EnumSet.of(GUEST));
    private String webCommandName;
    private EnumSet<UserRole> acceptableRole;

    CommandType(String webCommandName, EnumSet<UserRole> acceptableRole) {
        this.webCommandName = webCommandName;
        this.acceptableRole = acceptableRole;
    }

    public EnumSet<UserRole> getAcceptableRole() {
        return acceptableRole;
    }
}
