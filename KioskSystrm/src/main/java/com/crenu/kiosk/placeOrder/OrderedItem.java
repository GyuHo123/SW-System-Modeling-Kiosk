package com.crenu.kiosk.placeOrder;

import com.crenu.kiosk.menu.Category;
import com.crenu.kiosk.menu.Menu;

public class OrderedItem extends Menu {
    private int count;

    public OrderedItem(String menuName, int unitPrice, Category category, int count) {
        super(menuName, unitPrice, category);  // Call to super class (Menu) constructor
        this.count = count;
    }

    public int getTotalPrice() {
        return super.getPrice() * count;  // Using price from the Menu class
    }

    public void increaseCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "menuName='" + super.getName() + '\'' +  // menuName is inherited from Menu
                ", unitPrice=" + super.getPrice() +
                ", category='" + super.getCategory() + '\'' +  // category is inherited from Menu
                ", count=" + count +
                '}';
    }
}
