package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.uiManager;
import static com.crenu.kiosk.ui.PanelNameEntity.*;

public class InitialScreen {
    public static void init() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuDisplayScreen menuDisplayScreen = new MenuDisplayScreen();

                uiManager.addPanel(MENU_PANELNAME, menuDisplayScreen, BorderLayout.CENTER);
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(MENU_PANELNAME, true);
            }
        });

        JButton loginButton = new JButton("Admin page");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();

                uiManager.addPanel(LOGIN_PANELNAME, loginScreen, BorderLayout.CENTER);
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(LOGIN_PANELNAME, true);
            }
        });

        panel.add(startButton);
        panel.add(loginButton);
         
        uiManager.addPanel(INITAL_PANELNAME, panel, BorderLayout.CENTER);
        uiManager.setVisible(true);
    }
}
