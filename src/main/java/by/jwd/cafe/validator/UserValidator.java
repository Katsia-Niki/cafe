package by.jwd.cafe.validator;

public interface UserValidator {
    boolean validateLoginForm(String email, String password);

    boolean validatePassword(String password);

    boolean validateName(String name);

    boolean validateAmount(String amount);
}
