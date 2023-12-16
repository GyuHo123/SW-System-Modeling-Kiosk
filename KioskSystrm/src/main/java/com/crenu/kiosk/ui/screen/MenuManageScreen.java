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
    private JPanel menuListPanel;
    private JPanel menuEditPanel;

    private JButton btnAdd = new JButton("Add Menu");
    private JTextField nameField = new JTextField(20);
    private JTextField priceField = new JTextField(20);
    private JComboBox<Category> categoryComboBox = new JComboBox<>(Category.values());


    @Override
    public void init() {
        setLayout(new BorderLayout());

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

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuInfo();
            }
        });

        menuEditPanel.add(nameField);
        menuEditPanel.add(priceField);
        menuEditPanel.add(categoryComboBox);
        menuEditPanel.add(btnAdd);
        add(menuEditPanel);
    }

    public void addMenuInfo(){
        String name = nameField.getText();
        Integer price = Integer.parseInt(priceField.getText());
        Category category = (Category)categoryComboBox.getSelectedItem();
        addMenu(name, price, category);
    }

    public void addMenu(String name, Integer price, Category category){
        Menu menu = new Menu(name, price, category);
        removeMenu(menu); //remove a menu with the same name
        menuManager.addMenuItem(menu);
        addMenuPanel(menu);
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
