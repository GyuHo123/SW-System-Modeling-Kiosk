package com.crenu.kiosk.placeOrder;

import com.crenu.kiosk.menu.Menu;

public class OrderedItem extends Menu {
    private Integer count;
    private Integer unitPrice;

    public OrderedItem(String menuName, Integer price, String category, Integer count) {
        super(menuName, price, category);
        this.unitPrice = price;
        this.count = count;
        setPrice(unitPrice * count);
    }

    public void increaseCount() {
        count++;
        setPrice(unitPrice * count);
    }

    public void decreaseCount() {
        if (count > 0) {
            count--;
            setPrice(unitPrice * count);
        }
    }

    public Integer getCount() {
        return count;
    }
}
