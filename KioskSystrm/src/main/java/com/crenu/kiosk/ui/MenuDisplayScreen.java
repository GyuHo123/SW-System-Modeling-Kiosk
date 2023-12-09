package com.crenu.kiosk.ui;

import com.crenu.kiosk.cart.CartItem;
import com.crenu.kiosk.menu.Category;
import com.crenu.kiosk.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.*;
import static com.crenu.kiosk.ui.PanelNameEntity.CART_PNAELNAME;
import static com.crenu.kiosk.ui.PanelNameEntity.MENU_PANELNAME;

public class MenuDisplayScreen{
    private JPanel main;
    private JPanel menuPanel;
    private JPanel infoPanel;


    private CartItem selectItem;

    public MenuDisplayScreen() {

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

        this.main = new JPanel();
        this.main.setLayout(new BorderLayout());
        uiManager.addPanel(MENU_PANELNAME, this.main);

        initSelectPanel();
        initMenuPanel();
        initInfoPanel();
        initFunctionPanel();

//        JButton backButton = new JButton("back");
//        backButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                uiManager.allPanelVisibleOff();
//                InitialScreen.init();
//            }
//        });
//        this.main.add(backButton, BorderLayout.SOUTH);
    }

    private void initSelectPanel(){
        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new GridLayout(0, Category.values().length, 10, 10));
        selectPanel.setPreferredSize(new Dimension(860, 80));
        for(Category category : Category.values()){
            addSelectButton(selectPanel, category);
        }
        this.main.add(selectPanel, BorderLayout.NORTH);
    }

    private void addSelectButton(JPanel panel, Category category) {
        JButton button = new JButton(category.name());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenuPanel(category);
            }
        });
        panel.add(button);
    }

    private void initMenuPanel(){
        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuPanel.setPreferredSize(new Dimension(800, 400));
        menuPanel.setBackground(Color.BLUE);
        this.main.add(menuPanel, BorderLayout.CENTER);
    }

    private void setMenuPanel(Category category){
        for(Component com : menuPanel.getComponents()){
            menuPanel.remove(com);
        }
        for(Menu item :  menuManager.getMenuItemsByCategory(category)){
            addMenuButton(menuPanel, item);
            System.out.println(item.getName());
        }
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void addMenuButton(JPanel panel, Menu menu) {
        JButton button = new JButton(menu.getName());
        button.setPreferredSize(new Dimension(180, 200));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upateInfo(menu);
            }
        });
        panel.add(button);
    }

    private void initInfoPanel(){
        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setPreferredSize(new Dimension(860, 300));
        infoPanel.setBackground(Color.red);
        this.main.add(infoPanel, BorderLayout.SOUTH);
    }

    private void upateInfo(Menu menu){
        for(Component com : infoPanel.getComponents()){
            infoPanel.remove(com);
        }
        selectItem = menu.toCartItem();
        JButton plusButton = new JButton("+");
        JLabel nameText = new JLabel();
        nameText.setText(menu.getName());
        JLabel countText = new JLabel();
        countText.setText("Count : " + 1 );
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem.addCount();
                countText.setText("Count : " + selectItem.getCount());
            }
        });
        JButton minusButton = new JButton("-");
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItem.minusCount();
                if(selectItem.getCount() < 1){
                    clearSelectItem();
                    return;
                }
                countText.setText("Count : " + selectItem.getCount());
            }
        });
        infoPanel.add(plusButton, BorderLayout.WEST);
        infoPanel.add(nameText, BorderLayout.NORTH);
        infoPanel.add(minusButton, BorderLayout.EAST);
        infoPanel.add(countText, BorderLayout.CENTER);
        initFunctionPanel();
        infoPanel.revalidate();
        infoPanel.repaint();

    }

    private void clearSelectItem(){
        for(Component com : infoPanel.getComponents()){
            infoPanel.remove(com);
        }
        selectItem = null;
        initFunctionPanel();
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    private void initFunctionPanel(){
        JPanel funPanel = new JPanel();
        funPanel.setLayout(new GridLayout(0, 2));
        funPanel.setPreferredSize(new Dimension(860, 100));
        funPanel.setBackground(Color.YELLOW);
        JButton addItemButton = new JButton("ADD ITEM : " + cart.getCartItems().size());

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectItem == null){
                    return;
                }
                cart.addCartItem(selectItem);
                clearSelectItem();
            }
        });
        funPanel.add(addItemButton);
        JButton payButton = new JButton("PAY");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new CartScreen();
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(CART_PNAELNAME, true);
            }
        });
        funPanel.add(payButton);
        infoPanel.add(funPanel, BorderLayout.SOUTH);
    }


}