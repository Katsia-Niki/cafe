package by.jwd.cafe.entity;

public enum MenuItemType {
    UNSUPPORTED ("неподдерживается"),
    APPETIZER ("закуска"),
    MAIN_COURSE("основное блюдо"),
    SOUP ("суп"),
    DESSERT("десерт"),
    DRINK("напиток");
    String name;  //fixme проверить, нужен ли
    MenuItemType(String name) {} //fixme проверить, нужен ли

    public String getName() {  //fixme проверить, нужен ли
        return name;
    }
}
