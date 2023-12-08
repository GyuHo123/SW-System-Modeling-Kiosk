package com.crenu.kiosk.admin;

import com.crenu.kiosk.menu.Category;

import java.util.ArrayList;

public class Item {
    private String name;
    private Integer price;
    private String thumID;
    private Category category;
    public Item(String name, Integer price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Item(String menuName, Integer price, Category category, String thumID) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
