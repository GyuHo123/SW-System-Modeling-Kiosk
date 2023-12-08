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
        panel.setLayout(new BorderLayout());

        JButton startButton = new JButton("START");
        startButton.setPreferredSize(new Dimension(860, 600));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuDisplayScreen();
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(MENU_PANELNAME, true);
            }
        });

        JButton loginButton = new JButton("Admin page");
        loginButton.setPreferredSize(new Dimension(860, 100));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginScreen = new LoginScreen();

                uiManager.addPanel(LOGIN_PANELNAME, loginScreen);
                uiManager.allPanelVisibleOff();
                uiManager.panelSetVisible(LOGIN_PANELNAME, true);
            }
        });

        panel.add(startButton, BorderLayout.NORTH);
        panel.add(loginButton, BorderLayout.SOUTH);
         
        uiManager.addPanel(INITAL_PANELNAME, panel);
        uiManager.setVisible(true);
        uiManager.validate();
        uiManager.repaint();
    }
}
