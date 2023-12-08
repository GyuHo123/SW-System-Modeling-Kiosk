package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.uiManager;

public class ManagerScreen extends JPanel {
    public ManagerScreen() {
        setSize(460, 600);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton menuManageBtn = new JButton("Menu Manage");
        JButton orderManageBtn = new JButton("Order Manage");
        add(menuManageBtn);
        add(orderManageBtn);
    }
}
