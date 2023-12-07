package com.crenu.kiosk.ui;

import com.crenu.kiosk.admin.MenuManager;

import com.crenu.kiosk.menu.Menu;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class MenuDisplayScreen extends JFrame {
    private String currentLanguage;
    private MenuManager menuManager;
    private JPanel menuPanel;
    private JPanel cartPanel;
    private Map<String, Integer> cartItems; // Tracks the quantity of each item in the cart

    public MenuDisplayScreen(String language, MenuManager menuManager) {
        this.currentLanguage = language;
        this.menuManager = menuManager;
        this.cartItems = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menu Display - " + currentLanguage);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for displaying menu items
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        add(menuScrollPane, BorderLayout.CENTER);

        // Panel for displaying the shopping cart
        cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // Debugging border
        cartPanel.setPreferredSize(new Dimension(800, 200)); // Set a preferred size for the cart panel

        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        add(cartScrollPane, BorderLayout.SOUTH);

        updateMenuDisplay();
        updateCartDisplay();
    }

    public void updateMenuDisplay() {
        List<Menu> menuItems = menuManager.getMenuItems();
        menuPanel.removeAll();
        for (Menu temp : menuItems) {
            JButton addButton = new JButton("Select " + temp.getMenuName());
            addButton.addActionListener(e -> selectItem(temp));
            menuPanel.add(new JLabel(temp.toString()));
            menuPanel.add(addButton);
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }


    private void addItemToCart(String menuItem) {
        cartItems.put(menuItem, cartItems.getOrDefault(menuItem, 0) + 1);
        System.out.println("Added to cart: " + menuItem + ", Quantity: " + cartItems.get(menuItem)); // Debugging line
        updateCartDisplay();
    }

    private void removeItemFromCart(String menuItem) {
        int currentCount = cartItems.getOrDefault(menuItem, 0);
        if (currentCount > 1) {
            cartItems.put(menuItem, currentCount - 1);
        } else {
            cartItems.remove(menuItem);
        }
    }

    private void removeItemCompletelyFromCart(String menuItem) {
        cartItems.remove(menuItem);
    }


    private void updateCartDisplay() {
        cartPanel.removeAll();
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new FlowLayout());

            JLabel itemLabel = new JLabel(entry.getKey() + " x " + entry.getValue());
            JButton increaseButton = new JButton("+");
            JButton decreaseButton = new JButton("-");
            JButton removeButton = new JButton("Remove");
            JButton payment = new JButton("Pay");

            increaseButton.addActionListener(e -> {
                addItemToCart(entry.getKey());
                updateCartDisplay();
            });

            decreaseButton.addActionListener(e -> {
                removeItemFromCart(entry.getKey());
                updateCartDisplay();
            });

            removeButton.addActionListener(e -> {
                removeItemCompletelyFromCart(entry.getKey());
                updateCartDisplay();
            });

            itemPanel.add(itemLabel);
            itemPanel.add(increaseButton);
            itemPanel.add(decreaseButton);
            itemPanel.add(removeButton);
            cartPanel.add(itemPanel);
        }
        cartPanel.revalidate();
        cartPanel.repaint();
    }

    private String translateMenuItem(String menuItem, String language) {
        // Translation logic here
        return menuItem + " (" + language + ")";
    }

    private void selectItem(Menu mainItem) {
        double mainItemPrice = mainItem.getPrice(); // Get the price of the main item

        String side = selectSide();
        double sidePrice = side.equals("None") ? 0 : 3.0; // $3 for a side, $0 if None

        String drink = selectDrink();
        double drinkPrice = drink.equals("None") ? 0 : 3.0; // $3 for a drink, $0 if None

        double totalPrice = mainItemPrice + sidePrice + drinkPrice;
        String completeOrder = mainItem.getMenuName() + " (Drink: " + drink + ", Side: " + side + ") - $" + totalPrice;

        addItemToCart(completeOrder);
        updateCartDisplay();
    }


    private String selectSide() {
        String[] sides = {"French Fries", "Salad", "None"};
        return (String) JOptionPane.showInputDialog(this,
                "Select a side:",
                "Side Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sides,
                sides[0]);
    }

    private String selectDrink() {
        String[] drinks = {"Cola", "Water", "None"};
        return (String) JOptionPane.showInputDialog(this,
                "Select a drink:",
                "Drink Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                drinks,
                drinks[0]);
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
        updateMenuDisplay();
        updateCartDisplay();
    }
}
