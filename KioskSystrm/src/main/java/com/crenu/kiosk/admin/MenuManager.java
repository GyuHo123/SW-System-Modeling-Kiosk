package com.crenu.kiosk.admin;

import com.crenu.kiosk.entity.Category;
import com.crenu.kiosk.menu.Menu;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.crenu.kiosk.KioskSystem.menuManager;

public class MenuManager extends JFrame {
    private List<Menu> menuItems;

    public MenuManager() {

        menuItems = new ArrayList<>();
        loadMenu();
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

    public void loadMenu(){
        //hard coding
        addMenuItem(new com.crenu.kiosk.menu.Menu("Bulgogi Burger", 8, Category.MAIN));
        addMenuItem(new com.crenu.kiosk.menu.Menu("Cheese Burger", 7, Category.MAIN));
        addMenuItem(new Menu("Veggie Burger", 6, Category.MAIN));
        addMenuItem(new Menu("Cola", 1, Category.DRINK));
        addMenuItem(new Menu("Water", 1, Category.DRINK));
        addMenuItem(new Menu("Lemonade", 1, Category.DRINK));
        addMenuItem(new Menu("French fries", 1, Category.SIDE));
    }

    public void saveMenu(){
        //저장했다고 가정한다
    }
}
