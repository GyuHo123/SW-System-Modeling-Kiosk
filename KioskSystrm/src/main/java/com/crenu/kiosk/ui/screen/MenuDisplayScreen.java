package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.cart.CartItem;
import com.crenu.kiosk.entity.Category;
import com.crenu.kiosk.menu.Menu;
import com.crenu.kiosk.ui.panel.KioskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.*;
import static com.crenu.kiosk.entity.PanelName.CART_PNAELNAME;
import static com.crenu.kiosk.entity.PanelName.INITAL_PANELNAME;

public class MenuDisplayScreen extends KioskPanel {
    private JPanel menuPanel;
    private JPanel infoPanel;

    private JButton addItemButton;

    private CartItem selectItem;
    private Category initCategory = Category.MAIN;

    @Override
    public void init() {
        setLayout(new BorderLayout());

        initSelectPanel();
        initMenuPanel();
        initInfoPanel();
        initFunctionPanel();
    }

    @Override
    public void changeAction() {
        updateCartItemCountText();
        setMenuPanel(initCategory);
    }

    private void initSelectPanel(){
        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new GridLayout(0, Category.values().length, 10, 10));
        selectPanel.setPreferredSize(new Dimension(860, 80));
        for(Category category : Category.values()){
            addSelectButton(selectPanel, category);
        }
        add(selectPanel, BorderLayout.NORTH);
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
        add(menuPanel, BorderLayout.CENTER);
    }

    private void setMenuPanel(Category category){
        menuPanel.removeAll();
        for(Menu item :  menuManager.getMenuItemsByCategory(category)){
            addMenuButton(menuPanel, item);
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
        add(infoPanel, BorderLayout.SOUTH);
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

    private void updateCartItemCountText(){
        addItemButton.setText("ADD ITEM : " + cart.getCartItems().size());
    }

    private void initFunctionPanel(){
        JPanel funPanel = new JPanel();
        funPanel.setLayout(new GridLayout(0, 3));
        funPanel.setPreferredSize(new Dimension(860, 100));
        funPanel.setBackground(Color.YELLOW);
        addItemButton = new JButton("ADD ITEM : " + cart.getCartItems().size());

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        funPanel.add(addItemButton);
        JButton payButton = new JButton("PAY");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(CART_PNAELNAME.getName());
            }
        });
        funPanel.add(payButton);

        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(INITAL_PANELNAME.getName());
            }
        });
        funPanel.add(backButton);

        infoPanel.add(funPanel, BorderLayout.SOUTH);
    }

    public void addItem(){
        if(selectItem == null){
            return;
        }
        cart.addCartItem(selectItem);
        clearSelectItem();
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



}