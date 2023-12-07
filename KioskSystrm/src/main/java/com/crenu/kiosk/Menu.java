package com.crenu.kiosk;

public class Menu {
    private String menuName;
    private Integer price;
    private String category;

    Menu(String menuName, Integer price, String category){
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
