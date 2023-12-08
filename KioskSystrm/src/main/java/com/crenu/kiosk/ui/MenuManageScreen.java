package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.crenu.kiosk.cart.CartItem;
import com.crenu.kiosk.menu.Menu;
import static com.crenu.kiosk.KioskSystem.*;

public class MenuManageScreen {
    JPanel main;
    JPanel menuListPanel;

    public MenuManageScreen(){
        this.main = new JPanel();
        this.main.setLayout(new BorderLayout());
        uiManager.addPanel(MANAGER_PNAELNAME, this.main);

        initMenuList();
    }

    private void initMenuList(){
        menuListPanel = new JPanel();
        menuListPanel.setLayout(new FlowLayout());
        menuListPanel.setPreferredSize(new Dimension(860, 800));
        menuListPanel.setBackground(Color.GREEN);
        for(Menu menu : menuManager.getMenuItems()){
            addMenuPanel(menu);
        }
        this.main.add(menuListPanel, BorderLayout.NORTH);
        menuListPanel.revalidate();
        menuListPanel.repaint();
    }

    private void addMenuPanel(Menu menu){
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setPreferredSize(new Dimension(860, 100));

        JButton deleteButton = new JButton("+");
        JLabel nameText = new JLabel();
        nameText.setText(menu.getName());
        JLabel countText = new JLabel();
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                countText.setText("Count : " + menu.getCount());
                //삭제를 한다
            }
        });
        menuPanel.add(deleteButton, BorderLayout.WEST);
        menuPanel.add(nameText, BorderLayout.NORTH);
        menuPanel.add(countText, BorderLayout.CENTER);
        this.menuListPanel.add(menuPanel);
    }

}
