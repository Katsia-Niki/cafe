package by.jwd.cafe.validator;

import java.math.BigDecimal;
import java.util.Map;

public interface MenuItemValidator {
    boolean validateName(String name);
    boolean validateDescription(String description);
    boolean validatePrice(String price);
    boolean validateItemDataCreate(Map<String, String> menuItemData);
}
