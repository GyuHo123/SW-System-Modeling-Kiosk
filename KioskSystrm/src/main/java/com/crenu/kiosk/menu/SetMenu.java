package com.crenu.kiosk.menu;

import com.crenu.kiosk.placeOrder.OrderedItem;
import java.util.ArrayList;
import java.util.List;

public class SetMenu {
    private String name;
    private Integer price;
    private List<OrderedItem> items;

    public SetMenu(String name, Integer price) {
        this.name = name;
        this.price = price;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderedItem item) {
        items.add(item);
    }

    // Getters
    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public List<OrderedItem> getItems() {
        return new ArrayList<>(items);
    }
}
