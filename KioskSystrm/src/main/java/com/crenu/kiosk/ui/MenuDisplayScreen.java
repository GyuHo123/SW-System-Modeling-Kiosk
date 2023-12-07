package com.crenu.kiosk.ui;

import com.crenu.kiosk.placeOrder.OrderedItem;
import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDisplayScreen extends JFrame {
    private String currentLanguage;
    private MenuManager menuManager;
    private JPanel menuPanel;
    private JPanel cartPanel;
    private JLabel totalAmountLabel;
    private JButton paymentButton;
    private Map<String, OrderedItem> cartItems;

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

        // Menu items panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        add(menuScrollPane, BorderLayout.CENTER);

        // Cart panel
        cartPanel = new JPanel();
        cartPanel.setPreferredSize(new Dimension(300, 600));
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        add(cartScrollPane, BorderLayout.EAST);

        // Total amount and payment button
        totalAmountLabel = new JLabel("Total: $0.00");
        paymentButton = new JButton("Pay");
        paymentButton.addActionListener(e -> proceedToPayment());

        cartPanel.add(totalAmountLabel);
        cartPanel.add(paymentButton);

        updateMenuDisplay();
    }

    private double calculateTotalAmount() {
        return cartItems.values().stream()
                .mapToDouble(OrderedItem::getTotalPrice)
                .sum();
    }

    public void updateMenuDisplay() {
        menuPanel.removeAll();
        List<Menu> menuItems = menuManager.getMenuItems();
        for (Menu item : menuItems) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton selectButton = new JButton("Select " + item.getMenuName());
            selectButton.addActionListener(e -> selectItem(item)); // Pass the Menu item
            itemPanel.add(new JLabel(item.toString()));
            itemPanel.add(selectButton);
            menuPanel.add(itemPanel);
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void addItemToCart(OrderedItem orderedItem) {
        cartItems.put(orderedItem.getMenuName(), orderedItem);
        updateCartDisplay();
    }

    private void updateCartDisplay() {
        cartPanel.removeAll();
        cartPanel.add(totalAmountLabel);
        for (Map.Entry<String, OrderedItem> entry : cartItems.entrySet()) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel itemLabel = new JLabel(entry.getValue().getMenuName() + " x " + entry.getValue().getCount());
            JButton increaseButton = new JButton("+");
            JButton decreaseButton = new JButton("-");
            JButton removeButton = new JButton("Remove");

            increaseButton.addActionListener(e -> changeItemQuantity(entry.getKey(), 1));
            decreaseButton.addActionListener(e -> changeItemQuantity(entry.getKey(), -1));
            removeButton.addActionListener(e -> removeItemCompletelyFromCart(entry.getKey()));

            itemPanel.add(itemLabel);
            itemPanel.add(increaseButton);
            itemPanel.add(decreaseButton);
            itemPanel.add(removeButton);
            cartPanel.add(itemPanel);
        }
        double totalAmount = calculateTotalAmount();

        // Update the total amount label with the new total
        totalAmountLabel.setText("Total: $" + String.format("%.2f", totalAmount));

        // Revalidate and repaint the label to ensure the updated text is displayed
        totalAmountLabel.revalidate();
        totalAmountLabel.repaint();

        // You may also need to revalidate and repaint the panel that contains the total label
        cartPanel.revalidate();
        cartPanel.repaint();
    }

    private void changeItemQuantity(String itemName, int delta) {
        if (cartItems.containsKey(itemName)) {
            OrderedItem item = cartItems.get(itemName);
            int newCount = item.getCount() + delta;
            if (newCount > 0) {
                item.setCount(newCount);
            } else {
                cartItems.remove(itemName);
            }
            updateCartDisplay();
        }
    }

    private void removeItemCompletelyFromCart(String itemName) {
        cartItems.remove(itemName);
        updateCartDisplay();
    }

    private void selectItem(Menu mainItem) {
        int mainItemPrice = mainItem.getPrice(); // Assuming getPrice() returns int

        String side = selectSide();
        String drink = selectDrink();

        // Create a description for the selected item
        String itemDescription = mainItem.getMenuName() + " (Drink: " + drink + ", Side: " + side + ")";

        // Check if this item is already in the cart
        if (cartItems.containsKey(itemDescription)) {
            OrderedItem orderedItem = cartItems.get(itemDescription);
            orderedItem.increaseCount();
        } else {
            // Create a new OrderedItem instance and add it to the cartItems map
            OrderedItem orderedItem = new OrderedItem(itemDescription, mainItemPrice, "Category", 1);
            cartItems.put(itemDescription, orderedItem);
        }

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
    private void proceedToPayment() {
        System.out.println("Hello");
    }
}
