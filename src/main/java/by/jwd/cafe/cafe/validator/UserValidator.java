package by.jwd.cafe.validator;

import java.util.Map;

public interface UserValidator {
    boolean validateLogin(String login);
    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validateName(String name);

    boolean validateAmount(String amount);

    boolean validateUserDataCreate(Map<String, String> userData);
}
