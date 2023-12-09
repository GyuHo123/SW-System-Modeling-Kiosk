package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.crenu.kiosk.admin.MenuManager;
import com.crenu.kiosk.menu.Category;
import com.crenu.kiosk.menu.Menu;
import static com.crenu.kiosk.KioskSystem.*;
import static com.crenu.kiosk.ui.PanelNameEntity.*;

public class MenuManageScreen extends JPanel{
    JPanel menuListPanel;
    JPanel menuEditPanel;

    public MenuManageScreen(){
        setLayout(new BorderLayout());
        uiManager.allPanelVisibleOff();
        uiManager.addPanel(MENU_MANAGE_PNAELNAME, this);

        initMenuList();
        initMenuEdit();

        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerScreen managerScreen = new ManagerScreen();
                uiManager.addPanel(MANAGER_PANELNAME, managerScreen);
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(MANAGER_PANELNAME, true);
                menuListPanel.revalidate();
                menuListPanel.repaint();
            }
        });
        add(backButton,BorderLayout.SOUTH);


        revalidate();
        repaint();
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
