package com.crenu.kiosk.admin;

import com.crenu.kiosk.menu.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MenuManager extends JFrame {
    private List<Menu> menuItems;
    private JPanel menuPanel;
    private JMenuBar menuBar; // Declare menuBar as a private field

    public MenuManager() {
        menuItems = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menu Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(menuPanel);
        add(scrollPane, BorderLayout.CENTER);

        menuBar = new JMenuBar(); // Initialize the menuBar
        setJMenuBar(menuBar);
        createMenuBar();
    }

    private void createMenuBar() {
        JMenu editMenu = new JMenu("Edit");
        JMenuItem addItem = new JMenuItem("Add Menu Item");
        JMenuItem removeItem = new JMenuItem("Remove Menu Item");

        addItem.addActionListener(e -> {
            String newItemName = JOptionPane.showInputDialog(this, "Enter Menu Item Name:");
            try {
                Integer newItemPrice = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Menu Item Price:"));
                String newItemCategory = "Main"; // Assuming a default category
                Menu newItem = new Menu(newItemName, newItemPrice, newItemCategory);
                menuItems.add(newItem);
                updateMenuDisplay();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price entered.");
            }
        });

        removeItem.addActionListener(e -> {
            String itemToRemove = JOptionPane.showInputDialog(this, "Enter Menu Item Name to Remove:");
            menuItems.removeIf(item -> item.getMenuName().equals(itemToRemove));
            updateMenuDisplay();
        });

        editMenu.add(addItem);
        editMenu.add(removeItem);
        menuBar.add(editMenu); // Use the menuBar field here
    }

    private void updateMenuDisplay() {
        menuPanel.removeAll();
        for (Menu menuItem : menuItems) {
            menuPanel.add(new JLabel(menuItem.toString()));
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    public List<Menu> getMenuItems() {
        return new ArrayList<>(menuItems); // Return a copy to prevent external modification
    }

    public void addMenuItem(Menu menu) {
        this.menuItems.add(menu);
        updateMenuDisplay();  // Update the display to reflect the new item
    }

    public void removeMenuItem(Menu menu) {
        for (Menu menuItem : menuItems) {
            if (menuItem.getMenuName().equals(menu.getMenuName())) {
                menuItems.remove(menuItem);
                updateMenuDisplay();  // Update the display to reflect the removal
                break;  // Exit the loop after removing the item
            }
        }
    }
}
