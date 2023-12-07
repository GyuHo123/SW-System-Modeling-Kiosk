package com.crenu.kiosk.ui;

import com.crenu.kiosk.menu.Category;
import com.crenu.kiosk.placeOrder.OrderedItem;
import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDisplayScreen extends JPanel {
    private MenuManager menuManager;
    private JPanel menuPanel;
    private JPanel cartPanel;
    private JLabel totalAmountLabel;
    private JButton paymentButton;
    private Map<String, OrderedItem> cartItems;

    public MenuDisplayScreen() {
        this.menuManager = new MenuManager();
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Bulgogi Burger", 8, Category.MAIN));
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Cheese Burger", 7, Category.MAIN));
        menuManager.addMenuItem(new Menu("Veggie Burger", 6, Category.MAIN));
        menuManager.addMenuItem(new Menu("Cola", 1, Category.DRINK));
        menuManager.addMenuItem(new Menu("Water", 1, Category.DRINK));
        menuManager.addMenuItem(new Menu("Lemonade", 1, Category.DRINK));
        menuManager.addMenuItem(new Menu("Cola", 1, Category.SIDE));
        menuManager.addMenuItem(new Menu("Water", 1, Category.SIDE));
        menuManager.addMenuItem(new Menu("Lemonade", 1, Category.SIDE));
        this.cartItems = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        add(menuScrollPane, BorderLayout.CENTER);

        cartPanel = new JPanel();
        cartPanel.setPreferredSize(new Dimension(300, 600));
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        add(cartScrollPane, BorderLayout.EAST);

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
            if(item.getCategory() == Category.MAIN){
                JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton selectButton = new JButton("Select " + item.getMenuName());
                selectButton.addActionListener(e -> selectItem(item));  
                itemPanel.add(new JLabel(item.toString()));
                itemPanel.add(selectButton);
                menuPanel.add(itemPanel);
            }
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
        cartPanel.add(paymentButton);
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

        totalAmountLabel.setText("Total: $" + String.format("%.2f", totalAmount));
        totalAmountLabel.revalidate();
        totalAmountLabel.repaint();
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
        int mainItemPrice = mainItem.getPrice();  

        String side = selectSide();
        String drink = selectDrink();

        int additionalPrice = calculateAdditionalPrice(side, drink);

        String itemDescription = mainItem.getMenuName() + " (Drink: " + drink + ", Side: " + side + ")";

        if (cartItems.containsKey(itemDescription)) {
            OrderedItem orderedItem = cartItems.get(itemDescription);
            orderedItem.increaseCount();   
            addItemToCart(orderedItem);    
        } else {
            OrderedItem orderedItem = new OrderedItem(itemDescription, mainItemPrice + additionalPrice, Category.SET , 1);
            addItemToCart(orderedItem);
        }
        updateCartDisplay();   
    }

    private int calculateAdditionalPrice(String side, String drink) {
        boolean sideSelected = !side.equals("None");
        boolean drinkSelected = !drink.equals("None");

        if (sideSelected && drinkSelected) {
            return 5;  
        } else if (sideSelected || drinkSelected) {
            return 3;  
        } else {
            return 0;  
        }
    }

    private String selectSide() {
         
        List<Menu> sideItems = menuManager.getMenuItemsByCategory(Category.SIDE);
         
        String[] sideOptions = new String[sideItems.size() + 1];

        for (int i = 0; i < sideItems.size(); i++) {
            sideOptions[i] = sideItems.get(i).getMenuName();
        }
        sideOptions[sideItems.size()] = "None";
        if (sideOptions.length == 0) {
            return "None";  
        }
        return (String) JOptionPane.showInputDialog(
                this,
                "Select a side:",
                "Side Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sideOptions,
                sideOptions[0]);
    }
    private String selectDrink() {

        List<Menu> drinkItems = menuManager.getMenuItemsByCategory(Category.DRINK);
        String[] drinkOptions = new String[drinkItems.size() + 1];

        for (int i = 0; i < drinkItems.size(); i++) {
            drinkOptions[i] = drinkItems.get(i).getMenuName();
        }
        drinkOptions[drinkItems.size()] = "None";
        return (String) JOptionPane.showInputDialog(
                this,
                "Select a drink:",
                "Drink Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                drinkOptions,
                drinkOptions[0]);
    }
    private void proceedToPayment() {
        System.out.println("Hello");
    }
}