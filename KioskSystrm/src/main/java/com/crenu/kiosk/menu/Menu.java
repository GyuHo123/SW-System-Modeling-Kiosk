package com.crenu.kiosk.menu;

public class Menu {
    private String menuName;
    private Integer price;
    private Category category;

    public Menu(String menuName, Integer price, Category category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }
    public String getMenuName() {
        return menuName;
    }

    public Integer getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return menuName + " - $" + price;
    }
}
