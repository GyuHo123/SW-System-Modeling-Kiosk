package com.crenu.kiosk.menu;

public class Menu {
    private String menuName;
    private Integer price;
    private String category;

    public Menu(String menuName, Integer price, String category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters
    public String getMenuName() {
        return menuName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return menuName + " - $" + price;
    }
}
