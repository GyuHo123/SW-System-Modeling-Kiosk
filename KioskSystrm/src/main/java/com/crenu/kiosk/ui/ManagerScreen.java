package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.uiManager;
import static com.crenu.kiosk.ui.PanelNameEntity.*;

public class ManagerScreen extends JPanel {
    public ManagerScreen() {
        setSize(460, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton menuManageBtn = new JButton("Menu Manage");
        JButton orderManageBtn = new JButton("Order Manage");
        add(menuManageBtn);
        add(orderManageBtn);

        menuManageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuManageScreen();
            }
        });

        orderManageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderManageScreen orderManageScreen = new OrderManageScreen();
                uiManager.addPanel(ORDER_MANAGE_PANELNAME, orderManageScreen);
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(ORDER_MANAGE_PANELNAME, true);
            }
        });
    }

}
