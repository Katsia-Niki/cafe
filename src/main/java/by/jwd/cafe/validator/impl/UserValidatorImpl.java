package by.jwd.cafe.validator.impl;

import by.jwd.cafe.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    private static final int MAX_EMAIL_LENGTH = 45;
    private static final int MAX_NAME_LENGTH = 20;
    private static final String EMAIL_REGEX =
            "[\\d\\p{Lower}]([\\d\\p{Lower}_\\-\\.]*)[\\d\\p{Lower}_\\-]@[\\d\\p{Lower}_\\-]{2,}\\.\\p{Lower}{2,6}";
    private static final String PASSWORD_REGEX = "^[\\wА-Яа-я\\d\\.]{5,45}$";
    private static final String AMOUNT_REGEX = "\\d{1,4}(\\.\\d\\d)??";
    private static final String NAME_REGEX = "[\\wА-Яа-яЁё][\\wА-Яа-яЁё\\s]*";




    @Override
    public boolean validateEmail(String email) {
        if ((email.length() > MAX_EMAIL_LENGTH) || email == null || email.isBlank()) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean validatePassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean validateName(String name) {
        if ((name.length() > MAX_NAME_LENGTH) ||name == null || name.isBlank()) {
            return false;
        }
        return name.matches(NAME_REGEX);
    }

    @Override
    public boolean validateAmount(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }
}
