package by.jwd.cafe.validator.impl;

import by.jwd.cafe.validator.UserValidator;

import java.util.Map;

import static by.jwd.cafe.command.RequestParameter.WRONG_DATA_MARKER;
import static by.jwd.cafe.command.SessionAttribute.*;


public class UserValidatorImpl implements UserValidator {
    private static final String EMAIL_REGEX =
            "[\\d\\p{Lower}]([\\d\\p{Lower}_\\-\\.]*)[\\d\\p{Lower}_\\-]@[\\d\\p{Lower}_\\-]{2,39}\\.\\p{Lower}{2,6}";
    private static final String PASSWORD_REGEX = "^[\\w\\d]{3,45}$";
    private static final String AMOUNT_REGEX = "\\d{1,4}(\\.\\d\\d)??";
    private static final String NAME_REGEX = "[\\wА-яЁё\\s]{2,20}";
    private static final String LOGIN_REGEX = "[\\w\\d_-]{2,45}";
    private static final UserValidatorImpl instance = new UserValidatorImpl();

    private UserValidatorImpl() {

    }

    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean validateLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean validatePassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean validateName(String name) {
        return name.matches(NAME_REGEX);
    }

    @Override
    public boolean validateAmount(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }

    @Override
    public boolean validateUserDataCreate(Map<String, String> userData) {
        String login = userData.get(LOGIN_SES);
        String password = userData.get(PASSWORD_SES);
        String repeatPassword = userData.get(REPEAT_PASSWORD_SES);
        String firstName = userData.get(FIRST_NAME_SES);
        String lastName = userData.get(LAST_NAME_SES);
        String email = userData.get(EMAIL_SES);

        boolean isValid = true;
        if (!validateLogin(login)) {
            userData.put(WRONG_LOGIN_SES, WRONG_DATA_MARKER);
            isValid = false;
        }
        if (!validatePassword(password)) {
            userData.put(WRONG_PASSWORD_SES, WRONG_DATA_MARKER);
            isValid = false;
        }
        if (!password.equals(repeatPassword)) {
            userData.put(MISMATCH_PASSWORDS_SES, WRONG_DATA_MARKER);
            isValid = false;
        }
        if (!validateName(firstName)) {
            userData.put(WRONG_FIRST_NAME_SES, WRONG_DATA_MARKER);
            isValid = false;
        }
        if (!validateName(lastName)) {
            userData.put(WRONG_LAST_NAME_SES, WRONG_DATA_MARKER);
            isValid = false;
        }
        if (!validateEmail(email)) {
            userData.put(WRONG_EMAIL_SES, WRONG_DATA_MARKER);
            isValid = false;
        }
        return isValid;
    }
}
