package by.jwd.cafe.validator;

public interface UserValidator {
    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validateName(String name);

    boolean validateAmount(String amount);
}
