package by.jwd.cafe.validator.impl;

import by.jwd.cafe.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static by.jwd.cafe.command.RequestParameter.WRONG_DATA_MARKER;
import static by.jwd.cafe.command.SessionAttribute.*;


public class UserValidatorImpl implements UserValidator {
    static Logger logger = LogManager.getLogger();
    private static final String EMAIL_REGEX =
            "[\\d\\p{Alpha}]([\\d\\p{Alpha}_\\-\\.]*)[\\d\\p{Alpha}_\\-]@[\\d\\p{Alpha}_\\-]{2,}\\.\\p{Alpha}{2,6}";
    private static final String PASSWORD_REGEX = "^[\\wА-я\\.\\-]{3,45}$";
    private static final String AMOUNT_REGEX = "\\d{1,4}(\\.\\d\\d)??";
    private static final String NAME_REGEX = "[\\wА-яЁё\\s]{2,20}";
    private static final String LOGIN_REGEX = "[\\w-]{2,45}";
    private static final UserValidatorImpl instance = new UserValidatorImpl();

    private UserValidatorImpl() {

    }

    public static UserValidatorImpl getInstance() {
        return instance;
    }

    @Override
    public boolean validateEmail(String email) {
        boolean isValid = false;
        if (email != null) {
            isValid = email.matches(EMAIL_REGEX);
        }
        return isValid;
    }

    @Override
    public boolean validateLogin(String login) {
        boolean isValid = false;
        if (login != null) {
            isValid = login.matches(LOGIN_REGEX);
        }
        return isValid;
    }

    @Override
    public boolean validatePassword(String password) {
        boolean isValid = false;
        if (password != null) {
            isValid = password.matches(PASSWORD_REGEX);
        }
        return isValid;
    }

    @Override
    public boolean validateName(String name) {
        boolean isValid = false;
        if (name != null) {
            isValid =  name.matches(NAME_REGEX);
        }
        return isValid;
    }

    @Override
    public boolean validateAmount(String amount) {
        boolean isValid = false;
        if (amount != null) {
            isValid = amount.matches(AMOUNT_REGEX);
        }
        return isValid;
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
            logger.info("Invalid login.");
            isValid = false;
        }
        if (!validatePassword(password)) {
            userData.put(WRONG_PASSWORD_SES, WRONG_DATA_MARKER);
            logger.info("Invalid password.");
            isValid = false;
        }
        if (!password.equals(repeatPassword)) {
            userData.put(MISMATCH_PASSWORDS_SES, WRONG_DATA_MARKER);
            logger.info("Mismatch passwords.");
            isValid = false;
        }
        if (!validateName(firstName)) {
            userData.put(WRONG_FIRST_NAME_SES, WRONG_DATA_MARKER);
            logger.info("Invalid first name.");
            isValid = false;
        }
        if (!validateName(lastName)) {
            userData.put(WRONG_LAST_NAME_SES, WRONG_DATA_MARKER);
            logger.info("Invalid last name.");
            isValid = false;
        }
        if (!validateEmail(email)) {
            userData.put(WRONG_EMAIL_SES, WRONG_DATA_MARKER);
            logger.info("Invalid email.");
            isValid = false;
        }
        return isValid;
    }
}
