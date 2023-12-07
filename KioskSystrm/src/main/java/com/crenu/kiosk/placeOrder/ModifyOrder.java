package com.crenu.kiosk.placeOrder;

import java.util.ArrayList;
import java.util.List;

public class ModifyOrder {
    private List<OrderedItem> items;

    public ModifyOrder() {
        items = new ArrayList<>();
    }

    public void addItem(OrderedItem item) {
        items.add(item);
    }

    public void removeItem(OrderedItem item) {
        items.remove(item);
    }

    public int getTotalPrice() {
        int total = 0;
        for (OrderedItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayOrder() {
        for (OrderedItem item : items) {
            System.out.println("Item: " + item.getMenuName() + ", Quantity: " + item.getCount() + ", Price: " + item.getPrice());
        }
        System.out.println("Total Price: " + getTotalPrice());
    }
}
