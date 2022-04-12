package by.jwd.cafe.entity;

public enum MenuItemType {

    APPETIZER (1),
    MAIN_COURSE(2),
    SOUP(3),
    DESSERT(4),
    DRINK(5),
    UNSUPPORTED(0);
    private int menuItemId;
    MenuItemType(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }
}
