package by.jwd.cafe.entity;

import java.math.BigDecimal;

public class MenuItem extends AbstractEntity {

    private static final long serialVersionUID  = 1L;
    private int menuItemId;
    private MenuItemType menuItemType;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;

    public MenuItem() {
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public MenuItemType getMenuItemType() {
        return menuItemType;
    }

    public void setMenuItemType(int menuItemTypeId) {
        switch (menuItemTypeId) {
            case 1 -> this.menuItemType = MenuItemType.APPETIZER;
            case 2 -> this.menuItemType = MenuItemType.MAIN_COURSE;
            case 3 -> this.menuItemType = MenuItemType.SOUP;
            case 4 -> this.menuItemType = MenuItemType.DESSERT;
            case 5 -> this.menuItemType = MenuItemType.DRINK;
            default -> this.menuItemType = MenuItemType.UNSUPPORTED;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public static class MenuItemBuilder {
        private MenuItem newMenuItem;

        {
            newMenuItem = new MenuItem();
        }

        public MenuItemBuilder withMenuItemId (int menuItemId) {
            newMenuItem.menuItemId = menuItemId;
            return this;
        }

        public MenuItemBuilder withMenuItemType (MenuItemType menuItemType) {
            newMenuItem.menuItemType = menuItemType;
            return this;
        }

        public MenuItemBuilder withName (String name) {
            newMenuItem.name = name;
            return this;
        }

        public MenuItemBuilder withDescription (String description) {
            newMenuItem.description = description;
            return this;
        }

        public MenuItemBuilder withPrice (BigDecimal price) {
            newMenuItem.price = price;
            return this;
        }

        public MenuItemBuilder withIsAvailable (boolean isAvailable) {
            newMenuItem.isAvailable = isAvailable;
            return this;
        }

        public MenuItem build() {
            return newMenuItem;
        }
    }

    @Override
    public MenuItem clone() throws CloneNotSupportedException {
        return  (MenuItem) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (menuItemId != menuItem.menuItemId) return false;
        if (isAvailable != menuItem.isAvailable) return false;
        if (menuItemType != menuItem.menuItemType) return false;
        if (name != null ? !name.equals(menuItem.name) : menuItem.name != null) return false;
        if (description != null ? !description.equals(menuItem.description) : menuItem.description != null)
            return false;
        return price != null ? price.equals(menuItem.price) : menuItem.price == null;
    }

    @Override
    public int hashCode() {
        int result = menuItemId;
        result = 31 * result + (menuItemType != null ? menuItemType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (isAvailable ? 1 : 0);
        return result;
    }
}
