package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.ui.panel.KioskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.panelManager;
import static com.crenu.kiosk.entity.PanelName.*;

public class ManagerScreen extends KioskPanel {
    @Override
    public void init() {
        setSize(460, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton menuManageBtn = new JButton("Menu Manage");
        JButton orderManageBtn = new JButton("Order Manage");

        menuManageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(MENU_MANAGE_PNAELNAME.getName());
            }
        });

        orderManageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(ORDER_MANAGE_PANELNAME.getName());
            }
        });
        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(INITAL_PANELNAME.getName());
            }
        });

        add(backButton, BorderLayout.SOUTH);
        add(menuManageBtn);
        add(orderManageBtn);
    }

    @Override
    public void changeAction() {

    }
}
