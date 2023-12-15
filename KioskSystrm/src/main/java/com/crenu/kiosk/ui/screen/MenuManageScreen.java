package com.crenu.kiosk.ui.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.crenu.kiosk.entity.Category;
import com.crenu.kiosk.menu.Menu;
import com.crenu.kiosk.ui.panel.KioskPanel;

import static com.crenu.kiosk.KioskSystem.*;
import static com.crenu.kiosk.entity.PanelName.*;

public class MenuManageScreen extends KioskPanel {
    JPanel menuListPanel;
    JPanel menuEditPanel;


    @Override
    public void init() {
        setLayout(new BorderLayout());
        //hard coding
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Bulgogi Burger", 8, Category.MAIN));
        menuManager.addMenuItem(new com.crenu.kiosk.menu.Menu("Cheese Burger", 7, Category.MAIN));
        menuManager.addMenuItem(new Menu("Veggie Burger", 6, Category.MAIN));
        menuManager.addMenuItem(new Menu("Cola", 1, Category.DRINK));
        menuManager.addMenuItem(new Menu("Water", 1, Category.DRINK));
        menuManager.addMenuItem(new Menu("Lemonade", 1, Category.DRINK));
        menuManager.addMenuItem(new Menu("Cola", 1, Category.SIDE));
        menuManager.addMenuItem(new Menu("Water", 1, Category.SIDE));
        menuManager.addMenuItem(new Menu("Lemonade", 1, Category.SIDE));

        initMenuList();
        initMenuEdit();

        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(MANAGER_PANELNAME.getName());
            }
        });
        add(backButton,BorderLayout.SOUTH);
    }

    @Override
    public void changeAction() {

    }

    private void initMenuList(){
        menuListPanel = new JPanel();
        menuListPanel.setLayout(new FlowLayout());
        menuListPanel.setPreferredSize(new Dimension(840, 600));
        menuListPanel.setBackground(Color.GREEN);
        for(Menu menu : menuManager.getMenuItems()){
            addMenuPanel(menu);
        }
        add(menuListPanel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }

    private void initMenuEdit(){
        menuEditPanel = new JPanel();
        menuEditPanel.setLayout(new GridLayout());
        menuEditPanel.setPreferredSize(new Dimension(840,100));
        menuEditPanel.setBackground(Color.YELLOW);

        JButton btnAdd = new JButton("Add Menu");
        JTextField nameField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JComboBox<Category> categoryComboBox = new JComboBox<>(Category.values());

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Integer price = Integer.parseInt(priceField.getText());
                Category category = (Category)categoryComboBox.getSelectedItem();
                Menu menu = new Menu(name, price, category);
                removeMenu(menu); //remove a menu with the same name
                menuManager.addMenuItem(menu);
                addMenuPanel(menu);
            }
        });

        menuEditPanel.add(nameField);
        menuEditPanel.add(priceField);
        menuEditPanel.add(categoryComboBox);
        menuEditPanel.add(btnAdd);
        add(menuEditPanel);
    }

    private void addMenuPanel(Menu menu){
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout());
        menuPanel.setPreferredSize(new Dimension(840, 60));

        JButton deleteButton = new JButton("delete");
        JLabel nameText = new JLabel();
        JLabel priceText = new JLabel();
        JLabel categoryText = new JLabel();
        nameText.setText(menu.getName());
        priceText.setText(""+menu.getPrice());
        categoryText.setText(menu.getCategory().name());
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMenu(menu);
            }
        });
        menuPanel.add(nameText);
        menuPanel.add(priceText);
        menuPanel.add(categoryText);
        menuPanel.add(deleteButton);
        this.menuListPanel.add(menuPanel);
        revalidate();
        repaint();
    }

    private void removeMenu(Menu menuToRemove){
        menuManager.removeMenuItem(menuToRemove);
        menuListPanel.removeAll();
        for(Menu menu : menuManager.getMenuItems()){
            addMenuPanel(menu);
        }
        menuListPanel.revalidate();
        menuListPanel.repaint();
    }


}
