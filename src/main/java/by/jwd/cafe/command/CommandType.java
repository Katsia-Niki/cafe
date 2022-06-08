package by.jwd.cafe.command;

import by.jwd.cafe.entity.UserRole;

import java.util.EnumSet;

import static by.jwd.cafe.entity.UserRole.*;

public enum CommandType {
    ADD_ITEM_TO_CART(EnumSet.of(CUSTOMER)),
    CHANGE_PASSWORD(EnumSet.of(CUSTOMER, ADMIN)),
    CHANGE_LANGUAGE(EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    CONFIRM_ORDER(EnumSet.of(CUSTOMER)),
    DEFAULT(EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    FIND_ALL_USERS(EnumSet.of(ADMIN)),
    FIND_ALL_AVAILABLE_MENU(EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_ACCOUNT_PAGE(EnumSet.of(CUSTOMER, ADMIN)),
    GO_TO_CART_PAGE(EnumSet.of(CUSTOMER)),
    GO_TO_CHANGE_PASSWORD_PAGE(EnumSet.of(CUSTOMER, ADMIN)),
    GO_TO_CONTACT_PAGE(EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_EDIT_MENU_PAGE(EnumSet.of(ADMIN)),
    GO_TO_LOGIN_PAGE(EnumSet.of(GUEST)),
    GO_TO_MAIN_PAGE(EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_MENU_PAGE(EnumSet.of(GUEST, CUSTOMER, ADMIN)),
    GO_TO_PLACE_ORDER_PAGE(EnumSet.of(CUSTOMER)),
    GO_TO_REFILL_BALANCE_PAGE(EnumSet.of(CUSTOMER)),
    GO_TO_REGISTRATION_PAGE(EnumSet.of(GUEST)),
    LOGIN(EnumSet.of(GUEST)),
    LOGOUT(EnumSet.of(CUSTOMER, ADMIN)),
    REGISTRATION(EnumSet.of(GUEST)),
    REFILL_BALANCE(EnumSet.of(CUSTOMER)),
    REMOVE_ITEM_FROM_CART(EnumSet.of(CUSTOMER)),
    UPDATE_PERSONAL_DATA(EnumSet.of(CUSTOMER, ADMIN)),
    UPDATE_USER_STATUS(EnumSet.of(ADMIN));
    private EnumSet<UserRole> acceptableRole;

    CommandType(EnumSet<UserRole> acceptableRole) {
        this.acceptableRole = acceptableRole;
    }

    public EnumSet<UserRole> getAcceptableRole() {
        return acceptableRole;
    }
}