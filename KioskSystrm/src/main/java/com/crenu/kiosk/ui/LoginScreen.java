package com.crenu.kiosk.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.crenu.kiosk.KioskSystem.uiManager;
import static com.crenu.kiosk.ui.PanelNameEntity.LOGIN_PANELNAME;
import static com.crenu.kiosk.ui.PanelNameEntity.MANAGER_PANELNAME;

public class LoginScreen extends JPanel {

    public LoginScreen() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btnLogin = new JButton("Login");
        JTextField userIDField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkCredentials(userIDField.getText(), String.valueOf(passwordField.getPassword()))){
                    turnToManagerScrren();
                };
            }
        });

        add(userIDField);
        add(passwordField);
        add(btnLogin);
    }

    public void turnToManagerScrren() {
        ManagerScreen managerScreen = new ManagerScreen();
        uiManager.addPanel(MANAGER_PANELNAME, managerScreen);
        uiManager.allPanelVisibleOff();
        uiManager.panelSetVisible(MANAGER_PANELNAME, true);
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
}
