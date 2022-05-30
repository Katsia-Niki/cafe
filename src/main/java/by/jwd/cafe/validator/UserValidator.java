package by.jwd.cafe.validator;

import java.math.BigDecimal;
import java.util.Map;

public interface UserValidator {
    BigDecimal MAX_AMOUNT = new BigDecimal(999999.99);
    boolean validateLogin(String login);
    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validateName(String name);

    boolean validateAmount(String amount);

    boolean validateUserDataCreate(Map<String, String> userData);
    boolean validateUserDataUpdate(Map<String, String> userData);
}
