package com.crenu.kiosk.ui.screen;

import com.crenu.kiosk.ui.panel.KioskPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static com.crenu.kiosk.KioskSystem.panelManager;
import static com.crenu.kiosk.entity.PanelNameEntity.*;


public class LoginScreen extends KioskPanel {

    @Override
    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btnLogin = new JButton("Login");
        JTextField userIDField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkCredentials(userIDField.getText(), String.valueOf(passwordField.getPassword()))){
                    panelManager.changePanel(MANAGER_PANELNAME.getName());
                };
            }
        });

        add(userIDField);
        add(passwordField);
        add(btnLogin);


        JButton backButton = new JButton("back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.changePanel(INITAL_PANELNAME.getName());
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    private boolean checkCredentials(String userID, String password) {
        String adminID = "admin";
        String adminPassword = "password";

        if(userID.equals(adminID) && password.equals(adminPassword)) {
            JOptionPane.showMessageDialog(null, "로그인 성공");
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials. Please try again.");
            return false;
        }
    }

    @Override
    public void changeAction() {

    }
}
