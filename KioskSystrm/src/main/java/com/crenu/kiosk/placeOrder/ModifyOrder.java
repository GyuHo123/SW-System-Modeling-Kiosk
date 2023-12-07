package com.crenu.kiosk.placeOrder;

import java.util.ArrayList;
import java.util.List;

public class ModifyOrder {
    private List<OrderedItem> items;

    public ModifyOrder() {
        this.items = new ArrayList<>();
    }

    public void addItem(OrderedItem item) {
        // Find if the item already exists in the list
        for (OrderedItem orderedItem : items) {
            // If it exists, increase the count
            if (orderedItem.equals(item)) {
                orderedItem.increaseCount();
                return;
            }
        }
        // If it doesn't exist, add a new item
        this.items.add(item);
    }

    public boolean removeItem(OrderedItem item) {
        return this.items.remove(item);
    }

    public boolean updateItemQuantity(OrderedItem item, int quantity) {
        for (OrderedItem orderedItem : items) {
            if (orderedItem.equals(item)) {
                if (quantity > 0) {
                    orderedItem.setCount(quantity);
                    return true;
                } else {
                    // If the quantity is zero or less, remove the item from the list
                    removeItem(orderedItem);
                    return true;
                }
            }
        }
        return false;
    }

    public int getTotalPrice() {
        return this.items.stream().mapToInt(OrderedItem::getTotalPrice).sum();
    }

    public void displayOrder() {
        this.items.forEach(item -> System.out.println(item));
        System.out.println("Total Price: " + getTotalPrice());
    }

    // Consider overriding equals method in OrderedItem for proper comparison
    public List<OrderedItem> getItems() {
        return new ArrayList<>(this.items);
    }
}
