package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.ui.panel.KioskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.dataInit;
import static com.crenu.kiosk.KioskSystem.panelManager;
import static com.crenu.kiosk.entity.PanelNameEntity.*;

public class InitialScreen extends KioskPanel {
    @Override
    public void init() {
        setLayout(new BorderLayout());

        JButton startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(860, 600));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(MENU_PANELNAME.getName());
            }
        });

        JButton loginButton = new JButton("Admin page");
        loginButton.setPreferredSize(new Dimension(860, 100));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(LOGIN_PANELNAME.getName());
            }
        });

        add(startButton, BorderLayout.NORTH);
        add(loginButton, BorderLayout.SOUTH);
    }

    @Override
    public void changeAction() {
        dataInit();
    }
}
