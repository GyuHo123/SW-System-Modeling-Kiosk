package com.crenu.kiosk.placeOrder;

import com.crenu.kiosk.menu.Menu;

public class OrderedItem {
    private String menuName;
    private int unitPrice;
    private String category;
    private int count;

    public OrderedItem(String menuName, int unitPrice, String category, int count) {
        this.menuName = menuName;
        this.unitPrice = unitPrice;
        this.category = category;
        this.count = count;
    }

    public int getTotalPrice() {
        return unitPrice * count;
    }

    public void increaseCount() {
        count++;
    }

    public void decreaseCount() {
        if (count > 0) {
            count--;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "OrderedItem{" +
                "menuName='" + menuName + '\'' +
                ", unitPrice=" + unitPrice +
                ", category='" + category + '\'' +
                ", count=" + count +
                '}';
    }
}


