package by.jwd.cafe.command;

import by.jwd.cafe.entity.UserRole;

import java.util.EnumSet;

import static by.jwd.cafe.entity.UserRole.*;

public enum CommandType {
    CHANGE_PASSWORD("change_password",EnumSet.of(CUSTOMER, ADMIN)),
    CHANGE_LANGUAGE("change_language", EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    FIND_ALL_USERS("find_all_users", EnumSet.of(ADMIN)),
    GO_TO_ACCOUNT_PAGE("go_to_account_page", EnumSet.of(CUSTOMER, ADMIN)),
    GO_TO_CHANGE_PASSWORD_PAGE("go_to_change_password_page", EnumSet.of(CUSTOMER, ADMIN)),
    GO_TO_CONTACT_PAGE("go_to_contact_page", EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_LOGIN_PAGE("go_to_login_page", EnumSet.of(GUEST)),
    GO_TO_MAIN_PAGE("go_to_main_page", EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_REFILL_BALANCE_PAGE("go_to_refill_balance_page", EnumSet.of(CUSTOMER)),
    GO_TO_REGISTRATION_PAGE("go_to_registration_page", EnumSet.of(GUEST)),
    LOGIN("login", EnumSet.of(GUEST)),
    LOGOUT("logout", EnumSet.of(CUSTOMER, ADMIN)),
    REGISTRATION("registration", EnumSet.of(GUEST)),
    REFILL_BALANCE("refill_balance", EnumSet.of(CUSTOMER)),
    UPDATE_PERSONAL_DATA("update_personal_data", EnumSet.of(CUSTOMER, ADMIN)),
    UPDATE_USER_STATUS("update_user_status", EnumSet.of(ADMIN));
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
