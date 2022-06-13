package by.jwd.cafe.validator;

import java.math.BigDecimal;
import java.util.Map;

public interface MenuItemValidator {
    String WRONG_DATA_MARKER = "Wrong data";
    String PREVIOUS_SHEET = "-1";
    String NEXT_SHEET = "1";
    boolean validateName(String name);

    boolean validateDescription(String description);

    boolean validatePrice(String price);

    boolean validateItemData(Map<String, String> menuItemData);
    boolean validateDirection(String direction);
}
