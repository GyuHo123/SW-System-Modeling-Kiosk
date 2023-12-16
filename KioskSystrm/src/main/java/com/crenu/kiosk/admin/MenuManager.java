package com.crenu.kiosk.admin;

import com.crenu.kiosk.entity.Category;
import com.crenu.kiosk.menu.Menu;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MenuManager extends JFrame {
    private List<Menu> menuItems;

    public MenuManager() {
        menuItems = new ArrayList<>();
    }

    public List<Menu> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(Menu item) {
        menuItems.add(item);
    }

    public List<Menu> getMenuItemsByCategory(Category category) {
        return menuItems.stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }

    public void removeMenuItem(Menu menu) {
        for (Menu menuItem : menuItems) {
            if (menuItem.getName().equals(menu.getName())) {
                menuItems.remove(menuItem);
                break;  // Exit the loop after removing the item
            }
        }
    }
}
